package Telas.Coordenador;

import java.awt.Color;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaCadastroEdital extends TelaPadrao{
	
	public TelaCadastroEdital() {
		super("CADASTRO EDITAL");
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTextFields();
		adicionarButtons();
		adicionarIcones();
	}
	
	private void adicionarMenuBar() 
	{
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 440, 160, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("EDITAL", 440, 200, 180, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNumEdital = FabricaJLabel.criarJLabel("Número Edital", 297, 280, 100, 30, Color.BLACK, 12);
		add(lNumEdital);
		
		
		JLabel lDataInicio = FabricaJLabel.criarJLabel("Data de Inicio", 295, 345, 150, 30, Color.BLACK, 12);
		add(lDataInicio);
		
		JLabel lDataFim = FabricaJLabel.criarJLabel("Data Final", 458, 345, 150, 30, Color.BLACK, 12);
		add(lDataFim);
		
		JLabel lDisciplina = FabricaJLabel.criarJLabel("Disciplina", 297, 405, 200, 30, Color.BLACK, 12);
		add(lDisciplina);
		
		JLabel lVagasRemunerada = FabricaJLabel.criarJLabel("Vagas Remuneradas", 297, 465, 150, 30, Color.BLACK, 12);
		add(lVagasRemunerada);
		
		JLabel lVagasVoluntario = FabricaJLabel.criarJLabel("Vagas Voluntários", 458, 465, 150, 30, Color.BLACK, 12);
		add(lVagasVoluntario);
		
		JLabel lPesoCRE = FabricaJLabel.criarJLabel("Peso CRE Aluno", 297, 525, 200, 30, Color.BLACK, 12);
		add(lPesoCRE);
		
		JLabel lPesoMedia = FabricaJLabel.criarJLabel("Peso Média Aluno", 459, 525, 200, 30, Color.BLACK, 12);
		add(lPesoMedia);

	}
	
	private void adicionarTextFields() {
		JTextField tNumEdital = FabricaJTextField.criarJTextField(325, 305, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tNumEdital);
		
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JFormattedTextField fDataInicio = FabricaJTextField.criarJFormattedTextField(mascara, 325, 370, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataInicio.setToolTipText("dd/MM/yyyy");
		fDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataInicio);
		
		JFormattedTextField fDataFim = FabricaJTextField.criarJFormattedTextField(mascara, 487, 370, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataFim.setToolTipText("dd/MM/yyyy");
		fDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataFim);
		
		JTextField tDisciplina = FabricaJTextField.criarJTextField(325, 430, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tDisciplina);
		
		mascara = null;
		try {
			mascara = new MaskFormatter("##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JFormattedTextField fVagasRemuneradas = FabricaJTextField.criarJFormattedTextField(mascara, 325, 490, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasRemuneradas.setToolTipText("No máximo 2 dígitos");
		add(fVagasRemuneradas);
		
		JFormattedTextField fVagasVoluntario = FabricaJTextField.criarJFormattedTextField(mascara, 487, 490, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasVoluntario.setToolTipText("No máximo 2 dígitos");
		add(fVagasVoluntario);
		
		JFormattedTextField fPesoCRE = FabricaJTextField.criarJFormattedTextField(mascara, 325, 550, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoCRE.setToolTipText("No máximo 2 dígitos");
		add(fPesoCRE);
		
		JFormattedTextField fPesoMedia = FabricaJTextField.criarJFormattedTextField(mascara, 487, 550, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoMedia.setToolTipText("No máximo 2 dígitos");
		add(fPesoMedia);
		
	}
	
	private void adicionarButtons() {
		JButton bSalvarAdicionar = FabricaJButton.criarJButton("Adicionar mais Disciplinas", 293, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvarAdicionar);
		
		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 452, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
	}

	private void adicionarIcones() {
		JLabel iconeNumero = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 283, 305, 50, 30);
		add(iconeNumero);
		
		JLabel iconeDataInicio = FabricaIcones.criarIcone(FabricaImagens.DATA, 283, 370, 50, 30);
		add(iconeDataInicio);
		
		JLabel iconeDataFim = FabricaIcones.criarIcone(FabricaImagens.DATA, 445, 370, 50, 30);
		add(iconeDataFim);
		
		JLabel iconeDisciplina = FabricaIcones.criarIcone(FabricaImagens.MATRICULA, 283, 430, 50, 30);
		add(iconeDisciplina);
		
		JLabel iconeVagaRemunerada = FabricaIcones.criarIcone(FabricaImagens.INFO, 283, 490, 50, 30);
		add(iconeVagaRemunerada);
		
		JLabel iconeVagaVoluntario = FabricaIcones.criarIcone(FabricaImagens.INFO, 445, 490, 50, 30);
		add(iconeVagaVoluntario);
		
		JLabel iconePesoCRE = FabricaIcones.criarIcone(FabricaImagens.PESO, 283, 550, 50, 30);
		add(iconePesoCRE);
		
		JLabel iconePesoMedia = FabricaIcones.criarIcone(FabricaImagens.PESO, 445, 550, 50, 30);
		add(iconePesoMedia);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 150, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
		
	}
	public static void main(String[] args) {
		TelaCadastroEdital t = new TelaCadastroEdital();
	}
	

}
