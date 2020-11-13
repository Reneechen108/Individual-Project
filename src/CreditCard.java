import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreditCard{

    String cardNumber;
    String cardHolder;
    String expirationDate;
    String errorType;
    String cardType;

    final static String DATE_FORMAT = "MM/dd/yyyy";

    public CreditCard(){};

    public CreditCard(String number, String holder, String date, String type) {
        cardNumber = number;
        cardHolder = holder;
        expirationDate = date;
        cardType = type;
    }

    public void setCardNumber(String number) throws IllegalAccessException {
        if (number.length() == 0 || number.length() > 19){
            errorType = "Invalid card number(larger than 19 or empty)";
            this.setErrorType(errorType);
            throw new IllegalAccessException(errorType);
        }
        if (Integer.parseInt(number) < 0){
            errorType = "Invalid card number(cannot be negative number)";
            this.setErrorType(errorType);
            throw new IllegalAccessException(errorType);
        }
        cardNumber = number;
    }


    public void setCardHolder(String name) throws IllegalAccessException {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean b = m.find();
        if (b){
            System.out.println("name");
            errorType = "Card holder name cannot contain special character";
            this.setErrorType(errorType);
            throw new IllegalAccessException(errorType);
        }
        if(name.length() > 0){
            cardHolder = name;
        }
        else{
            errorType = "Card holder name cannot be empty";
            this.setErrorType(errorType);
            throw new IllegalAccessException(errorType);
        }
    }

    public void setExpirationDate(String date) throws IllegalAccessException, ParseException {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            errorType = "Invalid expiration date";
            this.setErrorType(errorType);
            throw new IllegalAccessException(errorType);
        }
        if (sdf.parse(date).before(new Date())) {
            errorType = "Expiration date has already passed";
            this.setErrorType(errorType);
            throw new IllegalAccessException(errorType);
        }
        if(date.length() > 0){
            expirationDate = date;
        }
    }

    public void setType(String type) {
        this.cardType = type;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getCardHolder(){
        return cardHolder;
    }
    public String getExpirationDate(){
        return expirationDate;
    }
    public String getType(){
        return cardType;
    }
    public String getErrorType(){
        return errorType;
    }

    public void printDescription(){
        System.out.println("This is Credit Card class!");
        System.out.println("Credit card [card number= " + cardNumber + ", " +
                "card holder= " + cardHolder + ", exirationDate=" + expirationDate + "]");
    }
}