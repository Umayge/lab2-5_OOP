import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tthread extends Thread
{
    private int type;
    DefaultTableModel model;
    JTable tableBase;
    public Tthread(int i, DefaultTableModel model1, JTable tableBase1 )
    {
        type = i;
        model = model1;
        tableBase = tableBase1;
    }
    public Tthread(int i, DefaultTableModel model1)
    {
       type = i;
       model = model1;
    }
    public void run()
    {
        if(type == 1)
        {
            Object shared =  new Object();
            synchronized (shared)
            {
                    try {
                        XmlOpen xmlOpen = new XmlOpen("Загрузка данных",model,tableBase);
                    }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(type == 2)
        {
            Object shared =  new Object();
            synchronized (shared)
            {

                model.addRow(new String[]{"Малинова Евгения Денисовна","4072 407901","4011 539037","Tesla","С 791 Н С","2022-04-3"});
                shared.notifyAll();
            }
        }
        if(type == 3)
        {
            Object shared =  new Object();
            synchronized (shared)
            {
                try {
                    XmlSave xmlSave = new XmlSave("Сохранение данных",model);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(type == 4)
        {
            Object shared =  new Object();
            synchronized (shared)
            {
                try {
                    pdfSave qwe = new pdfSave("Сохранение таблици В PDF", model);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    htmlSave  qwe2 = new htmlSave("Сохранение таблици в html", model);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
