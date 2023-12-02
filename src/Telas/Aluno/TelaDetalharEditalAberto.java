package Telas.Aluno;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.EditalNaoEncontradoException;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJTextField;

public class TelaDetalharEditalAberto extends TelaPadrao{
	private EditalDeMonitoria edital;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		JTextField fCRE = FabricaJTextField.criarJTextField(325, 550, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fCRE.setToolTipText("No máximo 3 dígitos");
		add(fCRE);

		JTextField fMedia = FabricaJTextField.criarJTextField(487, 550, 120, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		fMedia.setToolTipText("No máximo 3 dígitos");
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
			linha[2] = disciplina.getAlunosInscritos().size();
			mDisciplinas.addRow(linha);
		}

		// Torna todas as células não editáveis
		JTable tableDisciplinas = new JTable(mDisciplinas) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		//se inscreve em uma por vez
		tableDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(295, 420, 315, 100);
		add(rolagemTabela);
	}

	private void adicionarButtons() {
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 293, 610, 150, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);

		JButton bInscrever = FabricaJButton.criarJButton("Inscrever-se", 457, 610, 150, 30, Color.GREEN, Color.WHITE, 12);
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
}