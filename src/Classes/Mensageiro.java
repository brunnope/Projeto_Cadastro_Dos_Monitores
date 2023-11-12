package Classes;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Mensageiro {
	public static void enviarEmail(String eml, String mensagem) {
		SimpleEmail email = new SimpleEmail();  

	      try {  
	      //email.setDebug(true);  
	      email.setHostName("smtp.gmail.com");  
	      email.setAuthentication("mensageiroPOO1@gmail.com","ehqjgxcmsrminetq");  
	      email.setSSL(true);  
	      email.addTo(eml);  
	      email.setFrom(eml);
	      email.setSubject("Inscrição Edital de Monitoria");  
	      email.setMsg(mensagem);  
	      email.send();  

	      } catch (EmailException e) {  

	      System.out.println(e.getMessage());  
	      }
	}
}
