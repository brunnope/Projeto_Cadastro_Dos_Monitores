package Telas.Coordenador;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Classes.Aluno;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaTodosOsAlunos extends TelaPadrao{
	private JTable tableAlunos;
	
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
		
		JLabel lFiltro = FabricaJLabel.criarJLabel("Filtro", 250, 223, 50, 30, Color.BLACK, 12);
		add(lFiltro);
	}
	
	private void adicionarTextFields() {
		JTextField tFiltro = FabricaJTextField.criarJTextField(280, 253, 370, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tFiltro); 
		
	}
	
	private void adicionarTable() {
		DefaultTableModel mAlunos = new DefaultTableModel();
		
		mAlunos.addColumn("Aluno(a)");
		mAlunos.addColumn("Matrícula");
		
		ArrayList<Aluno> alunos = getCentral().getTodosOsAlunos();
		for(Aluno aluno: alunos) {
			Object[] linha = new Object[2];
			linha[0] = aluno.getNome();
			linha[1] = aluno.getMatricula();
			mAlunos.addRow(linha);
		}
		

		// Torna todas as células não editáveis
		tableAlunos = new JTable(mAlunos) {

			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//permite apenas uma seleção
		tableAlunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemTabela = new JScrollPane(tableAlunos);
		rolagemTabela.setBounds(250, 300, 400, 350);
		add(rolagemTabela);
		
	}  
	
	private void adicionarButtons() {		
		JButton bEditar = FabricaJButton.criarJButton("Editar", 293, 660, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bEditar);
		
		JButton bVisualizar = FabricaJButton.criarJButton("Visualizar", 458, 660, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bVisualizar);
	}

	private void adicionarIcones() {
		JLabel iconeLupa = FabricaIcones.criarIcone(FabricaImagens.VISUALIZAR, 240, 250, 50, 30);
		add(iconeLupa);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);

	}

	public static void main(String[] args) {
		TelaTodosOsAlunos t = new TelaTodosOsAlunos();
	}
}
