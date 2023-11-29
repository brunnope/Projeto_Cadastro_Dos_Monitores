package Classes;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;
import Telas.TelaLogin;
import Telas.Coordenador.TelaCadastroCoordenador;

public class MainCadastro {
	public static void main(String[] args) {
		Persistencia dados = new Persistencia();
		CentralDeInformacoes central = dados.recuperarCentral("central.xml");
	
		if (central.getCoordenador() == null) {
			TelaCadastroCoordenador t = new TelaCadastroCoordenador();
		}else {
			TelaLogin t = new TelaLogin();
		}	
	}
}
