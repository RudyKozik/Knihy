package sk.stuba.fei.uim.oop.assignment3.Contracts.Responses;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.Domain.Book;

@Getter
public class BookResponse {
    private final long id;
    private final String name;
    private final String description;
    private final Long author;
    private final int pages;
    private final int amount;
    private final int lendCount;

    public BookResponse(Book book) {
        id = book.getId();
        name = book.getName();
        author = book.getAuthor().getId();
        description = book.getDescription();
        pages = book.getPages();
        amount = book.getAmount();
        lendCount = book.getLendCount();
    }
}
