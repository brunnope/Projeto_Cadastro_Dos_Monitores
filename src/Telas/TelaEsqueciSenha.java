package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailException;

import Excecoes.EmailNaoEncontradoException;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEsqueciSenha extends TelaPadrao{
	JTextField tEmail;
	
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
		
		tEmail = FabricaJTextField.criarJTextField(315, 400, 300, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tEmail);
		
		
	}

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 280, 460, 165, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLogin();
			}
		});
		
		JButton bRecuperar = FabricaJButton.criarJButton("Recuperar", 450, 460, 165, 30, Color.GREEN, Color.WHITE, 12);
		add(bRecuperar);
		bRecuperar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tEmail.getText().isBlank()) {
					FabricaJOptionPane.criarMsgErro("Prencha os campos vazios!");
				}
				else{
					try {
					getUtil().recuperarSenhaPorEmail(getCentral(), tEmail.getText());
					FabricaJOptionPane.criarMsg("Senha Enviada por Email!");
					dispose();
					new TelaLogin();
					} catch (EmailNaoEncontradoException | EmailException e1) {
					FabricaJOptionPane.criarMsgErro(e1.getMessage());
					}
				}
			}
		});
		
	}

	private void adicionarIcones() {
		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 272, 400, 50, 30);
		add(iconeEmail);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 277, 230, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
	}
	
	public JTextField getTEmail() {
		return tEmail;
	}
}
