package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

import Excecoes.AlunoJaInscritoException;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;

public class EditalDeMonitoria {
	private long id;
	private String numeroEdital;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	
	public EditalDeMonitoria(String numeroEdital, LocalDate dataInicio, LocalDate dataFim, ArrayList<Disciplina> disciplinas) {
		id = System.currentTimeMillis();
		this.numeroEdital = numeroEdital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.disciplinas = disciplinas;
	}
	
	public EditalDeMonitoria(){
		id = System.currentTimeMillis();
	}

	public boolean inscrever(Aluno aluno, String nomeDisciplina) throws AlunoJaInscritoException, InscricoesFinalizadaException, InscricoesNaoAbertasException {
		if ((!jaAcabou()) && (status().equalsIgnoreCase("abertas"))) {
			for (Disciplina disciplina: disciplinas) {
				if(disciplina.getNome().equalsIgnoreCase(nomeDisciplina)) {
					for (Aluno a: disciplina.getAlunosInscritos()) {
						if(a.getMatricula().equalsIgnoreCase(aluno.getMatricula())) {
							throw new AlunoJaInscritoException();
						}
					} 
					if(disciplina.getQuantVagas() > 0) {
						disciplina.setQuantVagas(disciplina.getQuantVagas()-1);
						disciplina.getAlunosInscritos().add(aluno);					
						return true;
						}
					}
				}
			}
		throw new InscricoesFinalizadaException();		
	}
	
	public boolean jaAcabou() throws InscricoesFinalizadaException {
		LocalDate dataAtual = LocalDate.now();
		if (dataAtual.isBefore(dataFim)) {
			return false;
		}
		throw new InscricoesFinalizadaException();
	}
	
	public String status() throws InscricoesFinalizadaException, InscricoesNaoAbertasException {
		LocalDate dataAtual = LocalDate.now();
		if (jaAcabou()) {
			throw new InscricoesFinalizadaException();
		}else if(dataAtual.isBefore(dataInicio)) {
			throw new InscricoesNaoAbertasException();
		}
		return "abertas";
	}
	
	public String toString(){
		String printDisciplinas = ""; 
		String printMensagemEdital  = "";
		for(Disciplina disciplina: disciplinas) {
			printDisciplinas += disciplina.getNome() + " - " + disciplina.getQuantVagas() + "\n";
		}
		try {
			printMensagemEdital = String.format("Edital de Monitoria %s \n"
					+ "Disciplinas \n"
					+ "%s"
					+ "Inscrições %s", numeroEdital,printDisciplinas,status());
		} catch (InscricoesFinalizadaException | InscricoesNaoAbertasException e) {
			System.out.println(e.getMessage());
		}
		return printMensagemEdital;
	}
	
	public long getId() {
		return id;
	}

	public String getNumeroEdital() {
		return numeroEdital;
	}

	public void setNumeroEdital(String numeroEdital) {
		this.numeroEdital = numeroEdital;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
