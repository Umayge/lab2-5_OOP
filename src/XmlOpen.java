import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.itextpdf.text.DocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.log4j.Logger;
public class XmlOpen extends JFrame
{

    public static final Logger logFile = Logger.getLogger(XmlOpen.class);
    static String fileNameOpen;
    XmlOpen(String str, DefaultTableModel tableModel, JTable table) throws
            Exception
    {
        FileDialog openXML = new FileDialog(this,str,FileDialog.LOAD);
        openXML.setFile("*.xml");
        openXML.setVisible(true);
        //fileNameOpen = openXML.getDirectory() + openXML.getFile();
        fileNameOpen = "shg.xml";
        if(fileNameOpen.equals("nullnull"))return;

        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Document doc = getDocument();
        doc.getDocumentElement().normalize();
        NodeList nlMan = doc.getElementsByTagName("Man");
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
            tableModel.addRow(new String[]{name,pass,driver_license,mark, number, date});

        }

    }



    public static Document getDocument()
    {
        try {
            DocumentBuilder f = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = f.parse( new File(fileNameOpen));
            return doc;
        }
         catch (ParserConfigurationException | IOException | SAXException e) {
             logFile.error("File Not Found", e);
         }
        return null;
    }
}
