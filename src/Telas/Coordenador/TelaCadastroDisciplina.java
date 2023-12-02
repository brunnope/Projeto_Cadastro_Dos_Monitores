package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.DisciplinaJaCadastradaException;
import Excecoes.EditalNaoEncontradoException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaCadastroDisciplina extends TelaPadrao{

	private JFormattedTextField fVagasRemuneradas;
	private JFormattedTextField fVagasVoluntario;
	private JButton bSalvar;
	private JTextField tDisciplina;
	private EditalDeMonitoria edital;
	
	private Disciplina disciplina;
	
	public TelaCadastroDisciplina(EditalDeMonitoria edital) {
		super("CADASTRO DISCIPLINA");
		this.edital = edital;
		setSize(400, 400);
		setLocationRelativeTo(null);
		configurarComponentes();
		setVisible(true);
	}
	
	public TelaCadastroDisciplina(EditalDeMonitoria edital, Disciplina disciplina) {
		super("EDITAR DISCIPLINA");
		this.edital = edital;
		this.disciplina = disciplina;
		setSize(400, 400);
		setLocationRelativeTo(null);
		configurarComponentes();
		
		tDisciplina.setText(disciplina.getNome());
		tDisciplina.setEditable(false);
		fVagasRemuneradas.setText(Integer.toString(disciplina.getQuantDeVagasRemuneradas()));
		fVagasVoluntario.setText(Integer.toString(disciplina.getQuantDeVagasVoluntarias()));
		bSalvar.setText("Editar");
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
		
		tDisciplina = FabricaJTextField.criarJTextField(60, 105, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tDisciplina);
		
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		fVagasRemuneradas = FabricaJTextField.criarJFormattedTextField(mascara, 60, 165, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasRemuneradas.setToolTipText("No máximo 1 dígito");
		add(fVagasRemuneradas);
		
		fVagasVoluntario = FabricaJTextField.criarJFormattedTextField(mascara, 222, 165, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasVoluntario.setToolTipText("No máximo 1 dígito");
		add(fVagasVoluntario);
		
	}
	
	private void adicionarButtons() {
		JButton bSair = FabricaJButton.criarJButton("Sair", 30, 225, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSair);
		bSair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		
		bSalvar = FabricaJButton.criarJButton("Adicionar", 189, 225, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		bSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tDisciplina.getText().isBlank() || fVagasRemuneradas.getText().isBlank() || fVagasVoluntario.getText().isBlank()) {
					FabricaJOptionPane.criarMsgErro("Preencha os campos vazios.");
				}else {
					int vagasRemuneradas = Integer.parseInt(fVagasRemuneradas.getText());
					int vagasVoluntarias = Integer.parseInt(fVagasVoluntario.getText());
					
					//condição para editar disciplina no clone de edital
					if (bSalvar.getText().equals("Editar")) {
						disciplina.setQuantDeVagasRemuneradas(vagasRemuneradas);
						disciplina.setQuantDeVagasVoluntarias(vagasVoluntarias);
						FabricaJOptionPane.criarMsgValido("Disciplina editada com sucesso!");
						new TelaTodasAsDisciplinasEdital(edital);
					}else {
						
						try {
							edital.inscreverDisciplina(tDisciplina.getText(), vagasRemuneradas, vagasVoluntarias);
							FabricaJOptionPane.criarMsgValido("Disciplina Adicionada com sucesso!");
							getDados().salvarCentral(getCentral(), "central.xml");
							dispose();
							new TelaCadastroDisciplina(edital);
						} catch (DisciplinaJaCadastradaException e1) {
							FabricaJOptionPane.criarMsgErro(e1.getMessage());
						}
					}
					
					
				}
			}
		});
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
		TelaCadastroEdital t = new TelaCadastroEdital();
	}

}
