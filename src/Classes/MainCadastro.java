package Classes;
import Excecoes.AlunoJaInscritoException;
import Excecoes.AlunoJaMatriculadoException;
import Excecoes.AlunoNaoEncontradoException;
import Excecoes.EditalInvalidoException;
import Excecoes.EditalNaoEncontradoException;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;
import Excecoes.NenhumAlunoCadastradoException;
import Excecoes.NenhumEditalCadastradoExcecption;

public class MainCadastro {
	public static void main(String[] args) {	
		MenuOpcoes menu = new MenuOpcoes();
		String opc = null;
		do { opc = menu.escolherOpcao();
			switch (opc.toUpperCase()) {
			case "1":
				try {
					menu.cadastrarAluno();
				} catch (AlunoJaMatriculadoException e) {
					e.printStackTrace();
				}
				break;
			case "2":
				try {
					menu.listarTodosAlunos();
				} catch (NenhumAlunoCadastradoException e) {
					e.printStackTrace();
				}
				break;
			case "3":
				try {
					menu.exibirAlunoEspecifico();
				} catch (AlunoNaoEncontradoException | NenhumAlunoCadastradoException e) {
					
					e.printStackTrace();
				}
				break;
			case "4":
				try {
					menu.criarEdital();
				} catch (EditalInvalidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "5":
				try {
					menu.listarEditaisCadastrados();
				} catch (NenhumEditalCadastradoExcecption e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "6":
				try {
					menu.exibirEditalEspecifico();
				} catch (EditalNaoEncontradoException | NenhumEditalCadastradoExcecption e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "7":
				try {
					menu.inscreverAlunoEdital();
				} catch (EditalNaoEncontradoException | InscricoesFinalizadaException | AlunoNaoEncontradoException
						| AlunoJaInscritoException | InscricoesNaoAbertasException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;		
			case "8":
				try {
					menu.gerarRelatorioDeInscrição();
				} catch (AlunoNaoEncontradoException | EditalNaoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "9":
				menu.recuperarIDEditais();
				break;
			case "S":
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção Inválida!\n");
				continue;
			}
		}while (!opc.equalsIgnoreCase("S"));
		
		menu.salvarCentral();
	}
}