package Classes;

public class Inscricao {
	private Aluno aluno;
	private float nota;
	private float CRE;
	private boolean desistiu = false;
	private float notaFinal;
	private String resultado;
	
	public Inscricao (Aluno aluno, float nota, float cre ){
		this.aluno = aluno;
		this.nota = nota;
		this.CRE = cre;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public float getNota() {
		return nota;
	}
	public float getCRE() {
		return CRE;
	}
	public boolean isDesistiu() {
		return desistiu;
	}
	public void setDesistiu(boolean desistiu) {
		this.desistiu = desistiu;
	}
	public float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(float nota) {
		notaFinal = nota;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}