package Telas.Coordenador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Classes.Disciplina;
import Classes.EditalDeMonitoria;
import Excecoes.EditalNaoEncontradoException;
import Telas.FabricaImagens;
import Telas.TelaPadrao;
import Telas.FabricaComponentes.FabricaIcones;
import Telas.FabricaComponentes.FabricaJButton;
import Telas.FabricaComponentes.FabricaJLabel;
import Telas.FabricaComponentes.FabricaJMenuBar;
import Telas.FabricaComponentes.FabricaJOptionPane;

public class TelaTodasAsDiscplinasEdital extends TelaPadrao{
	private EditalDeMonitoria edital;
	JTable tableDisciplinas;
	
	public TelaTodasAsDiscplinasEdital(EditalDeMonitoria edital){
		super("DETALHE DISCIPLINAS EDITAL CLONE");
		this.edital = edital;
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarMenuBar();
		adicionarLabels();
		adicionarTable();
		adicionarButtons();
		adicionarIcones();
	}
	
	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuCoordenador(this);
		setJMenuBar(mOpcoes);
	}

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("DETALHE", 410, 110, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("DISCIPLINAS", 410, 150, 200, 30, Color.BLACK, 30);
		add(lTitulo);

	}
	
	private void adicionarTable() {
		DefaultTableModel mDisciplinas = new DefaultTableModel();
		
		mDisciplinas.addColumn("Disciplina");
		mDisciplinas.addColumn("Vagas Remuneradas");
		mDisciplinas.addColumn("Vagas Voluntárias");		
		
		for(Disciplina disciplina: edital.getDisciplinas()) {
			Object[] linha = new Object[3];
			linha[0] = disciplina.getNome();
			linha[1] = disciplina.getQuantDeVagasRemuneradas();
			linha[2] = disciplina.getQuantDeVagasVoluntarias();
			mDisciplinas.addRow(linha);
		}

		// Torna todas as células não editáveis
		tableDisciplinas = new JTable(mDisciplinas) {

			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		//permite apenas uma seleção
		tableDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane rolagemTabela = new JScrollPane(tableDisciplinas);
		rolagemTabela.setBounds(275, 250, 350, 350);
		add(rolagemTabela);
		
	}
	
	private void adicionarButtons() {
		JButton bEditarDisciplina = FabricaJButton.criarJButton("Editar", 275, 610, 112, 30, Color.GREEN, Color.WHITE, 12);
		add(bEditarDisciplina);
		bEditarDisciplina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (tableDisciplinas.getSelectedRow() == -1) {
					FabricaJOptionPane.criarMsgErro("Selecione alguma disciplina!");
				}else {
					int opcao = FabricaJOptionPane.criarMsgDeOpcao("CONFIRMAÇÃO", "Editar Disciplina?");
					if (opcao == JOptionPane.YES_OPTION) {
						new TelaCadastroDisciplina(edital, edital.getDisciplinas().get(tableDisciplinas.getSelectedRow()));
					}
				}
					
			}
		});
		
		JButton bExcluirDisciplina = FabricaJButton.criarJButton("Excluir", 394, 610, 112, 30, Color.GREEN, Color.WHITE, 12);
		add(bExcluirDisciplina);
		bExcluirDisciplina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableDisciplinas.getSelectedRow() == -1) {
						FabricaJOptionPane.criarMsgErro("Selecione alguma disciplina!");
					}else {
						int opcao = FabricaJOptionPane.criarMsgDeOpcao("CONFIRMAÇÃO", "Excluir Disciplina?");
						if (opcao == JOptionPane.YES_OPTION) {
							edital.getDisciplinas().remove(tableDisciplinas.getSelectedRow());
							FabricaJOptionPane.criarMsgValido("Disciplina excluída com sucesso!");
						}
					}
				} catch(IndexOutOfBoundsException e1) {
					FabricaJOptionPane.criarMsgErro("Disciplina não encontrada. Atualize a tabela!");
				}
			}
		});
		
		JButton bAdicionarDisciplina = FabricaJButton.criarJButton("Adicionar", 512, 610, 112, 30, Color.GREEN, Color.WHITE, 12);
		add(bAdicionarDisciplina);
		bAdicionarDisciplina.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new TelaCadastroDisciplina(edital);
			}
		});
		
		JButton bAtualizarTabela = FabricaJButton.criarJButton("Atualizar Tabela", 275, 650, 172, 30, Color.GREEN, Color.WHITE, 12);
		add(bAtualizarTabela);
		bAtualizarTabela.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaTodasAsDiscplinasEdital(edital);
			}
		});
			
		JButton bVoltar = FabricaJButton.criarJButton("Voltar", 452, 650, 172, 30, Color.GREEN, Color.WHITE, 12);
		add(bVoltar);
		bVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaClonarEdital(edital);

			}
		});
	}

	private void adicionarIcones() {
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 330, 100, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
		
		
	}
}
