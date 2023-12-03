package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Disciplina {
	private String nome;
	private int quantDeVagasRemuneradas;
	private int quantDeVagasVoluntarias;
	private HashMap<Aluno, Inscricao> inscricoes = new HashMap<>();
	
	public Disciplina(String nome, int quantRemuneradas, int quantVoluntarias) {
		this.nome = nome;
		quantDeVagasRemuneradas = quantRemuneradas;
		quantDeVagasVoluntarias = quantVoluntarias;
	}
	
	public void calcularResultadoDisciplina(EditalDeMonitoria edital) {
        float pontuacao = 0;
        for(Disciplina disciplina : edital.getDisciplinas()) {
            for(Inscricao inscricao:disciplina.getInscricoes().values()) {
                pontuacao = edital.getPesoCRE() * inscricao.getCRE() + edital.getPesoMedia() * inscricao.getNota();
                inscricao.setNotaFinal(pontuacao);
            }

        }
	}
	
	
	 public void distribuirVagas() {
        List<Map.Entry<Aluno, Inscricao>> listaOrdenada = ordenarInscricoesPorNotaFinal();

        int vagasRemuneradas = getQuantDeVagasRemuneradas();
        int vagasVoluntarias = getQuantDeVagasVoluntarias();

        for (Map.Entry<Aluno, Inscricao> entry : listaOrdenada) {
            Inscricao inscricao = entry.getValue();
            
            if (inscricao.isDesistiu()) {
            	continue;
            }

            if (vagasRemuneradas > 0) {
                inscricao.setResultado("Bolsista");
                vagasRemuneradas--;
            } else if (vagasVoluntarias > 0) {
                inscricao.setResultado("Voluntário");
                vagasVoluntarias--;
            } else {
                inscricao.setResultado("Não Contemplado");
            }
        }
    }

    public List<Map.Entry<Aluno, Inscricao>> ordenarInscricoesPorNotaFinal() {
        List<Map.Entry<Aluno, Inscricao>> listaInscricoes = new ArrayList<>(inscricoes.entrySet());

        Collections.sort(listaInscricoes, new Comparator<Map.Entry<Aluno, Inscricao>>() {
            public int compare(Entry<Aluno, Inscricao> entry1, Entry<Aluno, Inscricao> entry2) {
                // Comparando as notas finais
                return Float.compare(entry2.getValue().getNotaFinal(), entry1.getValue().getNotaFinal());
            }
        });

        return listaInscricoes;
    }

	public int getQuantDeVagasRemuneradas() {
		return quantDeVagasRemuneradas;
	}
	
	public void setQuantDeVagasRemuneradas(int quantDeVagasRemuneradas) {
		this.quantDeVagasRemuneradas = quantDeVagasRemuneradas;
	}
	
	public int getQuantDeVagasVoluntarias() {
		return quantDeVagasVoluntarias;
	}
	
	public void setQuantDeVagasVoluntarias(int quantDeVagasVoluntarias) {
		this.quantDeVagasVoluntarias = quantDeVagasVoluntarias;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public HashMap<Aluno, Inscricao> getInscricoes() {
		if (inscricoes == null) {
			inscricoes = new HashMap<>();		}
		return inscricoes;
	}
	public void setInscricoes(HashMap<Aluno, Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

}