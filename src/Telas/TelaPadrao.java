package Telas;

import javax.swing.JFrame;
import Classes.Utilidades;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;

public  abstract class TelaPadrao extends JFrame{
	private Utilidades util = new Utilidades();
	private Persistencia dados = new Persistencia();
	private CentralDeInformacoes central = dados.recuperarCentral("central.xml");
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
	public Persistencia getDados () {
		return dados;
	}
	public CentralDeInformacoes getCentral() {
		return central;
	}
}
