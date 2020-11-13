import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CreditCardTest {
    private CreditCard card;

    @BeforeEach
    void setUp() {
        card = new CreditCard("123", "Ruichun", "09/12/2022", "Visa");
    }

    @Test
    void setCardNumberInvalid() throws IllegalAccessException {
        try{
            card.setCardNumber("1234567890987654321234");
            fail("Invalid card number in setCardNumber() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in setCardNumber(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for setCardNumber() with empty argument");
        }
    }

    @Test
    void TestSetCardNumber() throws IllegalAccessException {
        card.setCardHolder("6011142152748129");
        String expResult = "6011142152748129";
        assertEquals(expResult, card.getCardHolder());
    }

    @Test
    void setCardHolderInvalid() throws IllegalAccessException {
        try{
            card.setCardHolder("");
            fail("Empty card holder in setCardHolder() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in setCardHolder(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for setCardHolder() with empty argument");
        }
        try{
            card.setCardHolder("Renee!");
            fail("Wrong card holder name in setCardHolder() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in setCardHolder(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for setCardHolder() with special character");
        }
    }

    @Test
    void testSetCardHolder() throws IllegalAccessException {
        card.setCardHolder("Renee");
        String expResult = "Renee";
        assertEquals(expResult, card.getCardHolder());
    }

    @Test
    void setExpirationDateInvalid() {
        try{
            card.setExpirationDate("");
            fail("Empty card expiration date in setExpirationDate() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in setExpirationDate(): " + e.getMessage());
        }catch (Exception e){
            fail("Wrong exception thrown for setExpirationDate() with empty argument");
        }
        try{
            card.setExpirationDate("06/20/2020");
            fail("Wrong card expiration date in setExpirationDate() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in setExpirationDate(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for setExpirationDate() with passed date");
        }
    }

    @Test
    void testSetExpirationDate() throws Exception {
        card.setExpirationDate("07/11/2024");
        String expResult = "07/11/2024";
        assertEquals(expResult, card.getExpirationDate());
    }
}