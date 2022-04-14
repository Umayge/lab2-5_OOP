import java.awt.FileDialog;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlOpen extends JFrame
{
    static String fileNameOpen;
    XmlOpen(String str, DefaultTableModel tableModel, JTable table) throws
            Exception
    {
        FileDialog openXML = new FileDialog(this,str,FileDialog.LOAD);
        openXML.setFile("*.xml");
        openXML.setVisible(true);
        fileNameOpen = openXML.getDirectory() + openXML.getFile();
        if(fileNameOpen == null)return;

        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        try
        {
            Document doc = getDocument();
            doc.getDocumentElement().normalize();
            NodeList nlMan = doc.getElementsByTagName("Mame");
            for(int temp = 0; temp< nlMan.getLength(); temp++)
            {
                Node elem = nlMan.item(temp);
                NamedNodeMap attrs = elem.getAttributes();
                String name = attrs.getNamedItem("Name").getNodeValue();
                String pass = attrs.getNamedItem("Pass").getNodeValue();
                String driver_license = attrs.getNamedItem("Driver_License").getNodeValue();
                String mark = attrs.getNamedItem("Mark").getNodeValue();
                String number =attrs.getNamedItem("Number").getNodeValue();
                String date = attrs.getNamedItem("Date").getNodeValue();
                tableModel.addRow(new String[]{pass,driver_license,mark, number, date});

            }
        }
        catch (SAXException saxException)
        {
            saxException.printStackTrace();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }



    private Document getDocument() throws Exception 
    {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(new File(fileNameOpen));
            return doc;
        } 
        catch (Exception exception)
        {
            throw new Exception("XML parsing error!");
        }
        
        
    }
}
