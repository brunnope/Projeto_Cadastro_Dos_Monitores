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
import Excecoes.EditalInvalidoException;
import Excecoes.NumeroDoEditalJaExistenteException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.TelaVisualizarEditais;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaClonarEdital extends TelaPadrao{
	private JTextField tNumEdital;
	private JFormattedTextField fDataInicio;
	private JFormattedTextField fDataFim;
	private JFormattedTextField fPesoCRE;
	private JFormattedTextField fPesoMedia;
	private JFormattedTextField fNumMaxIncricoes;
	private EditalDeMonitoria edital;

	public TelaClonarEdital(EditalDeMonitoria edital){
		super("CLONAR EDITAL");
		this.edital = edital;
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
		JLabel lTitulo = FabricaJLabel.criarJLabel("CLONE", 435, 145, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("EDITAL", 435, 185, 180, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNumEdital = FabricaJLabel.criarJLabel("Número Edital", 297, 280, 100, 30, Color.BLACK, 12);
		add(lNumEdital);

		JLabel lDataInicio = FabricaJLabel.criarJLabel("Data de Inicio", 295, 345, 150, 30, Color.BLACK, 12);
		add(lDataInicio);

		JLabel lDataFim = FabricaJLabel.criarJLabel("Data Final", 458, 345, 150, 30, Color.BLACK, 12);
		add(lDataFim);

		JLabel lPesoCRE = FabricaJLabel.criarJLabel("Peso CRE Aluno", 297, 410, 200, 30, Color.BLACK, 12);
		add(lPesoCRE);

		JLabel lPesoMedia = FabricaJLabel.criarJLabel("Peso Média Aluno", 459, 410, 200, 30, Color.BLACK, 12);
		add(lPesoMedia);
		
		JLabel lNumInscricoes = FabricaJLabel.criarJLabel("Número Máximo de Inscricoes por Aluno", 297, 475, 250, 30, Color.BLACK, 12);
		add(lNumInscricoes);

	}

	private void adicionarTextFields() {
		tNumEdital = FabricaJTextField.criarJTextField(325, 305, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNumEdital.setText(Float.toString(edital.getNumeroEdital()));
		tNumEdital.setToolTipText("Apenas números!");
		add(tNumEdital);

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		fDataInicio = FabricaJTextField.criarJFormattedTextField(mascara, 325, 370, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataInicio.setValue(edital.getDataInicio().format(formatter));
		fDataInicio.setToolTipText("dd/MM/yyyy");
		fDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataInicio);

		fDataFim = FabricaJTextField.criarJFormattedTextField(mascara, 487, 370, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataFim.setValue(edital.getDataFim().format(formatter));
		fDataFim.setToolTipText("dd/MM/yyyy");
		fDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataFim);
		
		try {
			mascara = new MaskFormatter("#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		fNumMaxIncricoes = FabricaJTextField.criarJFormattedTextField(mascara,325, 500, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fNumMaxIncricoes.setText(Integer.toString(edital.getNumMaxInscricoes()));
		fNumMaxIncricoes.setToolTipText("No máximo 1 dígito");
		add(fNumMaxIncricoes);
		
		try {
			mascara = new MaskFormatter("#.#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fPesoCRE = FabricaJTextField.criarJFormattedTextField(mascara, 325, 435, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoCRE.setText(Float.toString(edital.getPesoCRE()));
		fPesoCRE.setToolTipText("No máximo 2 dígitos de 0 a 1");
		add(fPesoCRE);

		fPesoMedia = FabricaJTextField.criarJFormattedTextField(mascara, 487, 435, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoMedia.setText(Float.toString(edital.getPesoMedia()));
		fPesoMedia.setToolTipText("No máximo 2 dígitos de 0 a 1");
		add(fPesoMedia);
		
	}
	private void adicionarButtons() {
		JButton bVerDisciplinas = FabricaJButton.criarJButton("Ver Disciplinas", 293, 610, 153, 30, Color.GREEN, Color.WHITE, 12);
		add(bVerDisciplinas);
		bVerDisciplinas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaTodasAsDisciplinasEdital(edital);
			}
		});
			
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 450, 610, 153, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarEditais();
			}
		});
		
		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 293, 650, 311, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		bSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tNumEdital.getText().isBlank() || fDataInicio.getText().isBlank() || fDataFim.getText().isBlank() || 
						fPesoCRE.getText().equals(" . ") || fPesoMedia.getText().equals(" . ") || fNumMaxIncricoes.getText().isBlank()) {
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
							
							if (dataFim.isBefore(dataInicio)) {
								FabricaJOptionPane.criarMsgErro("Data Final antes que Data Inicial!");
							}
							else {
								try {
									float numeroEdital = Float.parseFloat(tNumEdital.getText());
									int numMaxInscricoes = Integer.parseInt(fNumMaxIncricoes.getText());
									
									edital.setNumeroEdital(numeroEdital);
									edital.setNumeroEdital(numeroEdital);
									edital.setDataInicio(dataInicio);
									edital.setDataFim(dataFim);
									edital.setPesoCRE(pesoCRE);
									edital.setPesoMedia(pesoMedia);
									edital.setNumMaxInscricoes(numMaxInscricoes);
									
									
									try {
										getCentral().editalExiste(edital);
										getCentral().adicionarEdital(edital);
										getDados().salvarCentral(getCentral(), "central.xml");
										FabricaJOptionPane.criarMsgValido("Clone criado com sucesso!");
										dispose();
										new TelaVisualizarEditais();
									} catch (NumeroDoEditalJaExistenteException e1) {
										FabricaJOptionPane.criarMsgErro("Número edital já cadastrado!");
									} catch (EditalInvalidoException e1) {
										FabricaJOptionPane.criarMsgErro("Erro ao salvar edital!");
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
		});
	}

	private void adicionarIcones() {
		JLabel iconeNumero = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 283, 305, 50, 30);
		add(iconeNumero);

		JLabel iconeDataInicio = FabricaIcones.criarIcone(FabricaImagens.DATA, 283, 370, 50, 30);
		add(iconeDataInicio);

		JLabel iconeDataFim = FabricaIcones.criarIcone(FabricaImagens.DATA, 445, 370, 50, 30);
		add(iconeDataFim);

		JLabel iconePesoCRE = FabricaIcones.criarIcone(FabricaImagens.PESO, 283, 435, 50, 30);
		add(iconePesoCRE);

		JLabel iconePesoMedia = FabricaIcones.criarIcone(FabricaImagens.PESO, 445, 435, 50, 30);
		add(iconePesoMedia);
		
		JLabel iconeNumMaxInscricoes = FabricaIcones.criarIcone(FabricaImagens.INFO, 283, 500, 50, 30);
		add(iconeNumMaxInscricoes);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 130, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);

	}
}
