package ru.mai.khasanov;

import ru.mai.khasanov.Cards.BankCard;
import ru.mai.khasanov.Cards.impl.CreditCard;

public class Main {
    public static void main(String[] args) {
        BankCard card = new CreditCard(10000);
        card.pay(3000);
        card.replenish(423);
        card.replenish(4523.32);
        card.replenish(2158);
        System.out.println(card.getAvailableBalanceInfo());
    }
}