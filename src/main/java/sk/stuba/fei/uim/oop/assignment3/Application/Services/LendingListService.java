package sk.stuba.fei.uim.oop.assignment3.Application.Services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.Application.IBookService;
import sk.stuba.fei.uim.oop.assignment3.Application.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.Application.Repositories.ILendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.Domain.LendingList;

import java.util.List;

@Service
public class LendingListService implements ILendingListService {

    private final ILendingListRepository repository;
    private final IBookService bookService;

    public LendingListService(ILendingListRepository repository, IBookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Override
    public LendingList create() {
        return this.repository.save(new LendingList());
    }

    @Override
    public List<LendingList> getAll() {
        return this.repository.findAll();
    }

    @Override
    public LendingList getById(long id) {
        return this.repository.findLendingListById(id);
    }

    @Override
    public void delete(long id) {
        var lendingList = getById(id);
        if(lendingList.isLended()) {
            var books = lendingList.getBooks();
            for (var book:
                    books) {
                book.setLendCount(book.getLendCount() - 1);
                this.bookService.save(book);
            }
        }
        this.repository.delete(lendingList);
    }

    @Override
    public LendingList addBook(long id, BookIdRequest request) throws ResponseStatusException {
        var lendingList = getById(id);
        var book = this.bookService.getById(request.getId());

        if (lendingList == null || book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(lendingList.getBooks().contains(book) || lendingList.isLended()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        lendingList.getBooks().add(book);
        return this.repository.save(lendingList);
    }

    @Override
    public void lend(long id) throws ResponseStatusException {
        var lendingList = getById(id);
        if (lendingList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(lendingList.isLended()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        lendingList.setLended(true);
        var books = lendingList.getBooks();
        for (var book:
             books) {
            book.setLendCount(book.getLendCount() + 1);
            this.bookService.save(book);
        }
        this.repository.save(lendingList);
    }

    @Override
    public void deleteBook(long id, BookIdRequest request) {
        var lendingList = getById(id);
        var book = this.bookService.getById(request.getId());
        lendingList.getBooks().remove(book);
        this.repository.save(lendingList);
    }
}
