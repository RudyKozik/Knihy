package sk.stuba.fei.uim.oop.assignment3.WebApi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Application.Services.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Responses.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.Domain.Author;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorContoller {

    private final AuthorService _service;

    public AuthorContoller(AuthorService _service) {
        this._service = _service;
    }

    @PostMapping()
    public ResponseEntity<AuthorResponse> add(@RequestBody AuthorRequest request) {
        return new ResponseEntity<>(new AuthorResponse(_service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<AuthorResponse> getAll() {
        return _service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getById(@PathVariable("id") long id) {
        var author = _service.getById(id);
        if(author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new AuthorResponse(author), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAttributes(@PathVariable("id") long id,@RequestBody AuthorRequest request) {
        var author = _service.update(id, request);
        if(author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AuthorResponse(author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponse> delete(@PathVariable("id") long id) {
        var author = _service.getById(id);
        if(author == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        _service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
