package Telas;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Telas.FabricaComponentes.*;

public class TelaLogin extends TelaPadrao{

	public TelaLogin() {
		super("LOGIN");
		
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
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 420, 210, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("MONITORES", 420, 250, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		
		JLabel lLogin = FabricaJLabel.criarJLabel("Login", 335, 350, 50, 30, Color.BLACK, 12);
		add(lLogin);
		
		JLabel lSenha = FabricaJLabel.criarJLabel("Senha", 335, 405, 50, 30, Color.BLACK, 12);
		add(lSenha);
		
	}

	private void adicionarTextFields() {
		JTextField tLogin = FabricaJTextField.criarJTextField(370, 375, 200, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tLogin.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tLogin);
		
		
		JTextField tSenha = FabricaJTextField.criarJPasswordField(370, 430, 200, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		
		tSenha.setToolTipText("Exemplo: brunno123");
		add(tSenha);
		
		
	}
	
	
	private void adicionarButtons() {
		JButton bLogin = FabricaJButton.criarJButton("Login", 336, 470, 234, 30, Color.GREEN, Color.WHITE, 12);
		add(bLogin);
		
		JButton bEsqueciSenha = FabricaJButton.criarJButton("Esqueci a Senha", 336, 530, 115, 30, Color.GREEN, Color.WHITE, 12);
		add(bEsqueciSenha);
		
		JButton bCadastrar = FabricaJButton.criarJButton("Cadastrar-se", 454, 530, 115, 30, Color.GREEN, Color.WHITE, 12);
		add(bCadastrar);
	}
	
	private void adicionarIcones() {
		
		
		JLabel iconeLogin = FabricaIcones.criarIcone(FabricaImagens.LOGIN, 328, 375, 50, 30);
		add(iconeLogin);
		
		JLabel iconeSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 328, 430, 50, 30);
		add(iconeSenha);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 328, 200, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
	
	public static void main(String[] args) {
		TelaLogin t = new TelaLogin();
	}
}
