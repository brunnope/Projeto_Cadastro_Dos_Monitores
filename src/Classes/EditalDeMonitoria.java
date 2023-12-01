package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

import Excecoes.AlunoJaInscritoException;
import Excecoes.DisciplinaJaCadastradaException;
import Excecoes.InscricoesFinalizadaException;
import Excecoes.InscricoesNaoAbertasException;

public class EditalDeMonitoria {
	private long id;
	private float numeroEdital;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private float pesoCRE;
	private float pesoMedia;
	private int numMaxInscricoes;

	//pode ser não calculado, calculado e final
	private String resultado = "não calculado";
	
	//pode ser aberto, não aberto, finalizadas ou encerradas
	private String status;
	
	public EditalDeMonitoria(float numeroEdital, LocalDate dataInicio, LocalDate dataFim,  float pesoCRE, float pesoMedia, int numMaxInscricoes) {
		id = System.currentTimeMillis();
		this.numeroEdital = numeroEdital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.pesoCRE = pesoCRE;
		this.pesoMedia = pesoMedia;
		this.numMaxInscricoes = numMaxInscricoes;
	}
	
	public EditalDeMonitoria(float numeroEdital, LocalDate dataInicio, LocalDate dataFim, ArrayList<Disciplina> disciplinas, float pesoCRE, float pesoMedia) {
		id = System.currentTimeMillis();
		this.numeroEdital = numeroEdital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.disciplinas = disciplinas;
		this.pesoCRE = pesoCRE;
		this.pesoMedia = pesoMedia;
	}
	
	public EditalDeMonitoria(float numeroEdital, LocalDate dataInicio, LocalDate dataFim, ArrayList<Disciplina> disciplinas, float pesoCRE, float pesoMedia, int numMaxInscricoes) {
		id = System.currentTimeMillis();
		this.numeroEdital = numeroEdital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.disciplinas = disciplinas;
		this.pesoCRE = pesoCRE;
		this.pesoMedia = pesoMedia;
		this.numMaxInscricoes = numMaxInscricoes;
	}
	
	public EditalDeMonitoria(){
		id = System.currentTimeMillis();
	}
	public EditalDeMonitoria getInstancia() {
		return this;
	}
	
	public void inscreverDisciplina(String nomeDisciplina, int quantRemuneradas, int quantVoluntarios) throws DisciplinaJaCadastradaException {
		for(Disciplina d:disciplinas) {
			if(d.getNome().equalsIgnoreCase(nomeDisciplina)) {
				throw new DisciplinaJaCadastradaException();
			}
		}
		Disciplina disciplina = new Disciplina(nomeDisciplina, quantRemuneradas, quantVoluntarios);
		getDisciplinas().add(disciplina);
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

	public float getNumeroEdital() {
		return numeroEdital;
	}

	public void setNumeroEdital(float numeroEdital) {
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
	
	public float getPesoCRE() {
		return pesoCRE;
	}
	
	public void setPesoCRE(float pesoCRE) {
		this.pesoCRE = pesoCRE;
	}
	
	public float getPesoMedia() {
		return pesoMedia;
	}
	
	public void setPesoMedia(float pesoMedia) {
		this.pesoMedia = pesoMedia;
	}
	
	public int getNumMaxInscricoes() {
		return numMaxInscricoes;
	}

	public void setNumMaxInscricoes(int numMaxInscricoes) {
		this.numMaxInscricoes = numMaxInscricoes;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
