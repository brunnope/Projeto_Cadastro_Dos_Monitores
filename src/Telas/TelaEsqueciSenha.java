package Telas;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEsqueciSenha extends TelaPadrao{
	
	public TelaEsqueciSenha() {
		super("RECUPERAR SENHA");
		getContentPane().setBackground(Color.BLACK);
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarButtons();
		adicionarIcones();
	
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("RECUPERAR", 360, 240, 300, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("SENHA", 360, 280, 300, 30, Color.BLACK, 30);
		add(lTitulo);
		
		JLabel lEmail = FabricaJLabel.criarJLabel("Endereço de e-mail", 280, 370, 150, 30, Color.BLACK, 12);
		add(lEmail);
	}

	private void adicionarTextFields() {
		
		JTextField tEmail = FabricaJTextField.criarJTextField(315, 400, 300, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tEmail);
		
		
	}

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 280, 460, 165, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		
		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 450, 460, 165, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		
	}

	private void adicionarIcones() {
		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 272, 400, 50, 30);
		add(iconeEmail);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 277, 230, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
	}
	
	public static void main(String[] args) {
		TelaEsqueciSenha c = new TelaEsqueciSenha();
	}
}
