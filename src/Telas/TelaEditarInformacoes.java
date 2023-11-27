package Telas;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJComboBox;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEditarInformacoes extends TelaPadrao{

	public TelaEditarInformacoes(){
		super("EDITAR DADOS");
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarComboBox();
		adicionarButtons();
		adicionarIcones();
		
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("EDITAR", 430, 160, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("DADOS", 430, 200, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		JLabel lNome = FabricaJLabel.criarJLabel("Nome Completo", 292, 285, 100, 30, Color.BLACK, 12);
		add(lNome);
		
		JLabel lMatricula = FabricaJLabel.criarJLabel("Matrícula", 292, 345, 70, 30, Color.BLACK, 12);
		add(lMatricula);
		
		JLabel lGenero = FabricaJLabel.criarJLabel("Gênero", 453, 345, 70, 30, Color.BLACK, 12);
		add(lGenero);
		
		JLabel lEmail = FabricaJLabel.criarJLabel("Endereço de e-mail", 292, 405, 150, 30, Color.BLACK, 12);
		add(lEmail);
		
		JLabel lSenha = FabricaJLabel.criarJLabel("Senha", 292, 465, 150, 30, Color.BLACK, 12);
		add(lSenha);
	}
	
	private void adicionarTextFields() {
		JTextField tNome = FabricaJTextField.criarJTextField(325, 310, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNome.setToolTipText("Escreva seu nome completo!");
		add(tNome);
		
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("############");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JFormattedTextField fMatricula = FabricaJTextField.criarJFormattedTextField(mascara, 325, 370, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fMatricula.setToolTipText("Apenas números");
		add(fMatricula);
		
		JTextField tEmail = FabricaJTextField.criarJTextField(325, 430, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tEmail);	
		
		JTextField tSenha = FabricaJTextField.criarJPasswordField(325, 490, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tSenha.setToolTipText("Exemplo: brunno123");
		add(tSenha);

	}
	
	private void adicionarComboBox() {
		String[] opcoes = {"Masculino","Feminino"};
		JComboBox<String> cGenero = FabricaJComboBox.criarJComboBpx(opcoes, 487, 370, 120, 30, Color.WHITE, Color.BLACK, 12);
		add(cGenero);
	}
	

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 560, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		
		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 455, 560, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
	}

	private void adicionarIcones() {
		JLabel iconeNome = FabricaIcones.criarIcone(FabricaImagens.LOGIN, 283, 310, 50, 30);
		add(iconeNome);
		
		JLabel iconeMatricula = FabricaIcones.criarIcone(FabricaImagens.MATRICULA, 283, 370, 50, 30);
		add(iconeMatricula);
		
		JLabel iconeGenero = FabricaIcones.criarIcone(FabricaImagens.GENERO, 445, 370, 50, 30);
		add(iconeGenero);
		
		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 430, 50, 30);
		add(iconeEmail);
		
		JLabel iconeSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 490, 50, 30);
		add(iconeSenha);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 150, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
	
	public static void main(String[] args) {
		TelaEditarInformacoes t = new TelaEditarInformacoes();
	}


}
