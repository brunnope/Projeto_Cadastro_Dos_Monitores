package Classes;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Excecoes.AlunoNaoEncontradoException;
import Excecoes.EditalNaoEncontradoException;
import Persistencia.CentralDeInformacoes;

public class GeradorDeRelatorios{
	public void obterComprovanteDeInscricoesAluno(String matricula, long id, CentralDeInformacoes central) throws AlunoNaoEncontradoException, AlunoNaoEncontradoException, EditalNaoEncontradoException {
		Document doc = new Document(PageSize.A4);
		Aluno aluno = central.recuperarAlunoPorMatricula(matricula);
		ArrayList<Disciplina> disciplinas = central.recuperarInscriçõesDeUmAlunoEmUmEdital(matricula, id);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("relatorio.pdf"));
			doc.open();
			Font f = new Font(FontFamily.TIMES_ROMAN,20,Font.BOLD);
			Paragraph p = new Paragraph("COMPROVANTE DE INSCRIÇÃO ",f);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(20);
			doc.add(p);
			
			String paragrafo = String.format("	Declaramos para os fins que se fizerem necessários, e por nos haver sido solicitado,"
					+ "que %s, matrícula %s, foi inscrito regularmente "
					+ "nas disciplinas de ", aluno.getNome(),aluno.getMatricula());
					
			
			f = new Font(FontFamily.TIMES_ROMAN,12);
			for (Disciplina disciplina: disciplinas) {
				paragrafo += disciplina.getNome() + ", ";
			}
			paragrafo += String.format("de nível de ensino graduação, Campus Monteiro, desta "
					+ "Instituição de Ensino, no edital de numero %s.", central.recuperarEditalPeloId(id).getNumeroEdital());
			
			p = new Paragraph(paragrafo,f);
			p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
			doc.add(p);
			doc.close();
			System.out.println("Relatório Criado com Sucesso!\n");
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}
}