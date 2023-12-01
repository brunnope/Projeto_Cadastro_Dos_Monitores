package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.text.MaskFormatter;

import Classes.EditalDeMonitoria;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaEditarEdital extends TelaPadrao{
	private JFormattedTextField fDataInicio;
	private JFormattedTextField fDataFim;
	private JFormattedTextField fPesoCRE;
	private JFormattedTextField fPesoMedia;
	private JFormattedTextField fNumMaxIncricoes;
	private EditalDeMonitoria edital;

	public TelaEditarEdital(EditalDeMonitoria edital){
		super("EDITAR EDITAL");
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
		JLabel lTitulo = FabricaJLabel.criarJLabel("EDITAR", 435, 140, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("EDITAL", 435, 180, 180, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lDataInicio = FabricaJLabel.criarJLabel("Data de Inicio", 295, 280, 150, 30, Color.BLACK, 12);
		add(lDataInicio);

		JLabel lDataFim = FabricaJLabel.criarJLabel("Data Final", 458, 280, 150, 30, Color.BLACK, 12);
		add(lDataFim);

		JLabel lPesoCRE = FabricaJLabel.criarJLabel("Peso CRE Aluno", 297, 360, 200, 30, Color.BLACK, 12);
		add(lPesoCRE);

		JLabel lPesoMedia = FabricaJLabel.criarJLabel("Peso Média Aluno", 459, 360, 200, 30, Color.BLACK, 12);
		add(lPesoMedia);
		
		JLabel lNumInscricoes = FabricaJLabel.criarJLabel("Número Máximo de Inscricoes por Aluno", 297, 440, 250, 30, Color.BLACK, 12);
		add(lNumInscricoes);

	}

	private void adicionarTextFields() {

		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		fDataInicio = FabricaJTextField.criarJFormattedTextField(mascara, 325, 305, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataInicio.setValue(edital.getDataInicio().format(formatter));
		fDataInicio.setToolTipText("dd/MM/yyyy");
		fDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataInicio);

		fDataFim = FabricaJTextField.criarJFormattedTextField(mascara, 487, 305, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataFim.setValue(edital.getDataFim().format(formatter));
		fDataFim.setToolTipText("dd/MM/yyyy");
		fDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataFim);
		
		try {
			mascara = new MaskFormatter("#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		fNumMaxIncricoes = FabricaJTextField.criarJFormattedTextField(mascara,325, 465, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fNumMaxIncricoes.setText(Integer.toString(edital.getNumMaxInscricoes()));
		fNumMaxIncricoes.setToolTipText("No máximo 1 dígito");
		add(fNumMaxIncricoes);
		
		try {
			mascara = new MaskFormatter("#.#");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fPesoCRE = FabricaJTextField.criarJFormattedTextField(mascara, 325, 385, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoCRE.setText(Float.toString(edital.getPesoCRE()));
		fPesoCRE.setToolTipText("No máximo 2 dígitos de 0 a 1");
		add(fPesoCRE);

		fPesoMedia = FabricaJTextField.criarJFormattedTextField(mascara, 487, 385, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fPesoMedia.setText(Float.toString(edital.getPesoMedia()));
		fPesoMedia.setToolTipText("No máximo 2 dígitos de 0 a 1");
		add(fPesoMedia);

	}
	private void adicionarButtons() {
		JButton bReabrirInscricoes = FabricaJButton.criarJButton("Reabrir Inscrições", 293, 560, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bReabrirInscricoes);
		bReabrirInscricoes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(!(edital.jaAcabou()) && edital.getStatus().equals("encerradas")){
						edital.setResultado("não calculado");
						edital.setStatus("abertas");
						FabricaJOptionPane.criarMsgValido("Inscrições Reabertas!");
						getDados().salvarCentral(getCentral(), "central.xml");
						dispose();
						new TelaDetalharEditalAberto(edital);
					}else if(edital.getStatus().equals("não abertas")) {
						FabricaJOptionPane.criarMsgErro("Inscrições não abertas ainda!");
					}
					else{
						FabricaJOptionPane.criarMsgErro("Inscrições já abertas");
					}
				} catch (InscricoesFinalizadaException e1) {
					FabricaJOptionPane.criarMsgErro(e1.getMessage());
				}
			}
		});

		JButton bSalvar = FabricaJButton.criarJButton("Salvar", 452, 560, 155, 30, Color.GREEN, Color.WHITE, 12);
		add(bSalvar);
		bSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (fDataInicio.getText().isBlank() || fDataFim.getText().isBlank() || fPesoCRE.getText().equals(" . ") || fPesoMedia.getText().equals(" . ") || fNumMaxIncricoes.getText().isBlank()) {
					FabricaJOptionPane.criarMsgErro("Preencha os campos vazios");
				}else {
					float pesoCRE = Float.parseFloat(fPesoCRE.getText());
					float pesoMedia =  Float.parseFloat(fPesoMedia.getText());
					int numMaxInscricoes = Integer.parseInt(fNumMaxIncricoes.getText());
					
					if(((pesoCRE + pesoMedia) != 1) || (pesoCRE == 0) || (pesoMedia == 0)){
						FabricaJOptionPane.criarMsgErro("Peso CRE e Média precisam dar 1 somados e diferentes de 0!");
					}
					else if(numMaxInscricoes <= edital.getNumMaxInscricoes()) {
						FabricaJOptionPane.criarMsgErro("Só pode aumentar o número máximo de inscrições!");
					}
					else {
						LocalDate dataInicio = null;
						LocalDate dataFim = null;
						LocalDate dataAtual = LocalDate.now();
						try {
							dataInicio = LocalDate.parse(fDataInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
							dataFim = LocalDate.parse(fDataFim.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
							
							if (dataFim.isBefore(dataInicio)) {
								FabricaJOptionPane.criarMsgErro("Data Final antes que Data Inicial!");
							}
							else if(dataInicio.isBefore(dataAtual) || dataFim.isBefore(dataAtual)) {
								FabricaJOptionPane.criarMsgErro("Datas Inválidas!");
							}
							else {
								try {
									edital.setDataInicio(dataInicio);
									edital.setDataFim(dataFim);
									edital.setPesoCRE(pesoCRE);
									edital.setPesoMedia(pesoMedia);
									edital.setNumMaxInscricoes(numMaxInscricoes);
									
									try {
										//calcula o status novamente
										edital.status();
										edital.setStatus("abertas");;
									} catch (InscricoesFinalizadaException e1) {
										edital.setStatus("finalizadas");
									} catch (InscricoesNaoAbertasException e2) {
										edital.setStatus("não abertas");
									}
									FabricaJOptionPane.criarMsgValido("Edital editado com sucesso!");
									getDados().salvarCentral(getCentral(), "central.xml");
									dispose();
									new TelaDetalharEditalAberto(edital);
									
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
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 600, 313, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaDetalharEditalAberto(edital);
			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeDataInicio = FabricaIcones.criarIcone(FabricaImagens.DATA, 283, 305, 50, 30);
		add(iconeDataInicio);

		JLabel iconeDataFim = FabricaIcones.criarIcone(FabricaImagens.DATA, 445, 305, 50, 30);
		add(iconeDataFim);

		JLabel iconePesoCRE = FabricaIcones.criarIcone(FabricaImagens.PESO, 283, 385, 50, 30);
		add(iconePesoCRE);

		JLabel iconePesoMedia = FabricaIcones.criarIcone(FabricaImagens.PESO, 445, 385, 50, 30);
		add(iconePesoMedia);
		
		JLabel iconeNumMaxInscricoes = FabricaIcones.criarIcone(FabricaImagens.INFO, 283, 465, 50, 30);
		add(iconeNumMaxInscricoes);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 125, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);

	}
}
