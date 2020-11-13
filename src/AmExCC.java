class AmExCC extends CreditCard {

    public AmExCC(String number, String holder, String date, String type) {
        super(number, holder, date, type);
    }

    @Override
    public String getType(){
        return "American Express";
    }
}