package Telas.Aluno;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Aluno;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Classes.Inscricao;
import Excecoes.EditalNaoEncontradoException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;

public class TelaVisualizarResultado extends TelaPadrao{

	JTable tableDisciplinas;
	private EditalDeMonitoria edital;
	
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
				linha[2] = inscricao.getNotaFinal();
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
		//permite apenas uma seleção
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(295, 250, 315, 350);
		add(rolagemTabela);
		
	}
	
	private void adicionarButtons() {
		JButton bDesistir = FabricaJButton.criarJButton("Desistir", 350, 630, 200, 30, Color.GREEN, Color.WHITE, 12);
		bDesistir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		add(bDesistir);
	}

	private void adicionarIcones() {
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 330, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
		
	}

}
