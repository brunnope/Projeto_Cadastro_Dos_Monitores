package Telas.Aluno;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import Classes.Aluno;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Classes.Inscricao;
import Excecoes.EditalNaoEncontradoException;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.TelaVisualizarEditais;
import Telas.Coordenador.TelaTodosOsAlunos;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaDetalharEditalAberto extends TelaPadrao{
	private EditalDeMonitoria edital;
	private JTable tableDisciplinas;
	private Disciplina disciplina;
	JTextField fCRE;
	JTextField fMedia;

	public TelaDetalharEditalAberto(EditalDeMonitoria edital) {
		super("DETALHES EDITAL ABERTO");
		this.edital = edital;
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		try {
			edital = getCentral().recuperarEditalPeloId(getCentral().getTodosOsEditais().get(0).getId());
		} catch (EditalNaoEncontradoException e) {
			FabricaJOptionPane.criarMsgErro(e.getMessage());
		}
		adicionarMenuBar();
		adicionarLabels();
		adicionarTextFields();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
	}

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("DETALHES", 440, 110, 200, 30, Color.BLACK, 30);
		add(lTitulo);

		lTitulo = FabricaJLabel.criarJLabel("EDITAL", 440, 150, 180, 30, Color.BLACK, 30);
		add(lTitulo);

		JLabel lNumEdital = FabricaJLabel.criarJLabel("Número Edital", 297, 280, 100, 30, Color.BLACK, 12);
		add(lNumEdital);

		JLabel lDataInicio = FabricaJLabel.criarJLabel("Data de Inicio", 295, 335, 150, 30, Color.BLACK, 12);
		add(lDataInicio);

		JLabel lDataFim = FabricaJLabel.criarJLabel("Data Final", 458, 335, 150, 30, Color.BLACK, 12);
		add(lDataFim);

		JLabel lSelecione = FabricaJLabel.criarJLabel("Selecione uma ou mais disciplinas", 295, 395, 200, 30, Color.BLACK, 12);
		add(lSelecione);

		JLabel lPesoCRE = FabricaJLabel.criarJLabel("Seu CRE", 297, 525, 200, 30, Color.BLACK, 12);
		add(lPesoCRE);

		JLabel lPesoMedia = FabricaJLabel.criarJLabel("Sua Média", 459, 525, 200, 30, Color.BLACK, 12);
		add(lPesoMedia);

	}

	private void adicionarTextFields() {
		JTextField tNumEdital = FabricaJTextField.criarJTextField(325, 305, 282, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tNumEdital.setText(Float.toString(edital.getNumeroEdital()));
		tNumEdital.setEditable(false);
		add(tNumEdital);


		JTextField fDataInicio = FabricaJTextField.criarJTextField(325, 360, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataInicio.setText(edital.getDataInicio().toString());
		fDataInicio.setEditable(false);
		fDataInicio.setToolTipText("dd/MM/yyyy");
		fDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataInicio);


		JTextField fDataFim = FabricaJTextField.criarJTextField(487, 360, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fDataFim.setText(edital.getDataFim().toString());
		fDataFim.setEditable(false);
		fDataFim.setToolTipText("dd/MM/yyyy");
		fDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		add(fDataFim);

		fCRE = FabricaJTextField.criarJTextField( 325, 550, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fCRE.setToolTipText("No máximo 3 dígitos");
		limitarTamanhoCampo(fCRE, 3);
		add(fCRE);

		fMedia = FabricaJTextField.criarJTextField(487, 550, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fMedia.setToolTipText("No máximo 3 dígitos");
		limitarTamanhoCampo(fMedia, 3);
		add(fMedia);

	}

	private void adicionarTable() {
		DefaultTableModel mDisciplinas = new DefaultTableModel();

		mDisciplinas.addColumn("Disciplina(s)");
		mDisciplinas.addColumn("Vagas");
		mDisciplinas.addColumn("Inscritos");

		ArrayList<Disciplina> disciplinas = edital.getDisciplinas();
		for(Disciplina disciplina: disciplinas) {
			Object[] linha = new Object[3];
			linha[0] = disciplina.getNome();
			linha[1] = disciplina.getQuantVagas();
			linha[2] = disciplina.getInscricoes() != null ? disciplina.getInscricoes().size() : 0;
			mDisciplinas.addRow(linha);
		}

		// Torna todas as células não editáveis
		tableDisciplinas = new JTable(mDisciplinas) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(295, 420, 315, 100);
		add(rolagemTabela);
	}

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 610, 150, 30, Color.GREEN, Color.WHITE, 12);
		bVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarEditais();
			}
		});
		add(bVoltar);

		JButton bInscrever = FabricaJButton.criarJButton("Inscrever-se", 457, 610, 150, 30, Color.GREEN, Color.WHITE, 12);
		bInscrever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDisciplinas.getSelectedRow() == -1) {
					FabricaJOptionPane.criarMsgErro("Selecione uma disciplina!");
				} else {
					try {
						float cre = Float.parseFloat(fCRE.getText());
						float media = Float.parseFloat(fMedia.getText());

						if (cre > 100 || media > 100 || cre < 0 || media < 0) {
							FabricaJOptionPane.criarMsgErro("Valores de CRE ou média inválidos!");
						} else {
							disciplina = edital.getDisciplinas().get(tableDisciplinas.getSelectedRow());
							if (disciplina.getInscricoes().containsKey((Aluno) getUsuario())) {
								FabricaJOptionPane.criarMsgErro("Você já está inscrito nesta disciplina!");
							} else {
								disciplina.getInscricoes().put((Aluno) getUsuario(), new Inscricao((Aluno) getUsuario(), media, cre));
								
								getDados().salvarCentral(getCentral(), "central.xml");
								dispose();
								new TelaDetalharEditalAberto(edital);
							}
						}
					} catch (NumberFormatException ex) {
						FabricaJOptionPane.criarMsgErro("Preencha os campos vazios!");
					}
				}
			}
		});
		add(bInscrever);
	}

	private void adicionarIcones() {		
		JLabel iconeNumero = FabricaIcones.criarIcone(FabricaImagens.CADASTRAR, 283, 305, 50, 30);
		add(iconeNumero);

		JLabel iconeDataInicio = FabricaIcones.criarIcone(FabricaImagens.DATA, 283, 360, 50, 30);
		add(iconeDataInicio);

		JLabel iconeDataFim = FabricaIcones.criarIcone(FabricaImagens.DATA, 445, 360, 50, 30);
		add(iconeDataFim);


		JLabel iconePesoCRE = FabricaIcones.criarIcone(FabricaImagens.PESO, 283, 550, 50, 30);
		add(iconePesoCRE);

		JLabel iconePesoMedia = FabricaIcones.criarIcone(FabricaImagens.PESO, 445, 550, 50, 30);
		add(iconePesoMedia);

		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 100, 70, 94);
		add(iconeIf);

		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);

	}
	private void limitarTamanhoCampo(JTextField textField, int maxLength) {
		AbstractDocument document = (AbstractDocument) textField.getDocument();
		document.setDocumentFilter(new DocumentFilter() {
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text.matches("\\d*")) {
					// Calcula o novo comprimento do texto no documento após a substituição
					int newLength = fb.getDocument().getLength() + text.length() - length;

					// Verifica se o novo comprimento não excede o comprimento máximo definido
					if (newLength <= maxLength) {
						super.replace(fb, offset, length, text, attrs);
					}
				}
			}
		});

	}
}
