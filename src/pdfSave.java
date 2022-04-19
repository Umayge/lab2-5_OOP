import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class pdfSave {

    pdfSave(DefaultTableModel tableModel)
    {
        Document document = new Document(PageSize.A4,50,50,50,50);
        PdfPTable table =new PdfPTable(6);
        try
        {
            PdfWriter writer = PdfWriter.getInstance((com.itextpdf.text.Document) document,new FileOutputStream("../pdfdata.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BaseFont baseFont = null;
        try
        {
            baseFont = BaseFont.createFont("../PT-Astra-Serif-Regular.ottt/PT/PT/PT-Astra-Serif/PT-Astra-Serif_Regular.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font font = new Font(baseFont,12);
        table.addCell(new PdfPCell(new Phrase("ФИО",font)));
        table.addCell(new PdfPCell(new Phrase("Паспорт",font)));
        table.addCell(new PdfPCell(new Phrase("Водительские права",font)));
        table.addCell(new PdfPCell( new Phrase("Марка машины",font)));
        table.addCell(new PdfPCell( new Phrase("Гос номер", font)));
        table.addCell(new PdfPCell(new Phrase("Дата последенго тех осмотра",font)));

        for (int i = 0; i < tableModel.getRowCount(); i++)
        {
            table.addCell(new Phrase((String) tableModel.getValueAt(i,0),font));
            table.addCell(new Phrase((String) tableModel.getValueAt(i,1),font));
            table.addCell(new Phrase((String) tableModel.getValueAt(i,2),font));
            table.addCell(new Phrase((String) tableModel.getValueAt(i,3),font));
            table.addCell(new Phrase((String) tableModel.getValueAt(i,4),font));
            table.addCell(new Phrase((String) tableModel.getValueAt(i,5),font));
        }
        document.open();
        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }
}
