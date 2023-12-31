package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Classes.EditalDeMonitoria;
import Excecoes.DisciplinaJaCadastradaException;
import Excecoes.EditalInvalidoException;
import Excecoes.NumeroDoEditalJaExistenteException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaCadastroEdital extends TelaPadrao{
	private JTextField tNumEdital;
	private JFormattedTextField fDataInicio;
	private JFormattedTextField fDataFim;
	private JFormattedTextField fVagasRemuneradas;
	private JFormattedTextField fVagasVoluntario;
	private JFormattedTextField fPesoCRE;
	private JFormattedTextField fPesoMedia;
	private JFormattedTextField fNumMaxIncricoes;
	private JTextField tDisciplina;
	private EditalDeMonitoria edital;

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

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 435, 105, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("EDITAL", 435, 145, 180, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNumEdital = FabricaJLabel.criarJLabel("Número Edital", 297, 215, 100, 30, Color.BLACK, 12);
		add(lNumEdital);


		JLabel lDataInicio = FabricaJLabel.criarJLabel("Data de Inicio", 295, 280, 150, 30, Color.BLACK, 12);
		add(lDataInicio);

		JLabel lDataFim = FabricaJLabel.criarJLabel("Data Final", 458, 280, 150, 30, Color.BLACK, 12);
		add(lDataFim);

		JLabel lDisciplina = FabricaJLabel.criarJLabel("Disciplina", 297, 345, 200, 30, Color.BLACK, 12);
		add(lDisciplina);

		JLabel lVagasRemunerada = FabricaJLabel.criarJLabel("Vagas Remuneradas", 297, 410, 150, 30, Color.BLACK, 12);
		add(lVagasRemunerada);

		JLabel lVagasVoluntario = FabricaJLabel.criarJLabel("Vagas Voluntários", 458, 410, 150, 30, Color.BLACK, 12);
		add(lVagasVoluntario);

		JLabel lPesoCRE = FabricaJLabel.criarJLabel("Peso CRE Aluno", 297, 475, 200, 30, Color.BLACK, 12);
		add(lPesoCRE);

		JLabel lPesoMedia = FabricaJLabel.criarJLabel("Peso Média Aluno", 459, 475, 200, 30, Color.BLACK, 12);
		add(lPesoMedia);
		
		JLabel lNumInscricoes = FabricaJLabel.criarJLabel("Número Máximo de Inscricoes por Aluno", 297, 540, 250, 30, Color.BLACK, 12);
		add(lNumInscricoes);

	}

	private void adicionarTextFields() {
		tNumEdital = FabricaJTextField.criarJTextField(325, 240, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNumEdital.setToolTipText("Apenas números!");
		add(tNumEdital);

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fDataInicio = FabricaJTextField.criarJFormattedTextField(mascara, 325, 305, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataInicio.setToolTipText("dd/MM/yyyy");
		fDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataInicio);

		fDataFim = FabricaJTextField.criarJFormattedTextField(mascara, 487, 305, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataFim.setToolTipText("dd/MM/yyyy");
		fDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataFim);

		tDisciplina = FabricaJTextField.criarJTextField(325, 370, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		add(tDisciplina);

		mascara = null;
		try {
			mascara = new MaskFormatter("#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fVagasRemuneradas = FabricaJTextField.criarJFormattedTextField(mascara, 325, 435, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasRemuneradas.setToolTipText("No máximo 1 dígito");
		add(fVagasRemuneradas);

		fVagasVoluntario = FabricaJTextField.criarJFormattedTextField(mascara, 487, 435, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fVagasVoluntario.setToolTipText("No máximo 1 dígito");
		add(fVagasVoluntario);
		
		fNumMaxIncricoes = FabricaJTextField.criarJFormattedTextField(mascara,325, 565, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fNumMaxIncricoes.setToolTipText("No máximo 1 dígito");
		add(fNumMaxIncricoes);
		
		try {
			mascara = new MaskFormatter("#.#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fPesoCRE = FabricaJTextField.criarJFormattedTextField(mascara, 325, 500, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoCRE.setToolTipText("No máximo 2 dígitos de 0 a 1");
		add(fPesoCRE);

		fPesoMedia = FabricaJTextField.criarJFormattedTextField(mascara, 487, 500, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoMedia.setToolTipText("No máximo 2 dígitos de 0 a 1");
		add(fPesoMedia);
		
		

	}
	private void adicionarButtons() {
		JButton bSalvarAdicionar = FabricaJButton.criarJButton("Adicionar mais Disciplinas", 293, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvarAdicionar);
		bSalvarAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(edital != null) {
					new TelaCadastroDisciplina(edital);
				}else
					FabricaJOptionPane.criarMsgErro("Edital ainda não cadastrado");
			}
		});

		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 452, 610, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		bSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tNumEdital.getText().isBlank() || fDataInicio.getText().isBlank() || fDataFim.getText().isBlank() || tDisciplina.getText().isBlank() 
						|| fVagasRemuneradas.getText().isBlank() || fVagasVoluntario.getText().isBlank() || fPesoCRE.getText().equals(" . ") || fPesoMedia.getText().equals(" . ") || fNumMaxIncricoes.getText().isBlank()) {
					FabricaJOptionPane.criarMsgErro("Preencha os campos vazios");
				}else {
					float pesoCRE = Float.parseFloat(fPesoCRE.getText());
					float pesoMedia =  Float.parseFloat(fPesoMedia.getText());
					
					if(((pesoCRE + pesoMedia) != 1) || (pesoCRE == 0) || (pesoMedia == 0)){
						FabricaJOptionPane.criarMsgErro("Peso CRE e Média precisam dar 1 somados e diferentes de 0!");
					}else {
						LocalDate dataInicio = null;
						LocalDate dataFim = null;
						try {
							dataInicio = LocalDate.parse(fDataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
							dataFim = LocalDate.parse(fDataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
							LocalDate dataAtual = LocalDate.now();
							
							if (dataFim.isBefore(dataInicio)) {
								FabricaJOptionPane.criarMsgErro("Data Final antes que Data Inicial!");
							}else if(dataFim.isBefore(dataAtual) || dataFim.isEqual(dataInicio) || dataFim.isEqual(dataAtual)) {
								FabricaJOptionPane.criarMsgErro("Data Final já passou ou igual a atual/inicial!");
							}
							else {
								try {
									float numeroEdital = Float.parseFloat(tNumEdital.getText());
									int vagasRemuneradas = Integer.parseInt(fVagasRemuneradas.getText());
									int vagasVoluntarias = Integer.parseInt(fVagasVoluntario.getText());
									int numMaxInscricoes = Integer.parseInt(fNumMaxIncricoes.getText());
									edital = new EditalDeMonitoria(numeroEdital, dataInicio, dataFim, pesoCRE, pesoMedia, numMaxInscricoes);
									try {
										edital.inscreverDisciplina(tDisciplina.getText(), vagasRemuneradas, vagasVoluntarias);
										getCentral().editalExiste(edital);
										getCentral().adicionarEdital(edital);
										FabricaJOptionPane.criarMsgValido("Edital cadastrado com sucesso!");
										getDados().salvarCentral(getCentral(), "central.xml");
									} catch (DisciplinaJaCadastradaException | EditalInvalidoException |  NumeroDoEditalJaExistenteException  e1) {
										FabricaJOptionPane.criarMsgErro(e1.getMessage());
									} 
								} catch(NumberFormatException e1) {
									FabricaJOptionPane.criarMsgErro("Número de Edital com letra!");
								}
							}
						} catch(DateTimeParseException e1) {
							FabricaJOptionPane.criarMsgErro("Data inválida");
							}
						
						}
					
					}
				}
			}
		);
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 650, 313, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaHomeCoordenador();

			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeNumero = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 283, 240, 50, 30);
		add(iconeNumero);

		JLabel iconeDataInicio = FabricaIcones.criarIcone(FabricaImagens.DATA, 283, 305, 50, 30);
		add(iconeDataInicio);

		JLabel iconeDataFim = FabricaIcones.criarIcone(FabricaImagens.DATA, 445, 305, 50, 30);
		add(iconeDataFim);

		JLabel iconeDisciplina = FabricaIcones.criarIcone(FabricaImagens.MATRICULA, 283, 370, 50, 30);
		add(iconeDisciplina);

		JLabel iconeVagaRemunerada = FabricaIcones.criarIcone(FabricaImagens.INFO, 283, 435, 50, 30);
		add(iconeVagaRemunerada);

		JLabel iconeVagaVoluntario = FabricaIcones.criarIcone(FabricaImagens.INFO, 445, 435, 50, 30);
		add(iconeVagaVoluntario);

		JLabel iconePesoCRE = FabricaIcones.criarIcone(FabricaImagens.PESO, 283, 500, 50, 30);
		add(iconePesoCRE);

		JLabel iconePesoMedia = FabricaIcones.criarIcone(FabricaImagens.PESO, 445, 500, 50, 30);
		add(iconePesoMedia);
		
		JLabel iconeNumMaxInscricoes = FabricaIcones.criarIcone(FabricaImagens.INFO, 283, 565, 50, 30);
		add(iconeNumMaxInscricoes);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 95, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);


	}
}
