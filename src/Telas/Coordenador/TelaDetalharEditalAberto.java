package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.TelaVisualizarEditais;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaDetalharEditalAberto extends TelaPadrao{
	private EditalDeMonitoria edital;
	JButton bClonar;
	JButton bEncerrar;

	public TelaDetalharEditalAberto(EditalDeMonitoria edital) {
		super("DETALHES EDITAL ABERTO");
		this.edital = edital;
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTextFields();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
		
		if (edital.getStatus().equals("não abertas")) {
			bClonar.setEnabled(false);
			bEncerrar.setEnabled(false);
		}
	}
	
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("DETALHES", 440, 110, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("EDITAL", 440, 150, 180, 30, Color.BLACK, 30);
		add(lTitulo);
		
		JLabel lIdEdital = FabricaJLabel.criarJLabel("ID Edital", 297, 227, 100, 30, Color.BLACK, 12);
		add(lIdEdital);
		
		JLabel lNumEdital = FabricaJLabel.criarJLabel("Número Edital", 297, 280, 100, 30, Color.BLACK, 12);
		add(lNumEdital);
		
		
		JLabel lDataInicio = FabricaJLabel.criarJLabel("Data de Inicio", 295, 335, 150, 30, Color.BLACK, 12);
		add(lDataInicio);
		
		JLabel lDataFim = FabricaJLabel.criarJLabel("Data Final", 458, 335, 150, 30, Color.BLACK, 12);
		add(lDataFim);
		
		JLabel lPesoCRE = FabricaJLabel.criarJLabel("Peso CRE Aluno", 297, 505, 200, 30, Color.BLACK, 12);
		add(lPesoCRE);
		
		JLabel lPesoMedia = FabricaJLabel.criarJLabel("Peso Média Aluno", 459, 505, 200, 30, Color.BLACK, 12);
		add(lPesoMedia);

	}
	
	private void adicionarTextFields() {
		JTextField tIdEdital = FabricaJTextField.criarJTextField(325, 253, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tIdEdital.setText(Long.toString(edital.getId()));
		tIdEdital.setEditable(false);
		add(tIdEdital);
		
		JTextField tNumEdital = FabricaJTextField.criarJTextField(325, 305, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNumEdital.setText(Float.toString(edital.getNumeroEdital()));
		tNumEdital.setEditable(false);
		add(tNumEdital);
		
		JTextField fDataInicio = FabricaJTextField.criarJTextField(325, 360, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataInicio.setText(edital.getDataInicio().toString());
		fDataInicio.setEditable(false);
		fDataInicio.setToolTipText("dd/MM/yyyy");
		fDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataInicio);
		
		
		JTextField fDataFim = FabricaJTextField.criarJTextField(487, 360, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataFim.setText(edital.getDataFim().toString());
		fDataFim.setEditable(false);
		fDataFim.setToolTipText("dd/MM/yyyy");
		fDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataFim);
		
		JTextField fPesoCRE = FabricaJTextField.criarJTextField(325, 530, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoCRE.setText(Float.toString(edital.getPesoCRE()));
		fPesoCRE.setEditable(false);
		fPesoCRE.setToolTipText("No máximo 2 dígitos");
		add(fPesoCRE);
		
		JTextField fPesoMedia = FabricaJTextField.criarJTextField(487, 530, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoMedia.setText(Float.toString(edital.getPesoMedia()));
		fPesoMedia.setEditable(false);
		fPesoMedia.setToolTipText("No máximo 2 dígitos");
		add(fPesoMedia);
		
	}
	
	private void adicionarTable() {
		DefaultTableModel mDisciplinas = new DefaultTableModel();
		
		mDisciplinas.addColumn("Disciplina(s)");
		mDisciplinas.addColumn("Vagas");
		mDisciplinas.addColumn("Inscritos");
		
		ArrayList<Disciplina> disciplinas = edital.getDisciplinas();
		for(Disciplina disciplina: disciplinas) {
			Object[] linha = new Object[3];
			linha[0] = disciplina.getNome();
			linha[1] = disciplina.getQuantDeVagasRemuneradas() + disciplina.getQuantDeVagasVoluntarias();
			linha[2] = disciplina.getInscricoes().size();
			mDisciplinas.addRow(linha);
		}

		// Torna todas as células não editáveis
		JTable tableDisciplinas = new JTable(mDisciplinas) {

			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//permite apenas uma seleção
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(295, 400, 315, 100);
		add(rolagemTabela);
		
	}
	
	private void adicionarButtons() {
		JButton bEditar = FabricaJButton.criarJButton("Editar", 293, 610, 100, 30, Color.GREEN, Color.WHITE, 12);
		add(bEditar);
		bEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaEditarEdital(edital);
			}
		});
		
		bClonar = FabricaJButton.criarJButton("Clonar", 400, 610, 100, 30, Color.GREEN, Color.WHITE, 12);
		add(bClonar);
		bClonar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int opcao = FabricaJOptionPane.criarMsgDeOpcao("CONFIRMAÇÃO", "Clonar Edital?");
				if (opcao == JOptionPane.YES_OPTION) {
					
					//cria novos objetos de disciplina para evitar conexão entre os editais
					ArrayList<Disciplina>  disciplinas = new ArrayList<>();
		            for (Disciplina disciplina : edital.getDisciplinas()) {
		                disciplinas.add(new Disciplina(disciplina.getNome(), disciplina.getQuantDeVagasRemuneradas(), disciplina.getQuantDeVagasVoluntarias()));
		            }
					
					EditalDeMonitoria editalClonado = new EditalDeMonitoria(edital.getNumeroEdital(), edital.getDataInicio(), edital.getDataFim(), disciplinas,
							edital.getPesoCRE(), edital.getPesoMedia(), edital.getNumMaxInscricoes());
						FabricaJOptionPane.criarMsgValido("Clone Criado com sucesso!");
						dispose();
						new TelaClonarEdital(editalClonado);
				}
			}
		});
		
		
		bEncerrar = FabricaJButton.criarJButton("Gerar Resultado", 507, 610, 100, 30, Color.GREEN, Color.WHITE, 12);
		add(bEncerrar);
		bEncerrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (edital.getStatus().equals("abertas")) {
					for (Disciplina disciplina : edital.getDisciplinas()) {
						disciplina.calcularResultadoDisciplina(edital);
						disciplina.distribuirVagas();
					}
					edital.setResultado("calculado");
					edital.setStatus("finalizadas");
					FabricaJOptionPane.criarMsgValido("Resultado Calculado!");
					getDados().salvarCentral(getCentral(), "central.xml");
					dispose();
					new TelaVisualizarEditais();
				}else {
					FabricaJOptionPane.criarMsgErro("Inscrições não estão abertas!");
				}
			}
		});
		
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 650, 314, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarEditais();
			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeId = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 283, 253, 50, 30);
		add(iconeId);
		
		JLabel iconeNumero = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 283, 305, 50, 30);
		add(iconeNumero);
		
		JLabel iconeDataInicio = FabricaIcones.criarIcone(FabricaImagens.DATA, 283, 360, 50, 30);
		add(iconeDataInicio);
		
		JLabel iconeDataFim = FabricaIcones.criarIcone(FabricaImagens.DATA, 445, 360, 50, 30);
		add(iconeDataFim);
		
		
		JLabel iconePesoCRE = FabricaIcones.criarIcone(FabricaImagens.PESO, 283, 530, 50, 30);
		add(iconePesoCRE);
		
		JLabel iconePesoMedia = FabricaIcones.criarIcone(FabricaImagens.PESO, 445, 530, 50, 30);
		add(iconePesoMedia);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
	}
}
