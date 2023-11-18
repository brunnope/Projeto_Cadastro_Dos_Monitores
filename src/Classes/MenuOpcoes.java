package Classes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Excecoes.AlunoJaInscritoException;
import Excecoes.AlunoJaMatriculadoException;
import Excecoes.AlunoNaoEncontradoException;
import Excecoes.EditalInvalidoException;
import Excecoes.EditalNaoEncontradoException;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;
import Excecoes.NenhumAlunoCadastradoException;
import Excecoes.NenhumEditalCadastradoExcecption;
import Persistencia.CentralDeInformacoes;
import Persistencia.Persistencia;

public class MenuOpcoes {
	private Persistencia dados = new Persistencia();
	private GeradorDeRelatorios geraRelatorios = new GeradorDeRelatorios();
	private CentralDeInformacoes central = dados.recuperarCentral("central.xml");
	private Scanner leitor = new Scanner(System.in);

	public String escolherOpcao() {
		System.out.println("1 - novo aluno");
		System.out.println("2 - listar todos os alunos");
		System.out.println("3 - exibir informações de um aluno específico");
		System.out.println("4 - novo edital");
		System.out.println("5 - informar quantidade de editais cadastrados");
		System.out.println("6 - detalhar um edital específico");
		System.out.println("7 - inscrever aluno em vaga de algum edital");
		System.out.println("8 - gerar comprovante de todas as inscrisções do aluno em um edital");
		System.out.println("S - sair");
		System.out.println();
		String opc = leitor.nextLine();
		return opc;

	}
	
	public void cadastrarAluno() throws AlunoJaMatriculadoException {
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
		central.adicionarAluno(aluno); 
		System.out.println("Aluno(a) Cadastrado(a) com sucesso!\n");
	}

	public void listarTodosAlunos() throws NenhumAlunoCadastradoException {
		if (central.getTodosOsAlunos().size() == 0) {
			throw new NenhumAlunoCadastradoException();
		}
		for (Aluno a: central.getTodosOsAlunos()) {
			System.out.println(a.toString());
		}
		System.out.println("<-----FIM-DE-LISTAGEM----->\n");	
	}
	
	public void exibirAlunoEspecifico() throws AlunoNaoEncontradoException, NenhumAlunoCadastradoException {
		if (central.getTodosOsAlunos().size() == 0) {
			throw new NenhumAlunoCadastradoException();
		}else {
			System.out.print("Matrícula do aluno: ");
			String matricula = leitor.nextLine();
			System.out.println();
			Aluno aluno1 = central.recuperarAlunoPorMatricula(matricula);
			System.out.println(aluno1.toString() + "\n");
			System.out.println("<-----FIM----->\n");
		}
	} 
	
	public void adicionarDisciplinaEdital(EditalDeMonitoria e) {
		String opc = "s";
		while(opc.equalsIgnoreCase("s")) {
			Disciplina disciplina = new Disciplina();
			System.out.print("Nome: ");
			disciplina.setNome(leitor.nextLine());
			System.out.print("Quantidade de Vagas: ");
			disciplina.setQuantVagas(Integer.parseInt(leitor.nextLine()));
			e.getDisciplinas().add(disciplina);
			System.out.println("\nDisciplina Cadastrada");
			System.out.print("Continuar[S/N]: ");
			opc = leitor.nextLine();
			System.out.println();

		}
	}
	
	public void criarEdital() throws EditalInvalidoException {
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

		adicionarDisciplinaEdital(edital);

		central.adicionarEdital(edital);
		System.out.println("Edital cadastrado com sucesso!");
		System.out.println("<-----CADASTRO-FINALIZADO----->\n");
	}
	public void listarEditaisCadastrados() throws NenhumEditalCadastradoExcecption {
		if (central.getTodosOsEditais().size() == 0)
			throw new NenhumEditalCadastradoExcecption();
		System.out.println("Editais Cadastrados: " + central.getTodosOsEditais().size()+ "\n");

	}
	public long pedirID() {
		System.out.print("Id do edital: ");
		long id = Long.parseLong(leitor.nextLine());
		return id;
	}
	
	public void exibirEditalEspecifico() throws EditalNaoEncontradoException, NenhumEditalCadastradoExcecption {
		if (central.getTodosOsEditais().size() == 0) {
			throw new NenhumEditalCadastradoExcecption();
		}
		System.out.println("\n" + central.recuperarEditalPeloId(pedirID()).toString() + "\n");
	}
	
	public void inscreverAlunoEdital() throws EditalNaoEncontradoException, InscricoesFinalizadaException,
	AlunoNaoEncontradoException, AlunoJaInscritoException, InscricoesNaoAbertasException {
		long id = pedirID();
		if(central.recuperarEditalPeloId(id) == null) {
		}else {
			EditalDeMonitoria edital1 = central.recuperarEditalPeloId(id);
			if (edital1.jaAcabou()) {
			}else {
				System.out.print("Matrícula do aluno(a): ");
				String matricula = leitor.nextLine();
				if(central.recuperarAlunoPorMatricula(matricula) == null) {
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
		}System.out.println("<-----CADASTRO-FINALIZADO----->\n");
	}
	
	public void gerarRelatorioDeInscrição() throws AlunoNaoEncontradoException, EditalNaoEncontradoException {
		System.out.print("Matrícula do aluno: ");
		String matricula = leitor.nextLine();
		System.out.print("Id do edital: ");
		geraRelatorios.obterComprovanteDeInscricoesAluno(matricula, pedirID(), central);
	}
	
	public void salvarCentral() {
		dados.salvarCentral(central, "central.xml");
		System.out.println("<-----PROGRAMA-ENCERRADO----->");
	}
	
	public void recuperarIDEditais() {
		for (EditalDeMonitoria edital1: central.getTodosOsEditais()) {
			System.out.println(edital1.getId());
		}
	}
}

