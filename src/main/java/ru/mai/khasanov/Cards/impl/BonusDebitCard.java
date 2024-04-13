package ru.mai.khasanov.Cards.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BonusDebitCard extends DebitCard {
    private final BigDecimal threshold;
    private final BigDecimal percentageBonus;

    public BonusDebitCard(double threshold, double percentageBonus) {
        if (threshold < 0 || percentageBonus < 0) {
            throw new IllegalArgumentException("Threshold and percentageBonus must be non-negative");
        }

        this.threshold = new BigDecimal(threshold);
        this.percentageBonus = new BigDecimal(percentageBonus / 100).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void replenish(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        BigDecimal decimalAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);

        if (decimalAmount.compareTo(threshold) >= 0) {
            decimalAmount = decimalAmount.add(decimalAmount.multiply(percentageBonus));
        }
        balance = balance.add(decimalAmount);
    }
}
