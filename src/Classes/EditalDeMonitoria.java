package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditalDeMonitoria {
	private long id;
	private String numeroEdital;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private float pesoCRE;
	private float pesoMedia;
	//pode ser não calculado, calculado e final
	private String resultado;
	
	
	public EditalDeMonitoria(String numeroEdital, LocalDate dataInicio, LocalDate dataFim, ArrayList<Disciplina> disciplinas, float pesoCRE, float pesoMedia) {
		id = System.currentTimeMillis();
		this.numeroEdital = numeroEdital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.disciplinas = disciplinas;
		this.pesoCRE = pesoCRE;
		this.pesoMedia = pesoMedia;
	}
	
	public EditalDeMonitoria(){
		id = System.currentTimeMillis();
	}

	public boolean inscrever(Aluno aluno, String nomeDisciplina) {
		if ((!jaAcabou()) && (status().equalsIgnoreCase("abertas"))) {
			for (Disciplina disciplina: disciplinas) {
				if(disciplina.getNome().equalsIgnoreCase(nomeDisciplina)) {
					for (Aluno a: disciplina.getAlunosInscritos()) {
						if(a.getMatricula().equalsIgnoreCase(aluno.getMatricula())) {
							return false;
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
		return false;		
	}
	
	public boolean jaAcabou() {
		LocalDate dataAtual = LocalDate.now();
		if (dataAtual.isBefore(dataFim)) {
			return false;
		}
		return true;
	}
	
	public String status() {
		LocalDate dataAtual = LocalDate.now();
		if (jaAcabou()) {
			return "encerradas";
		}else if(dataAtual.isBefore(dataInicio)) {
			return "não abertas";
		}
		return "abertas";
	}
	
	public String toString() {
		String printDisciplinas = ""; 
		for(Disciplina disciplina: disciplinas) {
			printDisciplinas += disciplina.getNome() + " - " + disciplina.getQuantVagas() + "\n";
		}
		return String.format("Edital de Monitoria %s \n"
				+ "Disciplinas \n"
				+ "%s"
				+ "Inscrições %s", numeroEdital,printDisciplinas,status());
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
}
