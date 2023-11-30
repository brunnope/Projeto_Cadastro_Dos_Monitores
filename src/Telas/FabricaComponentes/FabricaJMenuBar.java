package Telas.FabricaComponentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Classes.Aluno;
import Telas.FabricaImagens;
import Telas.TelaEditarInformacoes;
import Telas.TelaLogin;
import Telas.TelaPadrao;
import Telas.TelaVisualizarEditais;
import Telas.Aluno.TelaCadastroAluno;
import Telas.Aluno.TelaEditarInformacoesAluno;
import Telas.Aluno.TelaHomeAluno;
import Telas.Coordenador.TelaCadastroCoordenador;
import Telas.Coordenador.TelaCadastroEdital;
import Telas.Coordenador.TelaHomeCoordenador;
import Telas.Coordenador.TelaTodosOsAlunos;

public class FabricaJMenuBar{

	public static JMenuBar MenuCoordenador(TelaPadrao tela) {
		JMenuBar barraMenuCoordenador = new JMenuBar();
		
		JMenu mOpcoes = new JMenu("Opções");
		JMenuItem inicio = new JMenuItem("Início", FabricaImagens.INICIO);
		mOpcoes.add(inicio);
		inicio.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaHomeCoordenador();
			}
		});
		JMenuItem cadastrarEdital = new JMenuItem("Cadastrar Edital", FabricaImagens.CADASTRAR);
		mOpcoes.add(cadastrarEdital);
		cadastrarEdital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();	
				new TelaCadastroEdital();
			}
		});
		JMenuItem visualizarEdital = new JMenuItem("Visualizar Edital", FabricaImagens.VISUALIZAR);
		mOpcoes.add(visualizarEdital);
		visualizarEdital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaVisualizarEditais();
			}
		});
		JMenuItem todosAlunos = new JMenuItem("Todos os Alunos", FabricaImagens.LISTAR);
		mOpcoes.add(todosAlunos);
		todosAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaTodosOsAlunos();
			}
		});
		JMenuItem sair = new JMenuItem("Sair", FabricaImagens.SAIR);
		mOpcoes.add(sair);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaLogin();
			}
		});
		
		JMenu mConfigurações = new JMenu("Configurações");
		JMenuItem editarCadastro = new JMenuItem("Editar Cadastro", FabricaImagens.EDITAR);
		mConfigurações.add(editarCadastro);
		editarCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaEditarInformacoes();
			}
		});
		
		barraMenuCoordenador.add(mOpcoes);
		barraMenuCoordenador.add(mConfigurações);
		
		return barraMenuCoordenador;
		
	}
	
	public static JMenuBar MenuAluno(TelaPadrao tela) {
		JMenuBar barraAluno = new JMenuBar();
		
		JMenu mOpcoes = new JMenu("Opções");
		JMenuItem inicio = new JMenuItem("Início", FabricaImagens.INICIO);
		mOpcoes.add(inicio);
		inicio.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaHomeAluno();
			}
		});		JMenuItem visualizarEdital = new JMenuItem("Visualizar Edital", FabricaImagens.VISUALIZAR);
		mOpcoes.add(visualizarEdital);
		visualizarEdital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaVisualizarEditais();
			}
		});
		JMenuItem todosAlunos = new JMenuItem("Editar Informações", FabricaImagens.LISTAR);
		mOpcoes.add(todosAlunos);
		todosAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				Aluno aluno = (Aluno) tela.getUsuario();
				new TelaEditarInformacoesAluno(aluno);
			}
		});
		JMenuItem sair = new JMenuItem("Sair", FabricaImagens.SAIR);
		mOpcoes.add(sair);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaLogin();
			}
		});
		barraAluno.add(mOpcoes);
		
		return barraAluno;
		
	}
	
}
