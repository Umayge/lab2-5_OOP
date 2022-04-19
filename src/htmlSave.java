import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class htmlSave extends JFrame
{
    htmlSave(String str, DefaultTableModel tableModel) throws Exception
    {
        if(tableModel.getRowCount()==0)return;
        FileDialog saveHTML = new FileDialog(this,str,FileDialog.SAVE);
        saveHTML.setFile("*.html");
        saveHTML.setVisible(true);
        String fileNameSave  = saveHTML.getDirectory() + saveHTML.getFile();
        if(fileNameSave == null)return;
        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter(new FileWriter(fileNameSave));

        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println("<TABLE BORDER><TR><TH>ФИО<TH>Паспорт<TH>Водительске права<TH>Марка машины<TH>Гос Номер<TH>Дата последнего тех осмотра</TR>");
        for(int i = 0; i < tableModel.getRowCount(); i++)
        {
            int square = i*i;
            printWriter.println("<TR><TD>"+(String) tableModel.getValueAt(i,0)
                    +"<TD>"+(String) tableModel.getValueAt(i,1)
                    +"<TD>"+(String) tableModel.getValueAt(i,2)
                    +"<TD>"+(String) tableModel.getValueAt(i,3)
                    +"<TD>"+(String) tableModel.getValueAt(i,4)
                    +"<TD>"+(String) tableModel.getValueAt(i,5));

        }
        printWriter.println("</TABLE>");
        printWriter.close();
    }
}
