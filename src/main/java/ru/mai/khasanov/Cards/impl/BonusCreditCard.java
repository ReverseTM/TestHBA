package ru.mai.khasanov.Cards.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
    В данной кредитной карте устанавливается период погашения кредита(repaymentPeriod), здесь период является количеством пополнений,
    если владелец карты восполнил кредитный баланс в течении установленного периода, то ему начислится бонусные средства(bonus)
 */

public final class BonusCreditCard extends CreditCard {
    private final int repaymentPeriod;
    private int remainingPeriod;
    private final BigDecimal bonus;

    public BonusCreditCard(double creditLimit, int period, double bonus) {
        super(creditLimit);
        if (period < 0 || bonus < 0) {
            throw new IllegalArgumentException("Period and bonus must be non-negative");
        }
        this.repaymentPeriod = period;
        this.remainingPeriod = period;
        this.bonus = new BigDecimal(bonus).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void replenish(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        BigDecimal decimalAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);

        if (creditBalance.compareTo(creditLimit) != 0) {
            remainingPeriod -= 1;
            BigDecimal debt = creditLimit.subtract(creditBalance);

            if (decimalAmount.compareTo(debt) >= 0) {
                decimalAmount = decimalAmount.subtract(debt);
                creditBalance = creditLimit;

                if (remainingPeriod >= 0) {
                    decimalAmount = decimalAmount.add(bonus);
                }
                remainingPeriod = repaymentPeriod;

                balance = balance.add(decimalAmount);
            } else {
                creditBalance = creditBalance.add(decimalAmount);
            }
        } else {
            balance = balance.add(decimalAmount);
        }
    }
}
