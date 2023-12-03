package Classes;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios{
	 public static void gerarPDF(String caminho, JTable tabela, EditalDeMonitoria edital) throws FileNotFoundException, DocumentException{
		Document doc = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(caminho));
			doc.open();
			Font f = new Font(FontFamily.TIMES_ROMAN,20,Font.BOLD);
			Paragraph p = new Paragraph("RESULTADO EDITAL " + edital.getNumeroEdital(),f);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(20);
			doc.add(p);
			
			String paragrafo = String.format("	Declaramos para os fins que se fizerem necessários, e por ser solicitado, "
					+ "que o edital de número %f, data de início %s e fim %s, foi finalizado com os seguintes resultados: ", 
					edital.getNumeroEdital(),edital.getDataInicio().toString(), edital.getDataFim().toString());
					
			
			f = new Font(FontFamily.TIMES_ROMAN,12);
			p = new Paragraph(paragrafo,f);
			p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
			p.setSpacingAfter(20);
			doc.add(p);
			
		   	PdfPTable pdfTable = new PdfPTable(tabela.getColumnCount());

		   	// Adiciona cabeçalho da tabela
		   	for (int i = 0; i < tabela.getColumnCount(); i++) {
		       	PdfPCell cell = new PdfPCell(new Phrase(tabela.getColumnName(i)));
		       	pdfTable.addCell(cell);
		   	}

		   	// Adiciona linhas da tabela
		   	for (int rows = 0; rows < tabela.getRowCount(); rows++) {
		       	for (int cols = 0; cols < tabela.getColumnCount(); cols++) {
		           	PdfPCell cell = new PdfPCell(new Phrase(tabela.getValueAt(rows, cols).toString()));
		           	pdfTable.addCell(cell);
		       	}
		  	}
		  doc.add(pdfTable);
		  doc.close();
		}
		 catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}
}