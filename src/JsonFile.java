//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonFile {

    public static ArrayList<CreditCard> clist = new ArrayList<CreditCard>();

    public static void readJSON(String file) throws ParseException, IOException, IllegalAccessException {
        if (!file.substring(file.length()-5, file.length()).equals(".json"))
            throw new IllegalAccessException("This is not a json file");
        try {
            String text = new String(Files.readAllBytes(Paths.get(file)));
            JSONArray arr = new JSONArray(text);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject creditCard = arr.getJSONObject(i);
                CreditCardFactory cf = new CreditCardFactory();

                BigDecimal bd = creditCard.getBigDecimal("CardNumber");
                BigInteger num = bd.toBigInteger();
                String cardNumber;
                if(num.toString().length() >=    19){
                    cardNumber = "";
                }else{
                    cardNumber = num.toString();
                }
                String expirationDate = (String) creditCard.get("ExpirationDate");
                String cardHolder = (String) creditCard.get("NameOfCardholder");
                CreditCard x = cf.createCard(cardNumber.toString(), cardHolder, expirationDate);
                if(x.getType() == "Credit Card"){
                    x.setCardNumber(cardNumber.toString());
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
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeJSON(String file) throws IOException, IllegalAccessException {
        if (!file.substring(file.length()-5, file.length()).equals(".json"))
            throw new IllegalAccessException("This is not a json file");
        try (FileWriter outfile = new FileWriter(file)) {
            JSONArray arr = new JSONArray();
            //HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
            for (CreditCard c : clist) {
                JSONObject json = new JSONObject();
                if (c.getType() != "Credit Card") {
                    json.put("CardNumber:", Long.parseLong(c.cardNumber));
                    json.put("CardType:", c.getType());
                } else {
                    json.put("ErrorType:", c.getErrorType());
                }
                arr.put(json);
            }
            outfile.write(arr.toString(2));
            outfile.flush();
        } catch (IOException | JSONException e) {
            throw new IOException("IO Exception");
        }
    }
}
