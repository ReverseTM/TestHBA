package ru.mai.khasanov.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mai.khasanov.Cards.impl.BonusDebitCard;

public class BonusDebitCardTest {
    private BankCard card;

    @Test
    void BonusDebitCardPositiveTest1() {
        card = new BonusDebitCard(5000, 5);
        card.replenish(10000);

        String expectedResult = "Balance: 10500";
        String actualResult = card.getAvailableBalanceInfo();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void BonusDebitCardPositiveTest2() {
        card = new BonusDebitCard(9999, 7);
        card.replenish(53564);

        String expectedResult = "Balance: 57313.48";
        String actualResult = card.getAvailableBalanceInfo();

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void BonusDebitCardNegativeTest1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BonusDebitCard(-5, 5));
    }

    @Test
    void BonusDebitCardNegativeTest2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BonusDebitCard(4500, -12));
    }
}
