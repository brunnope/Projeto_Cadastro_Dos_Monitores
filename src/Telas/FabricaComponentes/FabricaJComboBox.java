package Telas.FabricaComponentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;

public abstract class FabricaJComboBox {

	public static JComboBox<String> criarJComboBpx(String[] opcoes, int x, int y, 
			int largura, int altura, Color corFundo, Color corLetra, int tamanhoLetra) {
		
		JComboBox<String> cbx = new JComboBox<>(opcoes);
	    cbx.setBounds(x, y, largura, altura);
	    cbx.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));
	    cbx.setBackground(corFundo);
	    cbx.setForeground(corLetra);
	    cbx.setFocusable(true);
	    cbx.setBorder(null);
	    cbx.setOpaque(true);
	    cbx.setSelectedItem(opcoes[0]);
		
	    return cbx;
	}
}
