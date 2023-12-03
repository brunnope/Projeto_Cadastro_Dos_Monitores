package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.TelaVisualizarEditais;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;

public class TelaHomeCoordenador extends TelaPadrao{

	public TelaHomeCoordenador() {
		super("TELA INICIAL - COORDENADOR");
		configurarComponentes();
		setVisible(true);
		
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarMenuBar();
		adicionarButtons();
		adicionarIcones();
	}
	

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("MENU", 440, 200, 200, 30, Color.BLACK, 30);
		add(lTitulo);
	}

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}
	
	private void adicionarButtons() {
		JButton bCadastrar = FabricaJButton.criarJButton("Cadastrar Novo Edital", 325, 385, 250, 40, Color.GREEN, Color.WHITE, 12);
		add(bCadastrar);
		
		bCadastrar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroEdital();
			}
		});
		
		JButton bVisualizar = FabricaJButton.criarJButton("Visualizar Editais", 325, 435, 250, 40, Color.GREEN, Color.WHITE, 12);
		add(bVisualizar);
		bVisualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarEditais();
			}
		});
		
		JButton bTodosAlunos = FabricaJButton.criarJButton("Todos os Alunos", 325, 485, 250, 40, Color.GREEN, Color.WHITE, 12);
		add(bTodosAlunos);
		bTodosAlunos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaTodosOsAlunos();
			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 170, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
}
