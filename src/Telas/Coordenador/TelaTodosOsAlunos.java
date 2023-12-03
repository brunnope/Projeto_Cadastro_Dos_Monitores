package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;

import Classes.Aluno;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.Aluno.TelaEditarInformacoesAluno;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;
import Telas.FabricaComponentes.FabricaTableComFiltro;
import javax.swing.event.DocumentListener;

public class TelaTodosOsAlunos extends TelaPadrao{
	private JTable tableAlunos;
	JTextField tFiltro;
	public TelaTodosOsAlunos() {
		super("TODOS OS ALUNOS");
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
	}


	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("TODOS OS", 440, 110, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("ALUNOS", 440, 150, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lFiltro = FabricaJLabel.criarJLabel("Filtro", 250, 203, 50, 30, Color.BLACK, 12);
		add(lFiltro);
	}

	private void adicionarTextFields() {
		tFiltro = FabricaJTextField.criarJTextField(280, 233, 370, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tFiltro.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtrarTabela();
			}

			public void removeUpdate(DocumentEvent e) {
				filtrarTabela();
			}

			public void changedUpdate(DocumentEvent e) {
				filtrarTabela();
			}
		});
		add(tFiltro); 
	}


	private void adicionarTable() {
		ArrayList<Aluno> alunos = getCentral().getTodosOsAlunos();

		FabricaTableComFiltro mAlunos = new FabricaTableComFiltro(alunos);
		mAlunos.addColumn("Aluno(a)");
		mAlunos.addColumn("Matricula");
		tableAlunos = new JTable(mAlunos) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableAlunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane rolagemTabela = new JScrollPane(tableAlunos);
		rolagemTabela.setBounds(250, 280, 400, 350);
		add(rolagemTabela);

	}

	private void filtrarTabela() {
		String termo = tFiltro.getText();
		((FabricaTableComFiltro) tableAlunos.getModel()).filtrar(termo);
	}

	private void adicionarButtons() {		
		JButton bEditar = FabricaJButton.criarJButton("Editar", 292, 640, 101, 30, Color.GREEN, Color.WHITE, 12);
		add(bEditar);
		bEditar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tableAlunos.getSelectedRow() == -1){
					FabricaJOptionPane.criarMsgErro("Selecione algum Aluno!");
				}else {
					dispose();
					Aluno l = getCentral().getTodosOsAlunos().get(tableAlunos.getSelectedRow());
					new TelaEditarInformacoesAluno(l);
				}
			}
		});

		JButton bVisualizar = FabricaJButton.criarJButton("Visualizar", 506, 640, 101, 30, Color.GREEN, Color.WHITE, 12);
		bVisualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tableAlunos.getSelectedRow() == -1){
					FabricaJOptionPane.criarMsgErro("Selecione algum Aluno!");
				}else {
					dispose();
					Aluno l = getCentral().getTodosOsAlunos().get(tableAlunos.getSelectedRow());
					new TelaVisualizarAluno(l);
				}
			}
		});
		add(bVisualizar);
		
		JButton bEnviarEmail = FabricaJButton.criarJButton("Enviar email", 399, 640, 101, 30, Color.GREEN, Color.WHITE, 12);
		bEnviarEmail.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tableAlunos.getSelectedRow() == -1){
					FabricaJOptionPane.criarMsgErro("Selecione algum Aluno!");
				}else {
					new TelaEnviarEmail(getCentral().getTodosOsAlunos().get(tableAlunos.getSelectedRow()).getEmail());
				}
			}
		});
		add(bEnviarEmail);
		
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 292, 680, 315, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaHomeCoordenador();
			}
		});
		
	}

	private void adicionarIcones() {
		JLabel iconeLupa = FabricaIcones.criarIcone(FabricaImagens.VISUALIZAR, 240, 230, 50, 30);
		add(iconeLupa);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 100, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);

	}
}