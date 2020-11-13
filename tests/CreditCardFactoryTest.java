import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardFactoryTest {
    private CreditCardFactory cardf;

    @BeforeEach
    void setUp() {
        cardf = new CreditCardFactory();
    }

    @Test
    void createCardVisa() {
        cardf.createCard("4556828904412461", "Rachel", "08/09/2024");
        String expResult = "Visa Card";
        assertEquals(expResult, cardf.getType());
    }

    @Test
    void createCardMaster() {
        cardf.createCard("5572373659951402", "Alex", "07/09/2024");
        String expResult = "Master Card";
        assertEquals(expResult, cardf.getType());
    }

    @Test
    void createCardAmerican() {
        cardf.createCard("342203970468689", "David", "01/23/2024");
        String expResult = "American Express Card";
        assertEquals(expResult, cardf.getType());
    }

    @Test
    void createCardDiscover() {
        cardf.createCard("6011289819386826", "Duke", "05/01/2023");
        String expResult = "Discover Card";
        assertEquals(expResult, cardf.getType());
    }
}