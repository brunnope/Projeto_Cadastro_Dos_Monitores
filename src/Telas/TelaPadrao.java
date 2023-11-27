package Telas;

import javax.swing.JFrame;

public  abstract class TelaPadrao extends JFrame{
	
	public TelaPadrao(String titulo) {
		setSize(900, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle(titulo);
	}

	public abstract void configurarComponentes();
}
