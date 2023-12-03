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
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Classes.Aluno;
import Classes.Coordenador;
import Classes.Sexo;
import Excecoes.AlunoJaMatriculadoException;
import Excecoes.CamposVaziosException;
import Excecoes.EmailInvalidoException;
import Excecoes.EmailJaCadastradoException;
import Excecoes.SenhaMuitoPequenaException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.Coordenador.TelaTodosOsAlunos;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJComboBox;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEditarInformacoesAluno extends TelaPadrao{

	private JTextField tNome;
	private JTextField tEmail;
	private JTextField tNovoEmail;
	private JPasswordField tSenha;
	private JPasswordField tNovaSenha;
	private JFormattedTextField fMatricula;
	private JComboBox<String> cGenero;
	private Aluno aluno;
	String[] opcoes = {"Masculino","Feminino"};

	public TelaEditarInformacoesAluno(Aluno aluno) {
		super("EDITAR INFORMAÇÕES");
		getContentPane().setBackground(Color.BLACK);
		this.aluno = aluno;
		configurarComponentes();
		setVisible(true);
		
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarComboBox();
		adicionarButtons();
		adicionarCheckBoxes();
		adicionarIcones();
		preencherCampos();
		if (TelaPadrao.getUsuario() instanceof Coordenador) {
			adicionarMenuBar();
		}
		else {
			adicionarMenuBarAluno();
		}
	}
	public void preencherCampos() {
		tNome.setText(aluno.getNome());
		fMatricula.setText(aluno.getMatricula());
		tEmail.setText(aluno.getEmail());
		tSenha.setText(aluno.getSenha());
		if(aluno.getSexo() == Sexo.MASCULINO) {
			cGenero.setSelectedItem(opcoes[0]);	
		}else {
			cGenero.setSelectedItem(opcoes[1]);
		}
		
	}
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}
	private void adicionarMenuBarAluno() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("EDITAR DADOS", 380, 120, 240, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("ALUNO", 380, 160, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNome = FabricaJLabel.criarJLabel("Nome Completo", 292, 225, 100, 30, Color.BLACK, 12);
		add(lNome);

		JLabel lMatricula = FabricaJLabel.criarJLabel("Matrícula", 292, 285, 70, 30, Color.BLACK, 12);
		add(lMatricula);

		JLabel lGenero = FabricaJLabel.criarJLabel("Gênero", 453, 285, 70, 30, Color.BLACK, 12);
		add(lGenero);

		JLabel lEmail = FabricaJLabel.criarJLabel("E-mail atual", 292, 345, 150, 30, Color.BLACK, 12);
		add(lEmail);

		JLabel lNovoEmail = FabricaJLabel.criarJLabel("Novo endereço de e-mail", 292, 405, 200, 30, Color.BLACK, 12);
		add(lNovoEmail);

		JLabel lSenha = FabricaJLabel.criarJLabel("Senha atual", 292, 465, 150, 30, Color.BLACK, 12);
		add(lSenha);

		JLabel lNovaSenha = FabricaJLabel.criarJLabel("Nova senha", 292, 545, 200, 30, Color.BLACK, 12);
		add(lNovaSenha);
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
		if (getUsuario() instanceof Aluno) {
			fMatricula.setEditable(false);
		}
		add(fMatricula);

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

		tNovaSenha = FabricaJTextField.criarJPasswordField(325, 570, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tNovaSenha);
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
				if (getUsuario() instanceof Aluno) {
					new TelaHomeAluno();
				}else {
					new TelaTodosOsAlunos();
				}
			}
		});

		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 452, 640, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		bSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					getUtil().editarAluno(getCentral(), aluno, tNome.getText(), tNovoEmail.getText(), tNovaSenha.getText(),
							fMatricula.getText(), opcoes[cGenero.getSelectedIndex()]);
					getDados().salvarCentral(getCentral(), "central.xml");
					FabricaJOptionPane.criarMsgValido("Edição feita com sucesso!");
					dispose();
					if (getUsuario() instanceof Aluno) {
						new TelaHomeAluno();
					}else {
						new TelaTodosOsAlunos();
					}
					
				} catch (EmailInvalidoException | SenhaMuitoPequenaException | AlunoJaMatriculadoException
						| EmailJaCadastradoException | CamposVaziosException e1) {
					FabricaJOptionPane.criarMsgErro(e1.getMessage());		
				}

			}

		});

	}
	
	private void adicionarCheckBoxes(){
		
		JCheckBox boxVisualizarSenhaAtual = new JCheckBox("Visualizar Senha Atual");
	    boxVisualizarSenhaAtual.setBounds(320, 520, 150, 30);
	    boxVisualizarSenhaAtual.addActionListener(new ActionListener() {
	    	
	        public void actionPerformed(ActionEvent e) {
	            if (boxVisualizarSenhaAtual.isSelected()) {
	                tSenha.setEchoChar((char) 0); 
	            } else {
	                tSenha.setEchoChar('*'); 
	            }
	        }
	    });
	    add(boxVisualizarSenhaAtual);

	    JCheckBox checkBoxVisualizarNovaSenha = new JCheckBox("Visualizar Nova Senha");
	    checkBoxVisualizarNovaSenha.setBounds(320, 600, 200, 30);
	    checkBoxVisualizarNovaSenha.addActionListener(new ActionListener() {
	    	
	        public void actionPerformed(ActionEvent e) {
	            if (checkBoxVisualizarNovaSenha.isSelected()) {
	                tNovaSenha.setEchoChar((char) 0);
	            } else {
	                tNovaSenha.setEchoChar('*');
	            }
	        }
	    });
	    add(checkBoxVisualizarNovaSenha);
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

		JLabel iconeNovaSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 283, 570, 50, 30);
		add(iconeNovaSenha);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 290, 110, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}	

}