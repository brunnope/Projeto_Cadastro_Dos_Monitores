package Telas.Aluno;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.EditalNaoEncontradoException;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;

public class TelaVisualizarResultado extends TelaPadrao{

	private Persistencia dados = new Persistencia();
	private CentralDeInformacoes central = dados.recuperarCentral("central.xml");
	//apagar id
	private EditalDeMonitoria edital;
	
	public TelaVisualizarResultado() {
		super("DETALHES RESULTADO");
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		try {
			edital = central.recuperarEditalPeloId(central.getTodosOsEditais().get(0).getId());
		} catch (EditalNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adicionarMenuBar();
		adicionarLabels();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
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
		JButton bDesistir = FabricaJButton.criarJButton("Desistir", 350, 630, 200, 30, Color.GREEN, Color.WHITE, 12);
		add(bDesistir);
	}

	private void adicionarIcones() {
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 330, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
		
	}
	public static void main(String[] args) {
		TelaVisualizarResultado t = new TelaVisualizarResultado();
	}

}
