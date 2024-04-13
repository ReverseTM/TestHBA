package ru.mai.khasanov.Cards;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public abstract class BankCard {
    protected BigDecimal balance;

    public BankCard() {
        this.balance = new BigDecimal(0);
    }

    public void replenish(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.balance = balance.add(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_EVEN));
    }

    public abstract boolean pay(double amount);

    public abstract String getAvailableBalanceInfo();
}
