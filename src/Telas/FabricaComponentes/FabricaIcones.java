package Telas.FabricaComponentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class FabricaIcones {

	public static JLabel criarIcone(ImageIcon img, int x, int y, int largura, int altura){
		JLabel icone = new JLabel(img);
		icone.setBounds(x, y, largura, altura);
		return icone;
	}
}
