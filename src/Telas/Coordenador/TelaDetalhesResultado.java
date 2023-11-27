package Telas.Coordenador;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaDetalhesResultado extends TelaPadrao{
	private Persistencia dados = new Persistencia();
	private CentralDeInformacoes central = dados.recuperarCentral("central.xml");
	//apagar id
	private EditalDeMonitoria edital = central.recuperarEditalPeloId(central.getTodosOsEditais().get(0).getId());
	
	public TelaDetalhesResultado() {
		super("DETALHES RESULTADO");
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
	}
	
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador();
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("DETALHES", 440, 110, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("RESULTADO", 440, 150, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		


	}
	
	private void adicionarTable() {
		DefaultTableModel mResultados = new DefaultTableModel();
		
		mResultados.addColumn("Disciplina");
		mResultados.addColumn("Matrícula");
		mResultados.addColumn("Pontuação");
		mResultados.addColumn("Resultado");
		
		
		ArrayList<Disciplina> disciplinas = edital.getDisciplinas();
		

		// Torna todas as células não editáveis
		JTable tableDisciplinas = new JTable(mResultados) {

			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//permite apenas uma seleção
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(295, 250, 315, 350);
		add(rolagemTabela);
		
	}
	
	private void adicionarButtons() {
		JButton bFecharEdital = FabricaJButton.criarJButton("Fechar Edital", 293, 610, 315, 30, Color.GREEN, Color.WHITE, 12);
		add(bFecharEdital);
		
		JButton bEnviarEmail = FabricaJButton.criarJButton("Enviar Email", 293, 650, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bEnviarEmail);
		
		JButton bGerarPDF = FabricaJButton.criarJButton("Gerar PDF", 458, 650, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bGerarPDF);
	}

	private void adicionarIcones() {
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
		
	}
	public static void main(String[] args) {
		TelaDetalhesResultado t = new TelaDetalhesResultado();
	}

}
