package ru.mai.khasanov.Cards.impl;

import ru.mai.khasanov.Cards.BankCard;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DebitCard extends BankCard {
    @Override
    public boolean pay(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        BigDecimal decimalAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
        if (decimalAmount.compareTo(balance) <= 0) {
            balance = balance.subtract(decimalAmount);
            return true;
        }
        return false;
    }

    @Override
    public String getAvailableBalanceInfo() {
        return "Balance: " + balance.stripTrailingZeros().toPlainString();
    }
}
