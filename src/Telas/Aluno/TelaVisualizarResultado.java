package Telas.Aluno;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Aluno;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Classes.Inscricao;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.TelaVisualizarEditais;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;

public class TelaVisualizarResultado extends TelaPadrao{
	JTable tableDisciplinas;
	private EditalDeMonitoria edital;
	JButton bAtualizar;
	JButton bDesistir;
	
	public TelaVisualizarResultado(EditalDeMonitoria edital) {
		super("DETALHES RESULTADO");
		this.edital = edital;
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
		
		//se final, aluno não pode desistir e apenas visualizar o resultado
		if (edital.getResultado().equals("final")) {
			bDesistir.setEnabled(false);
			bAtualizar.setEnabled(false);
		}
	}
	
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("DETALHES", 410, 110, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("RESULTADO", 410, 150, 200, 30, Color.BLACK, 30);
		add(lTitulo);

	}
	
	private void adicionarTable() {
		DefaultTableModel mResultados = new DefaultTableModel();
		DecimalFormat formatoNota = new DecimalFormat("0.##");
		
		mResultados.addColumn("Disciplina");
		mResultados.addColumn("Matrícula");
		mResultados.addColumn("Pontuação");
		mResultados.addColumn("Resultado");
		
		
		ArrayList<Disciplina> disciplinas = edital.getDisciplinas();
		for(Disciplina disciplina : disciplinas) {
			List<Map.Entry<Aluno, Inscricao>> listaOrdenada = disciplina.ordenarInscricoesPorNotaFinal();
			for(Entry<Aluno, Inscricao> e : listaOrdenada) {
				Inscricao inscricao = e.getValue();
				Object[] linha = new Object[4];
				linha[0] = disciplina.getNome();
				linha[1] = inscricao.getAluno().getMatricula();
				
				double notaFinal = inscricao.getNotaFinal();
				linha[2] = formatoNota.format(notaFinal);
				
				linha[3] = inscricao.getResultado();
				mResultados.addRow(linha);
			}
	}
		
		

		// Torna todas as células não editáveis
		tableDisciplinas = new JTable(mResultados) {

			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//mostrar por completo a informação da célula selecionada
		 tableDisciplinas.addMouseMotionListener(new MouseMotionAdapter() {
	            public void mouseMoved(MouseEvent e) {
	            	// pega onde o mouse está selecionando
	                int linha = tableDisciplinas.rowAtPoint(e.getPoint());
	                int coluna = tableDisciplinas.columnAtPoint(e.getPoint());

	                // Verificar se as coordenadas são válidas
	                if (linha >= 0 && coluna >= 0) {
	                    // Obter o valor da célula e configurar o ToolTipText
	                    Object valorCelula = tableDisciplinas.getValueAt(linha, coluna);
	                    tableDisciplinas.setToolTipText(valorCelula.toString());
	                } else {
	                    // Se o mouse não estiver sobre uma célula válida, limpar o ToolTipText
	                    tableDisciplinas.setToolTipText(null);
	                }
	            }
	        });
		
		//permite apenas uma seleção
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(275, 250, 350, 350);
		add(rolagemTabela);
		
	}
	
	private void adicionarButtons() {
		bAtualizar = FabricaJButton.criarJButton("Atualizar", 275, 630, 170, 30, Color.GREEN, Color.WHITE, 12);
		add(bAtualizar);
		bAtualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarResultado(edital);
			}
		});
		
		bDesistir = FabricaJButton.criarJButton("Desistir", 455, 630, 170, 30, Color.GREEN, Color.WHITE, 12);
		add(bDesistir);
		bDesistir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tableDisciplinas.getSelectedRow() != -1) {
					
					String matriculaSelecionada = (String) tableDisciplinas.getValueAt(tableDisciplinas.getSelectedRow(), 1);
					Aluno aluno = (Aluno) getUsuario();
					
					if (matriculaSelecionada.equals(aluno.getMatricula())) {
					
						String nomeDisciplina = (String) tableDisciplinas.getValueAt(tableDisciplinas.getSelectedRow(), 0);
						Disciplina disciplina = edital.recuperarDisciplinaPeloNome(nomeDisciplina);
						if (disciplina.getInscricoes().containsKey(aluno)) {
							
							Inscricao inscricao = disciplina.getInscricoes().get(aluno);
							
							if (!(inscricao.getResultado().equals("desistente"))) {
								int opc = FabricaJOptionPane.criarMsgDeOpcao("Confirmação", "Desistir da vaga?");
								
								if(opc == JOptionPane.YES_OPTION) {
									inscricao.setDesistiu(true);
									inscricao.setResultado("desistente");
									disciplina.distribuirVagas();	
									FabricaJOptionPane.criarMsgAtencao("Você desistiu da sua vaga!");
									getDados().salvarCentral(getCentral(), "central.xml");
								}
								
							}else{
								FabricaJOptionPane.criarMsgErro("Você já é desistente!");
							}
						} else {
							FabricaJOptionPane.criarMsgErro("Você ainda não está inscrito nesta disciplina!");
						}
					}else {
						FabricaJOptionPane.criarMsgErro("Selecione sua inscrição!");
					}
					
		        } else {
		            FabricaJOptionPane.criarMsgErro("Selecione uma disciplina na tabela!");
		        }
		    }
		});	
		
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 275, 670, 350, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarEditais();
			}
		});
	}
	

	private void adicionarIcones() {
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 330, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
		
	}

}