package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Classes.Coordenador;
import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;
import Telas.Aluno.TelaDetalharEditalAberto;
import Telas.Aluno.TelaVisualizarResultado;
import Telas.Coordenador.TelaDetalharEditalEncerrado;
import Telas.Coordenador.TelaDetalhesResultado;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;

public class TelaVisualizarEditais extends TelaPadrao{
	private JTable tableEditais;
	private EditalDeMonitoria edital;
	
	public TelaVisualizarEditais() {
		super("VISUALIZAR TODOS OS EDITAIS");
		configurarComponentes();
		setVisible(true);
	}


	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
		if (getUsuario() instanceof Coordenador) {
			adicionarMenuBar();
		}else {
			adicionarMenuBarAluno();
		}
	}
	
	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("VISUALIZAR", 430, 180, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("EDITAIS", 430, 210, 200, 30, Color.BLACK, 30);
		add(lTitulo);
	}
	
	private void adicionarTable() {
		DefaultTableModel mEditais = new DefaultTableModel();
		
		mEditais.addColumn("Num. Edital");
		mEditais.addColumn("Data Início");
		mEditais.addColumn("Data Fim");
		mEditais.addColumn("Inscrições");
		mEditais.addColumn("Resultado");
		
		ArrayList<EditalDeMonitoria> editais = getCentral().getTodosOsEditais();
		for(EditalDeMonitoria edital: editais) {
			Object[] linha = new Object[5];
			linha[0] = edital.getNumeroEdital();
			linha[1] = edital.getDataInicio();
			linha[2] = edital.getDataFim();
			
			if ((edital.getStatus() != null) && (edital.getStatus().equals("finalizadas"))) {
				linha[3] = edital.getStatus();
			}else {
				try {
					//calcula no momento
					edital.status();
					edital.setStatus("abertas");;
				} catch (InscricoesFinalizadaException e) {
					edital.setStatus("finalizadas");
				} catch (InscricoesNaoAbertasException e) {
					edital.setStatus("não abertas");
				}
				linha[3] = edital.getStatus();
			}
			linha[4] = edital.getResultado();
			mEditais.addRow(linha);
		}

		// Torna todas as células não editáveis
			tableEditais = new JTable(mEditais) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//permite apenas uma seleção
		tableEditais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemTabela = new JScrollPane(tableEditais);
		rolagemTabela.setBounds(250, 300, 400, 300);
		add(rolagemTabela);
		
	}

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}
	private void adicionarMenuBarAluno() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}
	
	private void adicionarButtons() {
		JButton bVisualizar = FabricaJButton.criarJButton("Visualizar", 350, 630, 200, 30, Color.GREEN, Color.WHITE, 12);
		add(bVisualizar);
		bVisualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tableEditais.getSelectedRow() == -1){
					FabricaJOptionPane.criarMsgErro("Selecione algum Edital!");
				}else {
					edital = getCentral().getTodosOsEditais().get(tableEditais.getSelectedRow());
					if (edital.getResultado().equals("calculado") && edital.getStatus().equals("finalizadas")) {
						if (getUsuario() instanceof Coordenador) {
							dispose();
							new TelaDetalharEditalEncerrado(edital);
						}else {
							dispose();
							new TelaVisualizarResultado(edital);
						}
					}else if(edital.getResultado().equals("final")) {
						if (getUsuario() instanceof Coordenador) {
							dispose();
							new Telas.Coordenador.TelaDetalhesResultado(edital);
						}else {
							new TelaVisualizarResultado(edital);
						}
					}
					else {
						
						try {						
							edital.status();
							dispose();
							if (getUsuario() instanceof Coordenador) {
								new Telas.Coordenador.TelaDetalharEditalAberto(edital);
							}else {
								new TelaDetalharEditalAberto(edital);
							}
						}catch (InscricoesFinalizadaException e2) {
							
							//calcula automático os resultados caso tenha finalizado e não calculado!
							if (getUsuario() instanceof Coordenador) {
								if ((edital.getResultado().equals("não calculado") )){
									for (Disciplina disciplina : edital.getDisciplinas()) {
										disciplina.calcularResultadoDisciplina(edital);
										disciplina.distribuirVagas();
									}
									edital.setResultado("calculado");
									edital.setStatus("finalizadas");
									getDados().salvarCentral(getCentral(), "central.xml");
								}
								dispose();
								new TelaDetalharEditalEncerrado(edital);
							}else {
								FabricaJOptionPane.criarMsgErro(e2.getMessage());
							}
						} catch (InscricoesNaoAbertasException e3) {
							if (getUsuario() instanceof Coordenador) {
								dispose();
								new Telas.Coordenador.TelaDetalharEditalAberto(edital);
							}else {
								FabricaJOptionPane.criarMsgErro(e3.getMessage());
							}
						}
					}	
				}
			}
		});
	}

	private void adicionarIcones() {
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 330, 150, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
}
