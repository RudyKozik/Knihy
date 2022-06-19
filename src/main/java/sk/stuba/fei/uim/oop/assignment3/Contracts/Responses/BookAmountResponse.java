package sk.stuba.fei.uim.oop.assignment3.Contracts.Responses;

import lombok.Getter;

@Getter
public class BookAmountResponse {
    private final int amount;

    public BookAmountResponse(int amount) {
        this.amount = amount;
    }
}
