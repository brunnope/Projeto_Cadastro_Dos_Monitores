package Classes;
import Excecoes.*;
import java.util.Scanner;

public class MainCadastro {
	public static void main(String[] args) {	
		MenuOpcoes menu = new MenuOpcoes();
		try {
			menu.cadastrarCoordenador();
		} catch (AlunoJaMatriculadoException | EmailJaCadastradoException e) {
			System.out.println(e.getMessage());
		}
		String usuario;
		String senha;	
		String opc = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("=== TELA DE LOGIN ===");
		System.out.print("Usuário (email): ");
		usuario = scanner.nextLine();
		System.out.print("Senha: ");
		senha = scanner.nextLine();
		try {
			Pessoa pessoaLogada = menu.login(usuario, senha);
			if (pessoaLogada != null) {
				System.out.println("Login bem-sucedido para " + pessoaLogada.getNome() + "!\n");
				do { opc = menu.escolherOpcao();
				switch (opc.toUpperCase()) {
				case "1":
					try {
						menu.cadastrarAluno();
					} catch (AlunoJaMatriculadoException | EmailJaCadastradoException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "2":
					try {
						menu.listarTodosAlunos();
					} catch (NenhumAlunoCadastradoException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "3":
					try {
						menu.exibirAlunoEspecifico();
					} catch (AlunoNaoEncontradoException | NenhumAlunoCadastradoException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "4":
					try {
						menu.criarEdital();
					} catch (EditalInvalidoException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "5":
					try {
						menu.listarEditaisCadastrados();
					} catch (NenhumEditalCadastradoExcecption e) {
						System.out.println(e.getMessage());
					}
					break;
				case "6":
					try {
						menu.exibirEditalEspecifico();
					} catch (EditalNaoEncontradoException | NenhumEditalCadastradoExcecption e) {
						System.out.println(e.getMessage());
					}
					break;
				case "7":
					try {
						menu.inscreverAlunoEdital();
					} catch (EditalNaoEncontradoException | InscricoesFinalizadaException | AlunoNaoEncontradoException
							| AlunoJaInscritoException | InscricoesNaoAbertasException e) {
						System.out.println(e.getMessage());
					}
					break;		
				case "8":
					try {
						menu.gerarRelatorioDeInscrição();
					} catch (AlunoNaoEncontradoException | EditalNaoEncontradoException e) {
						System.out.println(e.getMessage());
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
			}else{
            System.out.println("Login falhou. Usuário ou senha incorretos.");
        }
    } catch (CredenciaisInvalidasException | NenhumAlunoCadastradoException e) {
        System.out.println(e.getMessage());
    }finally {
    	scanner.close();
    	}
	}
}
	