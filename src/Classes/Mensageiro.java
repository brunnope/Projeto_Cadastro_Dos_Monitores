package Classes;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro {
	public static void enviarEmail(String destino, String mensagemTitulo, String mensagemEmail) throws EmailException {
		SimpleEmail email = new SimpleEmail();  

	      try {  
	      //email.setDebug(true);  
	      email.setHostName("smtp.gmail.com");  
	      email.setAuthentication("mensageiroPOO1@gmail.com","ehqjgxcmsrminetq");  
	      email.setSSL(true);  
	      email.addTo(destino);  
	      email.setFrom("mensageiroPOO1@gmail.com");
	      email.setSubject(mensagemTitulo);  
	      email.setMsg(mensagemEmail);  
	      email.send();  

	      } catch (EmailException e) {  
	    	  throw e;
	      }
	}
}
