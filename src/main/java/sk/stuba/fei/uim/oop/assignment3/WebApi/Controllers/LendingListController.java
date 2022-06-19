package sk.stuba.fei.uim.oop.assignment3.WebApi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.Application.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Responses.LendingListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingListController {

    private final ILendingListService _service;

    public LendingListController(ILendingListService _service) {
        this._service = _service;
    }

    @PostMapping()
    public ResponseEntity<LendingListResponse> create() {
        return new ResponseEntity<>(new LendingListResponse(_service.create()), HttpStatus.CREATED);
    }

    @PostMapping("{id}/add")
    public ResponseEntity<LendingListResponse> addBook(@PathVariable("id") long id,@RequestBody BookIdRequest request) {
        try {
            return new ResponseEntity<>(new LendingListResponse(_service.addBook(id, request)), HttpStatus.OK);
        }catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatus());
        }
    }

    @GetMapping()
    public List<LendingListResponse> getAll() {
        return _service.getAll().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LendingListResponse> getById(@PathVariable("id") long id) {
        var lendingList = _service.getById(id);
        if(lendingList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new LendingListResponse(lendingList), HttpStatus.OK);
    }

    @GetMapping("{id}/lend")
    public ResponseEntity<LendingListResponse> lendList(@PathVariable("id") long id) {
        try {
            _service.lend(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatus());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LendingListResponse> delete(@PathVariable("id") long id) {
        var lendingList = _service.getById(id);
        if(lendingList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        _service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<LendingListResponse> deleteBook(@PathVariable("id") long id, @RequestBody BookIdRequest request) {
        var lendingList = _service.getById(id);
        if(lendingList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        _service.deleteBook(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
