import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class XmlFile {

    public static ArrayList<CreditCard> clist = new ArrayList<CreditCard>();

    public void readXMl(String file) throws IOException, IllegalAccessException {
        if (!file.substring(file.length()-4, file.length()).equals(".xml"))
            throw new IllegalAccessException("This is not a xml file");
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("row");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String cardNumber = eElement.getElementsByTagName("CardNumber")
                            .item(0).getTextContent();

                    String expirationDate = eElement.getElementsByTagName("ExpirationDate")
                            .item(0).getTextContent();

                    String cardHolder = eElement.getElementsByTagName("NameOfCardholder")
                            .item(0).getTextContent();

                    CreditCardFactory cf = new CreditCardFactory();
                    CreditCard x = cf.createCard(cardNumber, cardHolder, expirationDate);
                    if(x.getType() == "Credit Card"){
                        x.setCardNumber(cardNumber);
                        x.setCardHolder(cardHolder);
                        x.setExpirationDate(expirationDate);
                    }else {
                        clist.add(x);
                        x.printDescription();
                    }
                }
            }
        } catch (FileNotFoundException | ParserConfigurationException e) {
            throw new FileNotFoundException("File not found");
        } catch (IOException e) {
            throw new IOException("IO Exception");
        } catch (SAXException e) {
            throw new IOException("SAXException Exception");
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            CreditCard card = new CreditCard();
            card.setType("Credit Card");
            card.setErrorType(e.getMessage());
            clist.add(card);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void writeXML(String file) throws Exception {
        if (!file.substring(file.length()-4, file.length()).equals(".xml"))
            throw new IllegalAccessException("This is not a xml file");
        Document dom;
        Element row = null, cardNumber = null, cardHolder = null, expirationDate = null, errorType = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("root");
            for (CreditCard c : clist) {
                row = dom.createElement("row");
                if (c.getType() != "Credit Card") {
                    cardNumber = dom.createElement("CardNumber");
                    cardNumber.appendChild(dom.createTextNode(c.cardNumber));
                    row.appendChild(cardNumber);
                    expirationDate = dom.createElement("CardType");
                    expirationDate.appendChild(dom.createTextNode(c.getType()));
                    row.appendChild(expirationDate);
                }else{
                    errorType = dom.createElement("ErrorType");
                    errorType.appendChild(dom.createTextNode(c.getErrorType()));
                    row.appendChild(errorType);
                }
                rootEle.appendChild(row);
            }
            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(file)));

            } catch (TransformerException te) {
                throw new TransformerException("Transformer Exception");
            } catch (IOException ioe) {
                throw new IOException("IO Exception");
            }
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Parser Configuration Exception");
        } catch (Exception e) {
            throw new Exception("Throw Exception");
        }
    }
}
