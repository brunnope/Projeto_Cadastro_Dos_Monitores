package Telas.Aluno;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;

public class TelaHomeAluno extends TelaPadrao{

	public TelaHomeAluno() {
		super("TELA INICIAL - ALUNO");
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
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno();
		setJMenuBar(mOpcoes);
	}
	
	private void adicionarButtons() {
		
		JButton bVisualizar = FabricaJButton.criarJButton("Visualizar Editais", 325, 425, 250, 40, Color.GREEN, Color.WHITE, 12);
		add(bVisualizar);
		
		
		JButton bEditarInformacoes = FabricaJButton.criarJButton("Editar Informações", 325, 475, 250, 40, Color.GREEN, Color.WHITE, 12);
		add(bEditarInformacoes);
	}

	private void adicionarIcones() {
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 170, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
	
	public static void main(String[] args) {
		TelaHomeAluno t = new TelaHomeAluno();
	}

}
