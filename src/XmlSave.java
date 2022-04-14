import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;



public class XmlSave extends JFrame
{
    XmlSave(String str, DefaultTableModel tableModel) throws  Exception
    {
        FileDialog saveXML = new FileDialog(this,str,FileDialog.SAVE);
        saveXML.setFile("*.xml");
        saveXML.setVisible(true);
        String fileNameSave  = saveXML.getDirectory() + saveXML.getFile();
        if(fileNameSave == null)return;
        Document doc = getDocument();
        Node manlist =doc.createElement("Manlist");
        doc.appendChild(manlist);
        for(int i = 0;i<tableModel.getRowCount();i++)
        {
            Element man =doc.createElement("Man");
            manlist.appendChild(man);
            man.setAttribute("Name",(String) tableModel.getValueAt(i,0));
            man.setAttribute("Pass",(String) tableModel.getValueAt(i,1));
            man.setAttribute("Driver_License",(String) tableModel.getValueAt(i,2));
            man.setAttribute("Mark",(String) tableModel.getValueAt(i,3));
            man.setAttribute("Number",(String) tableModel.getValueAt(i,4));
            man.setAttribute("Date", (String) tableModel.getValueAt(i,5));
        }
        try
        {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD,"xml");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(fileNameSave)));
        }
        catch (TransformerConfigurationException transformerConfigurationException)
        {
            transformerConfigurationException.printStackTrace();
        }
        catch (TransformerException transformerException)
        {
            transformerException.printStackTrace();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    private static Document getDocument() throws Exception
    {
        try
        {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.newDocument();
        }
        catch (Exception exception)
        {
            throw new Exception("XML parsing error!");
        }

    }
}
