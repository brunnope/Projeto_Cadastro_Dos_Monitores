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
					e.getMessage();
				}
				break;
			case "2":
				try {
<<<<<<< HEAD
					central.listarAlunos();
				} catch (AlunoNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				System.out.println("<-----FIM-DE-LISTAGEM----->\n");
=======
					menu.listarTodosAlunos();
				} catch (NenhumAlunoCadastradoException e) {
					e.getMessage();
				}
>>>>>>> anderson
				break;
			case "3":
<<<<<<< HEAD
				System.out.print("Matrícula do aluno: ");
				matricula = leitor.nextLine();
				System.out.println();
				try {
					Aluno aluno1 = central.recuperarAlunoPorMatricula(matricula);
					System.out.println(aluno1.toString() + "\n");
				}catch(AlunoNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				System.out.println("<-----FIM----->\n");
=======
				try {
					menu.exibirAlunoEspecifico();
				} catch (AlunoNaoEncontradoException | NenhumAlunoCadastradoException e) {
					e.getMessage();
				}
>>>>>>> anderson
				break;
			case "4":
				try {
					menu.criarEdital();
				} catch (EditalInvalidoException e) {
					e.getMessage();
				}
<<<<<<< HEAD
				try {
					if (central.adicionarEdital(edital)) { 
						System.out.println("Edital cadastrado com sucesso!");
						System.out.println("<-----CADASTRO-FINALIZADO----->\n");
					}
				} catch (EditalInvalidoException e) {
					System.out.println(e.getMessage());
				}
=======
>>>>>>> anderson
				break;
			case "5":
				try {
					menu.listarEditaisCadastrados();
				} catch (NenhumEditalCadastradoExcecption e) {
					e.getMessage();
				}
				break;
			case "6":
				try {
					menu.exibirEditalEspecifico();
				} catch (EditalNaoEncontradoException | NenhumEditalCadastradoExcecption e) {
					e.getMessage();
				}
				break;
			case "7":
<<<<<<< HEAD
				System.out.print("Id do edital: ");
				long id = Long.parseLong(leitor.nextLine());
				if (opc.equals("6")) {
					try {
						System.out.println("\n" + central.recuperarEditalPeloId(id).toString() + "\n");
					} catch (EditalNaoEncontradoException e) {
						System.out.println(e.getMessage());
					}
				}else {
					EditalDeMonitoria edital1 = null;
					try {
						edital1 = central.recuperarEditalPeloId(id);
					} catch (EditalNaoEncontradoException e) {
						System.out.println(e.getMessage());
					}

					try {
						if (edital1.jaAcabou()) {
						}else {
							System.out.print("Matrícula do aluno(a): ");
							matricula = leitor.nextLine();
							String disciplina = null;
							Aluno aluno1 = null;
							try {
								aluno1 = central.recuperarAlunoPorMatricula(matricula);
								System.out.print("Nome da disciplina: ");
								disciplina = leitor.nextLine();
							}catch(AlunoNaoEncontradoException e) {
								System.out.println(e.getMessage());
							}
							try {
								edital1.inscrever(aluno1, disciplina);
								System.out.println("Aluno(a) cadastrado com sucesso!");
								String mensagem = String.format("Olá, %s :) \n\n"
										+ "Inscrição Confirmada: \n"
										+ "Disciplina: %s \n\n"
										+ "Att, Cordenação", aluno1.getNome(), disciplina);
								Mensageiro.enviarEmail(aluno1.getEmail(), mensagem);
							} catch (AlunoJaInscritoException | InscricoesFinalizadaException
									| InscricoesNaoAbertasException e) {
								System.out.println(e.getMessage());
							}

						}
					} catch (InscricoesFinalizadaException e) {
						System.out.println(e.getMessage());
					}

				}
				System.out.println("<-----CADASTRO-FINALIZADO----->\n");
=======
				try {
					menu.inscreverAlunoEdital();
				} catch (EditalNaoEncontradoException | InscricoesFinalizadaException | AlunoNaoEncontradoException
						| AlunoJaInscritoException | InscricoesNaoAbertasException e) {
					e.getMessage();
				}
>>>>>>> anderson
				break;		
			case "8":
<<<<<<< HEAD
				System.out.print("Matrícula do aluno: ");
				matricula = leitor.nextLine();
				System.out.print("Id do edital: ");
				id = Long.parseLong(leitor.nextLine());
				try {
					geraRelatorios.obterComprovanteDeInscricoesAluno(matricula, id, central);
				} catch (AlunoNaoEncontradoException | EditalNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}
				break;

			case "9":
				for (EditalDeMonitoria edital1: central.getTodosOsEditais()) {
					System.out.println(edital1.getId());
=======
				try {
					menu.gerarRelatorioDeInscrição();
				} catch (AlunoNaoEncontradoException | EditalNaoEncontradoException e) {
					e.getMessage();
>>>>>>> anderson
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