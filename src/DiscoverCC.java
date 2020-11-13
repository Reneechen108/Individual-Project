public class DiscoverCC extends CreditCard{

    public DiscoverCC(String number, String holder, String date, String type) {
        super(number, holder, date, type);
    }

    @Override
    public void printDescription(){
        System.out.println("This is Discover class!");
        System.out.println("Credit card [card number= " + cardNumber + ", " +
                "card holder= " + cardHolder + ", exirationDate=" + expirationDate + "]");
    }
}