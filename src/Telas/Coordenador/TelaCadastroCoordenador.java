package Telas.Coordenador;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Excecoes.AlunoJaMatriculadoException;
import Excecoes.EmailDiferenteException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import Excecoes.SenhaDiferenteException;
import Excecoes.SenhaMuitoPequenaException;
import Persistencia.Persistencia;
import Telas.FabricaImagens;
import Telas.TelaLogin;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJComboBox;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaCadastroCoordenador extends TelaPadrao{
	private JTextField tNome;
	private JTextField tEmail;
	private JTextField tConfirmaEmail;
	private JTextField tSenha;
	private JTextField tConfirmaSenha;
	public TelaCadastroCoordenador() {
		super("CADASTRO COORDENADOR");
		getContentPane().setBackground(Color.BLACK);
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
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 380, 130, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("COORDENADOR", 380, 170, 245, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNome = FabricaJLabel.criarJLabel("Nome Completo", 292, 225, 100, 30, Color.BLACK, 12);
		add(lNome);

		JLabel lGenero = FabricaJLabel.criarJLabel("Gênero", 292, 285, 70, 30, Color.BLACK, 12);
		add(lGenero);

		JLabel lEmail = FabricaJLabel.criarJLabel("Endereço de e-mail", 292, 345, 150, 30, Color.BLACK, 12);
		add(lEmail);

		JLabel lConfirmacaoEmail = FabricaJLabel.criarJLabel("Confirme seu endereço de e-mail", 292, 405, 200, 30, Color.BLACK, 12);
		add(lConfirmacaoEmail);

		JLabel lSenha = FabricaJLabel.criarJLabel("Senha", 292, 465, 150, 30, Color.BLACK, 12);
		add(lSenha);

		JLabel lConfirmacaoSenha = FabricaJLabel.criarJLabel("Confirme sua senha", 292, 525, 200, 30, Color.BLACK, 12);
		add(lConfirmacaoSenha);
	}

	private void adicionarTextFields() {
		tNome = FabricaJTextField.criarJTextField(325, 250, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNome.setToolTipText("Escreva seu nome completo!");
		add(tNome);

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("############");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		tEmail = FabricaJTextField.criarJTextField(325, 370, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tEmail);

		tConfirmaEmail = FabricaJTextField.criarJTextField(325, 430, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tConfirmaEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tConfirmaEmail);


		tSenha = FabricaJTextField.criarJPasswordField(325, 490, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tSenha.setToolTipText("Exemplo: brunno123");
		add(tSenha);

		tConfirmaSenha = FabricaJTextField.criarJPasswordField(325, 550, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tConfirmaSenha.setToolTipText("Exemplo: brunno123");
		add(tConfirmaSenha);
	}

	private void adicionarComboBox() {
		String[] opcoes = {"Masculino","Feminino"};
		JComboBox<String> cGenero = FabricaJComboBox.criarJComboBpx(opcoes, 325, 310, 120, 30, Color.WHITE, Color.BLACK, 12);
		add(cGenero);
	}


	private void adicionarButtons() {
		JButton bCadastrar = FabricaJButton.criarJButton("Cadastrar", 372, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bCadastrar);
		bCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
					try {
						getCentral().adicionarCoordenador(getUtil().cadastrarCoordenador(tNome.getText(), tEmail.getText(), tConfirmaEmail.getText(),
								tSenha.getText(), tConfirmaSenha.getText()));
						getDados().salvarCentral(getCentral(), "central.xml");
						dispose();
						TelaLogin t = new TelaLogin();
					} catch (EmailDiferenteException | SenhaDiferenteException | EmailInvalidoException | SenhaMuitoPequenaException e1) {
						JOptionPane.showMessageDialog(bCadastrar, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);		
					}
					
					
				}
			
		});
	}

	private void adicionarIcones() {
		JLabel iconeNome = FabricaIcones.criarIcone(FabricaImagens.LOGIN, 283, 250, 50, 30);
		add(iconeNome);

		JLabel iconeGenero = FabricaIcones.criarIcone(FabricaImagens.GENERO, 283, 310, 50, 30);
		add(iconeGenero);

		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 370, 50, 30);
		add(iconeEmail);

		JLabel iconeConfirmacaoEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 430, 50, 30);
		add(iconeConfirmacaoEmail);

		JLabel iconeSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 490, 50, 30);
		add(iconeSenha);

		JLabel iconeConfirmacaoSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 550, 50, 30);
		add(iconeConfirmacaoSenha);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 290, 110, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}

	public static void main(String[] args) {
		TelaCadastroCoordenador t = new TelaCadastroCoordenador();
	}


}
