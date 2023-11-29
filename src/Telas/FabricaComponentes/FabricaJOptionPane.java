package Telas.FabricaComponentes;

import javax.swing.JOptionPane;

import Telas.FabricaImagens;

public abstract class FabricaJOptionPane {

	public static void criarMsgErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}
		
	public static void criarMsgAtencao(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Atencao", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void criarMsgValido(String msg) {
		JOptionPane.showMessageDialog(null, msg, "REALIZADO COM SUCESSO", JOptionPane.WARNING_MESSAGE, FabricaImagens.CERTO);
	}
		
	public static int criarMsgDeOpcao(String titulo, String msg) {
		int opc = JOptionPane.showConfirmDialog(null, msg, titulo,JOptionPane.YES_NO_OPTION);
		return opc;
	}
	public static String criarInput(String msg) {
		String codigo = JOptionPane.showInputDialog(msg);
		return codigo;
	}
	public static void criarMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Informacao", JOptionPane.INFORMATION_MESSAGE);
	}
}
