package Telas.Coordenador;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.commons.mail.EmailException;

import Classes.Mensageiro;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEnviarEmail extends TelaPadrao{
	private JTextField tEmailAluno;
	private JTextField tTituloEmail;
	private JTextArea aMensagemEmail;
	private String email;
	
	public TelaEnviarEmail(String email) {
		super("ENVIAR EMAIL");
		this.email = email;
		setSize(400, 400);
		setLocationRelativeTo(null);
		configurarComponentes();
		setVisible(true);
	}

	
	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarJTextArea();
		adicionarButtons();
		adicionarIcones();
	}
	


	private void adicionarLabels() {
		
		JLabel lEmailDestinatario = FabricaJLabel.criarJLabel("Para:", 35, 65, 200, 30, Color.BLACK, 12);
		add(lEmailDestinatario);
		
		JLabel lTitulo = FabricaJLabel.criarJLabel("Título Email:", 35, 120, 200, 30, Color.BLACK, 12);
		add(lTitulo);
		
		JLabel lMensagem = FabricaJLabel.criarJLabel("Mensagem Email:", 35, 175, 200, 30, Color.BLACK, 12);
		add(lMensagem);
		
	}
	
	private void adicionarTextFields() {
		
		tEmailAluno = FabricaJTextField.criarJTextField(60, 90, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmailAluno.setText(email);
		tEmailAluno.setEditable(false);
		add(tEmailAluno);
		
		tTituloEmail = FabricaJTextField.criarJTextField(60, 145, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tTituloEmail);
		
		
	}
	
	private void adicionarJTextArea() {
		aMensagemEmail = new JTextArea();
        aMensagemEmail.setBorder(new LineBorder(Color.BLACK));
        aMensagemEmail.setLineWrap(true);
        aMensagemEmail.setWrapStyleWord(true);
        JScrollPane tela = new JScrollPane(aMensagemEmail);
        tela.setBounds(35, 200, 308, 60);
		add(tela);
	}
	
	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 30, 285, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
		JButton bEnviarEmail = FabricaJButton.criarJButton("Enviar Email", 189, 285, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bEnviarEmail);
		bEnviarEmail.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tTituloEmail.getText().isBlank() || aMensagemEmail.getText().isBlank()) {
					FabricaJOptionPane.criarMsgErro("Preencha os campos vazios.");
				}else {
					try {
						Mensageiro.enviarEmail(email, tTituloEmail.getText(), aMensagemEmail.getText());
						FabricaJOptionPane.criarMsgValido("Email enviado com sucesso!");
						dispose();
					} catch (EmailException e1) {
						FabricaJOptionPane.criarMsgErro("Erro ao enviar Email!");
					}
				}
			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeEmail = FabricaIcones.criarIcone(FabricaImagens.EMAIL, 20, 90, 50, 30);
		add(iconeEmail);
		
		JLabel iconeTitulo = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 20, 145, 50, 30);
		add(iconeTitulo);
		
	}
}
