package sk.stuba.fei.uim.oop.assignment3.Application;

import sk.stuba.fei.uim.oop.assignment3.Domain.Book;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.AddAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.UpdateBookRequest;

import java.util.List;

public interface IBookService {
    Book create(BookRequest request);
    Book addAmount(long id, AddAmountRequest request);
    List<Book> getAll();
    Book getById(long id);
    Book update(long id, UpdateBookRequest request);
    void delete(long id);
    void save(Book book);
}
