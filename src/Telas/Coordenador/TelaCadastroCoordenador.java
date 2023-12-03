package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Excecoes.EmailDiferenteException;
import Excecoes.EmailInvalidoException;
import Excecoes.SenhaDiferenteException;
import Excecoes.SenhaMuitoPequenaException;
import Telas.FabricaImagens;
import Telas.TelaLogin;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJOptionPane;
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
		adicionarButtons();
		adicionarIcones();
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 380, 150, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("COORDENADOR", 380, 190, 245, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNome = FabricaJLabel.criarJLabel("Nome Completo", 292, 285, 100, 30, Color.BLACK, 12);
		add(lNome);

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
		tNome = FabricaJTextField.criarJTextField(325, 310, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNome.setToolTipText("Escreva seu nome completo!");
		add(tNome);

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

	private void adicionarButtons() {
		JButton bCadastrar = FabricaJButton.criarJButton("Cadastrar", 372, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bCadastrar);
		bCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tNome.getText().isBlank() || tEmail.getText().isBlank() || tConfirmaEmail.getText().isBlank() || tSenha.getText().isBlank() || tConfirmaSenha.getText().isBlank()) {
					FabricaJOptionPane.criarMsgErro("Preencha os campos vazios");					
				}else {
					try {
						getCentral().adicionarCoordenador(getUtil().cadastrarCoordenador(tNome.getText().trim(), tEmail.getText().trim(), tConfirmaEmail.getText().trim(),
								tSenha.getText(), tConfirmaSenha.getText()));
						getDados().salvarCentral(getCentral(), "central.xml");
						FabricaJOptionPane.criarMsgValido("Cadastrado realizado com sucesso!");
						dispose();
						new TelaLogin();
					} catch (EmailDiferenteException | SenhaDiferenteException | EmailInvalidoException | SenhaMuitoPequenaException e1) {
						FabricaJOptionPane.criarMsgErro(e1.getMessage());
					}
				}			
			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeNome = FabricaIcones.criarIcone(FabricaImagens.LOGIN, 283, 310, 50, 30);
		add(iconeNome);

		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 370, 50, 30);
		add(iconeEmail);

		JLabel iconeConfirmacaoEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 430, 50, 30);
		add(iconeConfirmacaoEmail);

		JLabel iconeSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 490, 50, 30);
		add(iconeSenha);

		JLabel iconeConfirmacaoSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 550, 50, 30);
		add(iconeConfirmacaoSenha);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 290, 130, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
}
