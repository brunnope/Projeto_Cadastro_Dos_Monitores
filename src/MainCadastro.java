import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainCadastro {
	public static void main(String[] args) {	
		Persistencia dados = new Persistencia();
		CentralDeInformacoes central = dados.recuperarCentral("central.xml");
		GeradorDeRelatorios geraRelatorios = new GeradorDeRelatorios();
		
		Scanner leitor = new Scanner(System.in);
		
		String opc = null;
		do {
			System.out.println("1 - novo aluno");
			System.out.println("2 - listar todos os alunos");
			System.out.println("3 - exibir informações de um aluno específico");
			System.out.println("4 - novo edital");
			System.out.println("5 - informar quantidade de editais cadastrados");
			System.out.println("6 - detalhar um edital específico");
			System.out.println("7 - inscrever aluno em vaga de algum edital");
			System.out.println("8 - gerar comprovante de todas as inscrisções do aluno em um edital");
			System.out.println("S - sair");
			opc = leitor.nextLine();
			System.out.println();
			
			switch (opc.toUpperCase()) {
			case "1":
				System.out.print("Nome: ");
				String nome = leitor.nextLine();
				System.out.print("Sexo: ");
				Sexo sexo = Sexo.valueOf(leitor.nextLine().toUpperCase());
				System.out.print("Matrícula: ");
				String matricula = leitor.nextLine();
				System.out.print("Email: ");
				String email = leitor.nextLine();
				System.out.print("Senha: ");
				String senha = leitor.nextLine();
				
				Aluno aluno = new Aluno(nome, sexo, matricula, email, senha);
				if (central.adicionarAluno(aluno)) {
					System.out.println("Aluno(a) Cadastrado(a) com sucesso!\n");
				}else {
					System.out.println("Aluno(a) já Cadastrado(a)!\n");
				}
				break;
			
				
			case "2":
				if (central.getTodosOsAlunos().size() == 0) {
					System.out.println("Nenhum Aluno Cadastrado no Momento");
				}else{
					for (Aluno a: central.getTodosOsAlunos()) {
						System.out.println(a.toString());
					}
				}
				System.out.println("<-----FIM-DE-LISTAGEM----->\n");
				break;
			
				
			case "3":
				if (central.getTodosOsAlunos().size() == 0) {
					System.out.println("Nenhum Aluno Cadastrado no Momento");
				}else {
					System.out.print("Matrícula do aluno: ");
					matricula = leitor.nextLine();
					System.out.println();
					Aluno aluno1 = central.recuperarAlunoPorMatricula(matricula);
					if (aluno1 == null){
							System.out.println("Aluno não encontrado!\n");
					}else {
							System.out.println(aluno1.toString() + "\n");
						}
				}
				System.out.println("<-----FIM----->\n");
				break;
				
				
			case "4":
				EditalDeMonitoria edital = new EditalDeMonitoria();
				System.out.print("Número do edital: ");
				edital.setNumeroEdital(leitor.nextLine());
				System.out.print("Data de início: ");
				LocalDate dataInicio = LocalDate.parse(leitor.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				edital.setDataInicio(dataInicio); 
				System.out.print("Data de fim: ");
				LocalDate dataFim = LocalDate.parse(leitor.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				edital.setDataFim(dataFim);
				
				System.out.println("\nCadastro das Disciplinas\n");
				String cont = "s";
				
				while(cont.equalsIgnoreCase("s")) {
					Disciplina disciplina = new Disciplina();
					System.out.print("Nome: ");
					disciplina.setNome(leitor.nextLine());
					System.out.print("Quantidade de Vagas: ");
					disciplina.setQuantVagas(Integer.parseInt(leitor.nextLine()));
					edital.getDisciplinas().add(disciplina);
					System.out.println("\nDisciplina Cadastrada");
					System.out.print("Continuar[S/N]: ");
					cont = leitor.nextLine();
					System.out.println();
					
				}
				if (central.adicionarEdital(edital)) {
					System.out.println("Edital cadastrado com sucesso!");
				}else {
					System.out.println("Edital já Cadastrado.");
				}
				System.out.println("<-----CADASTRO-FINALIZADO----->\n");
				break;
			
				
			case "5":
				System.out.println("Editais Cadastrados: " + central.getTodosOsEditais().size()+ "\n");
				break;
				
				
			case "6":
			case "7":
				if (central.getTodosOsEditais().size() == 0) {
					System.out.println("Nenhum edital cadastrado no momento!\n");
					continue;
				}
				System.out.print("Id do edital: ");
				long id = Long.parseLong(leitor.nextLine());
				if (opc.equals("6")) {
				System.out.println("\n" + central.recuperarEditalPeloId(id).toString() + "\n");
				}else {
					if(central.recuperarEditalPeloId(id) == null) {
						System.out.println("Edital Não Encontrado!\n");
					}else {
						EditalDeMonitoria edital1 = central.recuperarEditalPeloId(id);
						if (edital1.jaAcabou()) {
							System.out.println("Inscrições já encerradas!\n");
						}else {
						System.out.print("Matrícula do aluno(a): ");
						matricula = leitor.nextLine();
						if (central.recuperarAlunoPorMatricula(matricula) == null) {
							System.out.println("Aluno(a) Não Encontrado!\n");
						} else {
							Aluno aluno1 = central.recuperarAlunoPorMatricula(matricula);
							System.out.print("Nome da disciplina: ");
							String disciplina = leitor.nextLine();
							if(edital1.inscrever(aluno1, disciplina)){
								System.out.println("Aluno(a) cadastrado com sucesso!");
								String mensagem = String.format("Olá, %s :) \n\n"
										+ "Inscrição Confirmada: \n"
										+ "Disciplina: %s \n\n"
										+ "Att, Cordenação", aluno1.getNome(), disciplina);
								Mensageiro.enviarEmail(aluno1.getEmail(), mensagem);
							}else {
								System.out.println("Não foi possível realizar o cadastro!");
							}
							}
						}
					}
				}
				System.out.println("<-----CADASTRO-FINALIZADO----->\n");
				break;		
			
			case "8":
				System.out.print("Matrícula do aluno: ");
				matricula = leitor.nextLine();
				System.out.print("Id do edital: ");
				id = Long.parseLong(leitor.nextLine());
				geraRelatorios.obterComprovanteDeInscricoesAluno(matricula, id, central);
				break;
				
			case "9":
				for (EditalDeMonitoria edital1: central.getTodosOsEditais()) {
					System.out.println(edital1.getId());
				}
				break;
				
			case "S":
				System.out.println("Saindo...");
				break;
			
				
			default:
				System.out.println("Opção Inválida!\n");
				continue;
			}
		} while (!opc.equalsIgnoreCase("S"));
		
		dados.salvarCentral(central, "central.xml");
		System.out.println("<-----PROGRAMA-ENCERRADO----->");
		leitor.close();
	}
}
