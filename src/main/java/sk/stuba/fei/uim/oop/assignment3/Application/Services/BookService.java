package sk.stuba.fei.uim.oop.assignment3.Application.Services;

import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Application.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.Application.IBookService;
import sk.stuba.fei.uim.oop.assignment3.Application.Repositories.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.Domain.Book;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.AddAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.UpdateBookRequest;

import java.util.List;

@Service
public class BookService implements IBookService {

    private final IBookRepository repository;
    private final IAuthorService authorService;

    public BookService(IBookRepository repository, IAuthorService authorService) {
        this.repository = repository;
        this.authorService = authorService;
    }

    @Override
    public Book create(BookRequest request) {
        var book = new Book();
        var author = this.authorService.getById(request.getAuthor());
        if(author == null) {
            return null;
        }

        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setPages(request.getPages());
        book.setAmount(request.getAmount());
        book.setLendCount(request.getLendCount());

        author.getBooks().add(book);
        book.setAuthor(author);

        this.repository.save(book);
        this.authorService.save(author);
        return book;
    }

    @Override
    public Book addAmount(long id, AddAmountRequest request) {
        var book = getById(id);
        if (book == null) {
            return null;
        }

        book.setAmount(book.getAmount() + request.getAmount());
        return this.repository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book getById(long id) {
        return this.repository.findBookById(id);
    }


    @Override
    public Book update(long id, UpdateBookRequest request) {
        var book = getById(id);
        if (book == null) {
            return null;
        }

        if (request.getAuthor() > 0) {
            var author = authorService.getById(request.getAuthor());
            if (author == null) {
                return null;
            }

            book.setAuthor(author);
            author.getBooks().add(book);
            this.authorService.save(author);
        }

        String name = (request.getName() == null || request.getName().equals("")) ? book.getName() : request.getName();
        book.setName(name);

        String surname = (request.getDescription() == null || request.getDescription().equals("")) ? book.getDescription() : request.getDescription();
        book.setDescription(surname);

        int pages = (request.getPages() <= 0) ? book.getPages() : request.getPages();
        book.setPages(pages);

        return this.repository.save(book);
    }

    @Override
    public void delete(long id) {
        var book = getById(id);
        var author = book.getAuthor();
        author.getBooks().remove(book);
        authorService.save(author);
        this.repository.delete(book);
    }

    @Override
    public void save(Book book) {
        this.repository.save(book);
    }
}
