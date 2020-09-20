package RecepeStoreJava.Project;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLLoader implements ILoader {

    @Override
    public Store loadFromXml(String path) throws SAXException, IOException {
        File file = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        //We collect all of the recepe books to a list.
        NodeList recepeBookList = doc.getElementsByTagName("RecepeBook");

        for(int count = 0; count < recepeBookList.getLength(); count++)
        {
            Node  node = recepeBookList.item(count);

            Element recepeBook = (Element) node;
            String bookName = recepeBook.getAttribute("name");
            String bookId = recepeBook.getAttribute("id");
        }

        return null;
    }
    
}
