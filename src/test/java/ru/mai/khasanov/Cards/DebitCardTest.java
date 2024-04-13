package ru.mai.khasanov.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mai.khasanov.Cards.impl.DebitCard;

public class DebitCardTest {

    private BankCard card;

    @BeforeEach
    void setUp() {
        this.card = new DebitCard();
    }

    @Test
    void debitCardReplenishMethodNegativeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> card.replenish(-432));
    }

    @Test
    void debitCardPayMethodNegativeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> card.pay(-432));
    }

    @Test
    void debitCardPositiveTest1() {
        card.replenish(5000);
        card.pay(3000);
        card.pay(1000);
        card.replenish(1235.45);

        String expectedResult = "Balance: 2235.45";
        String actualResult = card.getAvailableBalanceInfo();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void debitCardPositiveTest2() {
        card.replenish(1312);
        Assertions.assertFalse(card.pay(1313));
    }

}
