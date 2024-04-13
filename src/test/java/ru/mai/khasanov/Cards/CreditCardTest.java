package ru.mai.khasanov.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mai.khasanov.Cards.impl.CreditCard;

public class CreditCardTest {
    private BankCard card;

    @BeforeEach
    void setUp() {
        this.card = new CreditCard(10000);
    }

    @Test
    void debitCardPositiveTest() {
        card.replenish(5000);
        card.pay(5000);
        card.pay(3000);
        card.replenish(2000.12);
        card.replenish(2000);
        card.replenish(501);

        String expectedResult = "Balance: 1501.12, CreditBalance: 10000";
        String actualResult = card.getAvailableBalanceInfo();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void debitCardNegativeTest() {
        card.replenish(5000);

        Assertions.assertTrue(card.pay(15000));
    }

}
