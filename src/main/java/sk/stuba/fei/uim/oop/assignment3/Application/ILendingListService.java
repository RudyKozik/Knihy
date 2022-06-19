package sk.stuba.fei.uim.oop.assignment3.Application;

import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.Domain.LendingList;

import java.util.List;

public interface ILendingListService {

    LendingList create();
    List<LendingList> getAll();
    LendingList getById(long id);
    void delete(long id);
    LendingList addBook(long id, BookIdRequest request);
    void lend(long id);
    void deleteBook(long id, BookIdRequest request);
}
