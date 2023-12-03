package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Classes.Aluno;
import Classes.Coordenador;
import Classes.Sexo;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.Aluno.TelaHomeAluno;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJComboBox;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaVisualizarAluno extends TelaPadrao{
	private Aluno aluno;
	private JTextField tNome;
	private JTextField tEmail;
	private JPasswordField tSenha;
	private JTextField tMatricula;
	private JComboBox<String> cGenero;
	String[] opcoes = {"Masculino","Feminino"};
	
	public TelaVisualizarAluno(Aluno aluno){
		super("DADOS ALUNO");
		this.aluno = aluno;
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarMenuBar();
		adicionarComboBox();
		adicionarButtons();
		adicionarcheckBox();
		adicionarIcones();
		preencherCampos();
		
	}
	

	public void preencherCampos() {
		tNome.setText(aluno.getNome());
		tMatricula.setText(aluno.getMatricula());
		tEmail.setText(aluno.getEmail());
		tSenha.setText(aluno.getSenha());
		if(aluno.getSexo() == Sexo.MASCULINO) {
			cGenero.setSelectedItem(opcoes[0]);	
		}else {
			cGenero.setSelectedItem(opcoes[1]);
		}
		
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("DADOS", 430, 190, 200, 30, Color.BLACK, 30);
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
		tNome = FabricaJTextField.criarJTextField(325, 310, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNome.setEditable(false);
		add(tNome);
		
		tMatricula = FabricaJTextField.criarJTextField(325, 370, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tMatricula.setEditable(false);
		add(tMatricula);
		
		tEmail = FabricaJTextField.criarJTextField(325, 430, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tEmail.setEditable(false);
		add(tEmail);	
		
		tSenha = FabricaJTextField.criarJPasswordField(325, 490, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tSenha.setEditable(false);
		add(tSenha);

	}
	
	private void adicionarComboBox() {
		cGenero = FabricaJComboBox.criarJComboBpx(opcoes, 487, 370, 120, 30, Color.WHITE, Color.BLACK, 12);
		cGenero.setEnabled(false);
		add(cGenero);
	}
	

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 560, 313, 30, Color.GREEN, Color.WHITE, 12);
		bVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (getUsuario() instanceof Coordenador) {
					new TelaTodosOsAlunos();
				}else {
					new TelaHomeAluno();
				}
				
			}
		});
		add(bVoltar);
	}
	
	private void adicionarcheckBox() {
		JCheckBox boxVisualizarSenha = new JCheckBox("Visualizar Senha");
	    boxVisualizarSenha.setBounds(320, 520, 145, 30);
	    boxVisualizarSenha.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (boxVisualizarSenha.isSelected()) {
	                tSenha.setEchoChar((char) 0); // Exibe os caracteres
	            } else {
	                tSenha.setEchoChar('*'); // Oculta os caracteres
	            }
	        }
	    });
	    add(boxVisualizarSenha);
	}
	
	private void adicionarMenuBar() {
		JMenuBar menuBar =	FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(menuBar);
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
	
}