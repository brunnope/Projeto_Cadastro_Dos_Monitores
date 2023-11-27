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

public class TelaCadastroDisciplina extends TelaPadrao{

	public TelaCadastroDisciplina() {
		super("CADASTRO DISCIPLINA");
		setSize(400, 400);
		setLocationRelativeTo(null);
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
		
		JLabel lDisciplina = FabricaJLabel.criarJLabel("Disciplina", 35, 75, 200, 30, Color.BLACK, 12);
		add(lDisciplina);
		
		JLabel lVagasRemunerada = FabricaJLabel.criarJLabel("Vagas Remuneradas", 35, 135, 150, 30, Color.BLACK, 12);
		add(lVagasRemunerada);
		
		JLabel lVagasVoluntario = FabricaJLabel.criarJLabel("Vagas Voluntários", 198, 135, 150, 30, Color.BLACK, 12);
		add(lVagasVoluntario);

	}
	
	private void adicionarTextFields() {
		
		JTextField tDisciplina = FabricaJTextField.criarJTextField(60, 105, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tDisciplina);
		
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JFormattedTextField fVagasRemuneradas = FabricaJTextField.criarJFormattedTextField(mascara, 60, 165, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasRemuneradas.setToolTipText("No máximo 2 dígitos");
		add(fVagasRemuneradas);
		
		JFormattedTextField fVagasVoluntario = FabricaJTextField.criarJFormattedTextField(mascara, 222, 165, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasVoluntario.setToolTipText("No máximo 2 dígitos");
		add(fVagasVoluntario);
		
	}
	
	private void adicionarButtons() {
		JButton bSalvarAdicionar = FabricaJButton.criarJButton("Sair", 30, 225, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvarAdicionar);
		
		JButton bSalvar = FabricaJButton.criarJButton("Adicionar", 189, 225, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
	}

	private void adicionarIcones() {
		JLabel iconeDisciplina = FabricaIcones.criarIcone(FabricaImagens.MATRICULA, 20, 105, 50, 30);
		add(iconeDisciplina);
		
		JLabel iconeVagaRemunerada = FabricaIcones.criarIcone(FabricaImagens.INFO, 20, 165, 50, 30);
		add(iconeVagaRemunerada);
		
		JLabel iconeVagaVoluntario = FabricaIcones.criarIcone(FabricaImagens.INFO, 182, 165, 50, 30);
		add(iconeVagaVoluntario);
				
		
	}
	public static void main(String[] args) {
		TelaCadastroDisciplina t = new TelaCadastroDisciplina();
	}
	

}
