package sk.stuba.fei.uim.oop.assignment3.WebApi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Application.Services.BookService;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.AddAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.UpdateBookRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Responses.BookAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Responses.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.Domain.Book;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService _service;

    public BookController(BookService _service) {
        this._service = _service;
    }

    @PostMapping()
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request) {
        var book = _service.create(request);
        if(book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new BookResponse(book), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<BookAmountResponse> addAmount(@PathVariable("id") long id, @RequestBody AddAmountRequest request) {
        var book = _service.addAmount(id, request);
        if(book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new BookAmountResponse(book.getAmount()), HttpStatus.OK);
    }

    @GetMapping()
    public List<BookResponse> getAll() {
        return _service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable("id") long id) {
        var book = _service.getById(id);
        if(book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new BookResponse(book), HttpStatus.OK);
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<BookAmountResponse> getAmount(@PathVariable("id") long id) {
        var book = _service.getById(id);
        if(book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new BookAmountResponse(book.getAmount()), HttpStatus.OK);
    }

    @GetMapping("/{id}/lendCount")
    public ResponseEntity<BookAmountResponse> getLendCount(@PathVariable("id") long id) {
        var book = _service.getById(id);
        if(book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new BookAmountResponse(book.getLendCount()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateAttributes(@PathVariable("id") long id, @RequestBody UpdateBookRequest request) {
        var book = _service.update(id, request);
        if(book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new BookResponse(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> delete(@PathVariable("id") long id) {
        var book = _service.getById(id);
        if(book == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        _service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
