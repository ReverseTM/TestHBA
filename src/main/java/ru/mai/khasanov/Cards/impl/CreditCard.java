package ru.mai.khasanov.Cards.impl;


import ru.mai.khasanov.Cards.BankCard;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCard extends BankCard {
    protected final BigDecimal creditLimit;
    protected BigDecimal creditBalance;

    public CreditCard(double creditLimit) {
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Credit limit must be non-negative");
        }
        this.creditLimit = new BigDecimal(creditLimit);
        this.creditBalance = new BigDecimal(creditLimit);
    }

    @Override
    public void replenish(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        BigDecimal decimalAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);

        if (creditBalance.compareTo(creditLimit) != 0) {
            BigDecimal debt = creditLimit.subtract(creditBalance);
            if (decimalAmount.compareTo(debt) > 0) {
                decimalAmount = decimalAmount.subtract(debt);
                creditBalance = creditLimit;
                balance = balance.add(decimalAmount);
            } else {
                creditBalance = creditBalance.add(decimalAmount);
            }
        } else {
            balance = balance.add(decimalAmount);
        }
    }

    @Override
    public boolean pay(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        BigDecimal decimalAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);

        if (decimalAmount.compareTo(balance) <= 0) {
            balance = balance.subtract(decimalAmount);
            return true;
        } else if (decimalAmount.compareTo(creditBalance.add(balance)) <= 0) {
            creditBalance = creditBalance.subtract(decimalAmount.subtract(balance));
            balance = BigDecimal.valueOf(0);
            return true;
        }
        return false;
    }

    @Override
    public String getAvailableBalanceInfo() {
        return "Balance: " + balance.stripTrailingZeros().toPlainString() + ", CreditBalance: " + creditBalance.stripTrailingZeros().toPlainString();
    }
}
