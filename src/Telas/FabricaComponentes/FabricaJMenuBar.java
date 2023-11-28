package Telas.FabricaComponentes;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Telas.FabricaImagens;

public class FabricaJMenuBar {
	
	public static JMenuBar MenuCoordenador() {
		JMenuBar barraMenuCoordenador = new JMenuBar();
		
		JMenu mOpcoes = new JMenu("Opções");
		JMenuItem inicio = new JMenuItem("Início", FabricaImagens.INICIO);
		mOpcoes.add(inicio);
		
		JMenuItem cadastrarEdital = new JMenuItem("Cadastrar Edital", FabricaImagens.CADASTRAR);
		mOpcoes.add(cadastrarEdital);
		
		JMenuItem visualizarEdital = new JMenuItem("Visualizar Edital", FabricaImagens.VISUALIZAR);
		mOpcoes.add(visualizarEdital);
		
		JMenuItem todosAlunos = new JMenuItem("Todos os Alunos", FabricaImagens.LISTAR);
		mOpcoes.add(todosAlunos);
		
		JMenuItem sair = new JMenuItem("Sair", FabricaImagens.SAIR);
		mOpcoes.add(sair);
		
		
		JMenu mConfigurações = new JMenu("Configurações");
		JMenuItem editarCadastro = new JMenuItem("Editar Cadastro", FabricaImagens.EDITAR);
		mConfigurações.add(editarCadastro);
		
		JMenuItem editarCadastroAluno = new JMenuItem("Editar Cadastro Aluno", FabricaImagens.EDITAR);
		mConfigurações.add(editarCadastroAluno);
		
		
		barraMenuCoordenador.add(mOpcoes);
		barraMenuCoordenador.add(mConfigurações);
		
		return barraMenuCoordenador;
		
	}
	
	public static JMenuBar MenuAluno() {
		JMenuBar barraAluno = new JMenuBar();
		
		JMenu mOpcoes = new JMenu("Opções");
		JMenuItem inicio = new JMenuItem("Início", FabricaImagens.INICIO);
		mOpcoes.add(inicio);
		
		JMenuItem visualizarEdital = new JMenuItem("Visualizar Edital", FabricaImagens.VISUALIZAR);
		mOpcoes.add(visualizarEdital);
		
		JMenuItem todosAlunos = new JMenuItem("Editar Informações", FabricaImagens.LISTAR);
		mOpcoes.add(todosAlunos);
		
		JMenuItem sair = new JMenuItem("Sair", FabricaImagens.SAIR);
		mOpcoes.add(sair);
		
		barraAluno.add(mOpcoes);
		
		return barraAluno;
		
	}
	
}
