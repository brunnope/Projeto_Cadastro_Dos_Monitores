package Telas.FabricaComponentes;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public abstract class FabricaJLabel {

	public static JLabel criarJLabel(String texto, int x, int y, int largura, int altura,
			Color corFundo, Color corLetra, int tamanhoLetra) {

		JLabel lbl = new JLabel(texto);
		lbl.setOpaque(true);
		lbl.setBounds(x, y, largura, altura);
		lbl.setForeground(corLetra);
		lbl.setBackground(corFundo);
		lbl.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));

		return lbl;
	}

	public static JLabel criarJLabel(String texto, int x, int y, int largura, int altura,
			Color corLetra, int tamanhoLetra) {

		JLabel lbl = new JLabel(texto);
		lbl.setBounds(x, y, largura, altura);
		lbl.setForeground(corLetra);
		lbl.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));
		return lbl;
	}
}
