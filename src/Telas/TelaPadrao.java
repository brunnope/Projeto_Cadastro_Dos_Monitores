package Telas;

import javax.swing.JFrame;

import Classes.Pessoa;
import Classes.Utilidades;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;

public  abstract class TelaPadrao extends JFrame{
	private static Pessoa usuario;
	private Utilidades util = new Utilidades();
	private static Persistencia dados = new Persistencia();
	private static CentralDeInformacoes central = dados.recuperarCentral("central.xml");

	public TelaPadrao(String titulo) {
		setSize(900, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle(titulo);
	}

	public abstract void configurarComponentes();
	
	public Utilidades getUtil() {
		return util;
	}
	public static Persistencia getDados () {
		return dados;
	}
	public static CentralDeInformacoes getCentral() {
		return central;
	}
	public static Pessoa getUsuario() {
		return usuario;
	}
	public static void setUsuario(Pessoa novousuario) {
		usuario = novousuario;
	}
}
