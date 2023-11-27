package Telas.FabricaComponentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

public abstract class FabricaJTextField {
	
	public static JTextField criarJTextField(int x, int y, int largura, int altura,
			Color corFundo, Color corLetra, int tamanhoLetra, Color corBorda) {
			
			JTextField txt = new JTextField();
		    txt.setBounds(x, y, largura, altura);
		    txt.setBackground(corFundo);
		    txt.setForeground(corLetra);
		    txt.setBorder(new LineBorder(corBorda));
		    txt.setFont(new Font("Arial", Font.PLAIN, tamanhoLetra));
		    
		    return txt;
		}
	
	public static JPasswordField criarJPasswordField(int x, int y, int largura, int altura,
			Color corFundo, Color corLetra, int tamanhoLetra, Color corBorda) {
		
		JPasswordField pass = new JPasswordField();
		pass.setBounds(x, y, largura, altura);
		pass.setBackground(corFundo);
		pass.setForeground(corLetra);
		pass.setBorder(new LineBorder(corBorda));
		pass.setFont(new Font("Arial", Font.PLAIN, tamanhoLetra));
	    
		return pass;
	}
	
	public static JFormattedTextField criarJFormattedTextField(MaskFormatter mascara,int x, int y, int largura, int altura,
			Color corFundo, Color corLetra, int tamanhoLetra, Color corBorda) {
		
		
		JFormattedTextField ftf = new JFormattedTextField(mascara);
		ftf.setBounds(x, y, largura, altura);
		ftf.setBackground(corFundo);
		ftf.setForeground(corLetra);
		ftf.setBorder(new LineBorder(corBorda));
		ftf.setFont(new Font("Arial", Font.PLAIN, tamanhoLetra));
	    
		return ftf;
	}
	
	
}
