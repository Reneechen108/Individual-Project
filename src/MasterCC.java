class MasterCC extends CreditCard {

    public MasterCC(String number, String holder, String date, String type) {
        super(number, holder, date, type);
    }

    @Override
    public void printDescription(){
        System.out.println("This is MasterCard class!");
        System.out.println("Credit card [card number= " + cardNumber + ", " +
                "card holder= " + cardHolder + ", exirationDate=" + expirationDate + "]");
    }
}