import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class CsvFile<CSVWriter> {

    public static ArrayList<CreditCard> clist = new ArrayList<CreditCard>();

    public void readCSV(String file) throws Exception {
        if (!file.substring(file.length()-4, file.length()).equals(".csv"))
            throw new IllegalAccessException("This is not a csv file");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {

            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] creditCards = line.split(cvsSplitBy);
                String cardNumber = creditCards[0];
                String expirationDate = creditCards[1];
                String cardHolder = creditCards[2];
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
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        } catch (IOException e) {
            throw new IOException("IO Exception");
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


    public void writeCSV(String file) throws Exception {
        if (!file.substring(file.length()-4, file.length()).equals(".csv"))
            throw new IllegalAccessException("This is not a csv file");
        try {
            FileWriter f = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(f);
            for (CreditCard c : clist) {
//                System.out.println(c.getErrorType());
                String tmpString = "";
                if (c.getType() != "Credit Card") {
                    tmpString = c.cardNumber + "," + c.getType();
                }else{
                    tmpString = c.getErrorType();
                }
                writer.write(tmpString);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            throw new IOException("IO Exception");
        } catch (Exception e){
            throw new Exception("Exception throw");
        }
    }
}
