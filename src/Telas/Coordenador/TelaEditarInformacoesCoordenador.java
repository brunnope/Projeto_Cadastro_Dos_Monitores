package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

import Classes.Coordenador;
import Excecoes.CamposVaziosException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import Excecoes.SenhaMuitoPequenaException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.Aluno.TelaHomeAluno;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEditarInformacoesCoordenador extends TelaPadrao{
	private JTextField tNome;
	private JTextField tEmail;
	private JTextField tNovoEmail;
	private JTextField tSenha;
	private JTextField tNovaSenha;
	private Coordenador coordenador;

	public TelaEditarInformacoesCoordenador() {
		super("EDITAR INFORMAÇÕES");
		coordenador = (Coordenador) getUsuario();
		configurarComponentes();
		setVisible(true);
		
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarButtons();
		adicionarMenuBar();
		adicionarIcones();
		preencherCampos();
	}
	
	public void preencherCampos() {
		tNome.setText(coordenador.getNome());
		tEmail.setText(coordenador.getEmail());
		tSenha.setText(coordenador.getSenha());
	}
	
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("EDITAR DADOS", 380, 120, 240, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("COORDENADOR", 380, 160, 300, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNome = FabricaJLabel.criarJLabel("Nome Completo", 292, 285, 100, 30, Color.BLACK, 12);
		add(lNome);

		JLabel lEmail = FabricaJLabel.criarJLabel("E-mail atual", 292, 345, 150, 30, Color.BLACK, 12);
		add(lEmail);

		JLabel lNovoEmail = FabricaJLabel.criarJLabel("Novo endereço de e-mail", 292, 405, 200, 30, Color.BLACK, 12);
		add(lNovoEmail);

		JLabel lSenha = FabricaJLabel.criarJLabel("Senha atual", 292, 465, 150, 30, Color.BLACK, 12);
		add(lSenha);

		JLabel lNovaSenha = FabricaJLabel.criarJLabel("Nova senha", 292, 525, 200, 30, Color.BLACK, 12);
		add(lNovaSenha);
	}
	
	private void adicionarTextFields() {
		tNome = FabricaJTextField.criarJTextField(325, 310, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNome.setToolTipText("Escreva seu nome completo!");
		add(tNome);

		tEmail = FabricaJTextField.criarJTextField(325, 370, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setToolTipText("Este é seu e-mail atual");
		tEmail.setEditable(false);
		add(tEmail);

		tNovoEmail = FabricaJTextField.criarJTextField(325, 430, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tNovoEmail);

		tSenha = FabricaJTextField.criarJPasswordField(325, 490, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tSenha.setToolTipText("Esta é sua senha atual");
		
		tSenha.setEditable(false);
		add(tSenha);

		tNovaSenha = FabricaJTextField.criarJPasswordField(325, 550, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tNovaSenha);
	}


	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaHomeCoordenador();
			}
		});

		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 452, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		bSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					getUtil().editarCoordenador(getCentral(), coordenador, tNome.getText(), tNovoEmail.getText(), tNovaSenha.getText());
					getDados().salvarCentral(getCentral(), "central.xml");
					FabricaJOptionPane.criarMsgValido("Edição feita com sucesso!");
					dispose();
					new TelaHomeCoordenador();
					
				} catch (EmailInvalidoException | SenhaMuitoPequenaException | EmailJaCadastradoException | CamposVaziosException e1) {
					FabricaJOptionPane.criarMsgErro(e1.getMessage());		
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

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 290, 110, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
}
