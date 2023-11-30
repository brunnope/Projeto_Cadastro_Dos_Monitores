package Telas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Classes.Coordenador;
import Classes.EditalDeMonitoria;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;

public class TelaVisualizarEditais extends TelaPadrao{
	public TelaVisualizarEditais() {
		super("VISUALIZAR TODOS OS EDITAIS");
		configurarComponentes();
		setVisible(true);
	}


	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
		if (getUsuario() instanceof Coordenador) {
			adicionarMenuBar();
		}else {
			adicionarMenuBarAluno();
		}
	}
	
	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("VISUALIZAR", 430, 180, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("EDITAIS", 430, 210, 200, 30, Color.BLACK, 30);
		add(lTitulo);
	}
	
	private void adicionarTable() {
		DefaultTableModel mEditais = new DefaultTableModel();
		
		mEditais.addColumn("Num. Editais");
		mEditais.addColumn("Data Início");
		mEditais.addColumn("Número Fim");
		mEditais.addColumn("Inscrições");
		mEditais.addColumn("Resultado");
		
		ArrayList<EditalDeMonitoria> editais = getCentral().getTodosOsEditais();
		for(EditalDeMonitoria edital: editais) {
			Object[] linha = new Object[4];
			linha[0] = edital.getNumeroEdital();
			linha[1] = edital.getDataInicio();
			linha[2] = edital.getDataFim();
			try {
				linha[3] = edital.status();
			} catch (InscricoesFinalizadaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InscricoesNaoAbertasException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mEditais.addRow(linha);
		}

		// Torna todas as células não editáveis
		JTable tableEditais = new JTable(mEditais) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//permite apenas uma seleção
		tableEditais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemTabela = new JScrollPane(tableEditais);
		rolagemTabela.setBounds(250, 300, 400, 300);
		add(rolagemTabela);
		
	}

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}
	private void adicionarMenuBarAluno() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}
	
	private void adicionarButtons() {
		
		JButton bVisualizar = FabricaJButton.criarJButton("Visualizar", 350, 630, 200, 30, Color.GREEN, Color.WHITE, 12);
		add(bVisualizar);
		
	}

	private void adicionarIcones() {
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 330, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
	public static void main(String[] args) {
		TelaVisualizarEditais t = new TelaVisualizarEditais();
	}
}
