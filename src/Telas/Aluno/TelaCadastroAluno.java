package Telas.Aluno;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Excecoes.AlunoJaMatriculadoException;
import Excecoes.CamposVaziosException;
import Excecoes.EmailDiferenteException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import Excecoes.SenhaDiferenteException;
import Excecoes.SenhaMuitoPequenaException;
import Telas.FabricaImagens;
import Telas.TelaLogin;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJComboBox;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaCadastroAluno extends TelaPadrao{
	
	private JTextField tNome;
	private JTextField tEmail;
	private JTextField tConfirmaEmail;
	private JPasswordField tSenha;
	private JPasswordField tConfirmaSenha;
	private JFormattedTextField fMatricula;
	private JComboBox<String> cGenero;
	String[] opcoes = {"Masculino","Feminino"};
	
	public TelaCadastroAluno() {
		super("CADASTRO ALUNO");
		getContentPane().setBackground(Color.BLACK);
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarComboBox();
		adicionarCheckBoxes();
		adicionarButtons();
		adicionarIcones();
		
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 380, 120, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("ALUNO", 380, 160, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		JLabel lNome = FabricaJLabel.criarJLabel("Nome Completo", 292, 225, 100, 30, Color.BLACK, 12);
		add(lNome);
		
		JLabel lMatricula = FabricaJLabel.criarJLabel("Matrícula", 292, 285, 70, 30, Color.BLACK, 12);
		add(lMatricula);
		
		JLabel lGenero = FabricaJLabel.criarJLabel("Gênero", 453, 285, 70, 30, Color.BLACK, 12);
		add(lGenero);
		
		JLabel lEmail = FabricaJLabel.criarJLabel("Endereço de e-mail", 292, 345, 150, 30, Color.BLACK, 12);
		add(lEmail);
		
		JLabel lConfirmacaoEmail = FabricaJLabel.criarJLabel("Confirme seu endereço de e-mail", 292, 405, 200, 30, Color.BLACK, 12);
		add(lConfirmacaoEmail);
		
		JLabel lSenha = FabricaJLabel.criarJLabel("Senha", 292, 465, 150, 30, Color.BLACK, 12);
		add(lSenha);
		
		JLabel lConfirmacaoSenha = FabricaJLabel.criarJLabel("Confirme sua senha", 292, 545, 200, 30, Color.BLACK, 12);
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
		fMatricula = FabricaJTextField.criarJFormattedTextField(mascara, 325, 310, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fMatricula.setToolTipText("Apenas números (12)");
		add(fMatricula);
		
		tEmail = FabricaJTextField.criarJTextField(325, 370, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tEmail);
		
		tConfirmaEmail = FabricaJTextField.criarJTextField(325, 430, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tConfirmaEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tConfirmaEmail);
		
		
		tSenha = FabricaJTextField.criarJPasswordField(325, 490, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tSenha.setToolTipText("Exemplo: brunno123");
		add(tSenha);
		
		tConfirmaSenha = FabricaJTextField.criarJPasswordField(325, 570, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tConfirmaSenha.setToolTipText("Exemplo: brunno123");
		add(tConfirmaSenha);
	}
	
	private void adicionarComboBox() {
		cGenero = FabricaJComboBox.criarJComboBpx(opcoes, 487, 310, 120, 30, Color.WHITE, Color.BLACK, 12);
		add(cGenero);
	}
	

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 640, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLogin();
			}
		});
		
		JButton bCadastrar = FabricaJButton.criarJButton("Cadastrar", 452, 640, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bCadastrar);
		bCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
					try {
						getCentral().adicionarAluno(getUtil().cadastrarAluno(tNome.getText(), tEmail.getText(), tConfirmaEmail.getText(),
								tSenha.getText(), tConfirmaSenha.getText(), fMatricula.getText(), opcoes[cGenero.getSelectedIndex()]));
						getDados().salvarCentral(getCentral(), "central.xml");
						FabricaJOptionPane.criarMsgValido("Cadastrado realizado com sucesso!");
						dispose();
						new TelaLogin();
					} catch (EmailDiferenteException | SenhaDiferenteException | EmailInvalidoException | SenhaMuitoPequenaException | AlunoJaMatriculadoException | EmailJaCadastradoException | CamposVaziosException e1) {
						FabricaJOptionPane.criarMsgErro(e1.getMessage());		
					}
					
					
				}
			
		});
	
	}
	
	private void adicionarCheckBoxes(){
		
		JCheckBox boxSenha = new JCheckBox("Visualizar Senha");
	    boxSenha.setBounds(320, 520, 150, 30);
	    boxSenha.addActionListener(new ActionListener() {
	    	
	        public void actionPerformed(ActionEvent e) {
	            if (boxSenha.isSelected()) {
	                tSenha.setEchoChar((char) 0); 
	            } else {
	                tSenha.setEchoChar('*'); 
	            }
	        }
	    });
	    add(boxSenha);

	    JCheckBox boxConfirmaSenha = new JCheckBox("Visualizar Confirmação de Senha");
	    boxConfirmaSenha.setBounds(320, 600, 250, 30);
	    boxConfirmaSenha.addActionListener(new ActionListener() {
	    	
	        public void actionPerformed(ActionEvent e) {
	            if (boxConfirmaSenha.isSelected()) {
	            	tConfirmaSenha.setEchoChar((char) 0);
	            } else {
	            	tConfirmaSenha.setEchoChar('*');
	            }
	        }
	    });
	    add(boxConfirmaSenha);
	}

	private void adicionarIcones() {
		JLabel iconeNome = FabricaIcones.criarIcone(FabricaImagens.LOGIN, 283, 250, 50, 30);
		add(iconeNome);
		
		JLabel iconeMatricula = FabricaIcones.criarIcone(FabricaImagens.MATRICULA, 283, 310, 50, 30);
		add(iconeMatricula);
		
		JLabel iconeGenero = FabricaIcones.criarIcone(FabricaImagens.GENERO, 445, 310, 50, 30);
		add(iconeGenero);
		
		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 370, 50, 30);
		add(iconeEmail);
		
		JLabel iconeConfirmacaoEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 283, 430, 50, 30);
		add(iconeConfirmacaoEmail);
		
		JLabel iconeSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 490, 50, 30);
		add(iconeSenha);
		
		JLabel iconeConfirmacaoSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 570, 50, 30);
		add(iconeConfirmacaoSenha);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 290, 110, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}	
}
