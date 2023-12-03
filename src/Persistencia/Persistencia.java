package Persistencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Persistencia {
	private XStream xStream = new XStream( new DomDriver( "UTF-8" ));
	
	
	public Persistencia() {
		xStream.addPermission(AnyTypePermission.ANY);
	}
	
	public void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
		
		try {
			File arquivo = new File(nomeArquivo);
			arquivo.createNewFile();
			PrintWriter pw = new PrintWriter(arquivo);
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
			xml += xStream.toXML(central);
			pw.print(xml);
			pw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		
		if(arquivo.exists()) {
			try {
				FileInputStream fis = new FileInputStream(arquivo);
				
				return (CentralDeInformacoes) xStream.fromXML(fis);
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
		}
		return new CentralDeInformacoes();	
	}
}