package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
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

import com.itextpdf.text.DocumentException;

import Classes.Aluno;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Classes.GeradorDeRelatorios;
import Classes.Inscricao;
import Excecoes.AlunoNaoEncontradoException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;

public class TelaDetalhesResultado extends TelaPadrao{
	private EditalDeMonitoria edital;
	private JButton bFecharEdital;
	private JTable tableDisciplinas;
	
	public TelaDetalhesResultado(EditalDeMonitoria edital) {
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
		
		if (edital.getResultado().equals("final")) {
			bFecharEdital.setEnabled(false);
		}
	}
	
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
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
		bFecharEdital = FabricaJButton.criarJButton("Fechar Edital", 273, 610, 352, 30, Color.GREEN, Color.WHITE, 12);
		add(bFecharEdital);
		bFecharEdital.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opc = FabricaJOptionPane.criarMsgDeOpcao("Confirmação","Fechar Edital?");
				if (opc == JOptionPane.YES_OPTION) {
					FabricaJOptionPane.criarMsgValido("Edital finalizado com sucesso!");
					edital.setResultado("final");
					getDados().salvarCentral(getCentral(), "central.xml");
				}
				
			}
		});
		
		JButton bEnviarEmail = FabricaJButton.criarJButton("Enviar Email", 273, 650, 169, 30, Color.GREEN, Color.WHITE, 12);
		add(bEnviarEmail);
		bEnviarEmail.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tableDisciplinas.getSelectedRow() == -1){
					FabricaJOptionPane.criarMsgErro("Selecione algum Aluno!");
				}else {
					
					String matricula = (String) tableDisciplinas.getValueAt(tableDisciplinas.getSelectedRow(), 1);
					Aluno aluno;
					try {
						aluno = getCentral().recuperarAlunoPorMatricula(matricula);
						new TelaEnviarEmail(aluno.getEmail());
					} catch (AlunoNaoEncontradoException e1) {
						FabricaJOptionPane.criarMsgErro("Erro ao enviar!");
					}
				}
			}
		});
		
		JButton bGerarPDF = FabricaJButton.criarJButton("Gerar PDF", 458, 650, 169, 30, Color.GREEN, Color.WHITE, 12);
		add(bGerarPDF);
		bGerarPDF.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opc = FabricaJOptionPane.criarMsgDeOpcao("Confirmação", "Gerar PDF do resultado?");
				if (opc == JOptionPane.YES_OPTION) {
					try {
						String caminho = FabricaJOptionPane.criarInput("Digite o título do arquivo: ");
						
						if((caminho == null) || (caminho.isBlank())) {
							FabricaJOptionPane.criarMsgErro("Digite algum título!");
						}
						else {
							if (caminho.endsWith(".pdf")) {
								GeradorDeRelatorios.gerarPDF(caminho, tableDisciplinas, edital);
								FabricaJOptionPane.criarMsgValido("PDF gerado com sucesso!");
							} else {
							    FabricaJOptionPane.criarMsgErro("O arquivo não é um PDF");
							}
							
						}
					} catch (FileNotFoundException | DocumentException e1) {
						FabricaJOptionPane.criarMsgErro(e1.getMessage());
					}
				}
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
