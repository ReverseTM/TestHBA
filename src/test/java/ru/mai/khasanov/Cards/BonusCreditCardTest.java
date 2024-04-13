package ru.mai.khasanov.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mai.khasanov.Cards.impl.BonusCreditCard;
import ru.mai.khasanov.Cards.impl.BonusDebitCard;

public class BonusCreditCardTest {
    private BankCard card;

    @Test
    void bonusCreditCardPositiveTest1() {
        card = new BonusCreditCard(3000, 3, 500);

        card.pay(2000);
        card.replenish(2000);

        String expectedResult = "Balance: 500, CreditBalance: 3000";
        String actualResult = card.getAvailableBalanceInfo();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void bonusCreditCardPositiveTest2() {
        card = new BonusCreditCard(50000, 2, 1000);

        card.pay(50000);
        card.replenish(25000);
        card.replenish(1000);
        card.replenish(24000);

        String expectedResult = "Balance: 0, CreditBalance: 50000";
        String actualResult = card.getAvailableBalanceInfo();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void bonusCreditCardNegativeTest1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BonusCreditCard(5000, 3, -1));
    }

    @Test
    void bonusCreditCardNegativeTest2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BonusCreditCard(55000, -5, 5000));
    }
}
