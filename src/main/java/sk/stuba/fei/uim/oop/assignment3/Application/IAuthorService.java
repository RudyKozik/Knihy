package sk.stuba.fei.uim.oop.assignment3.Application;

import sk.stuba.fei.uim.oop.assignment3.Domain.Author;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.AuthorRequest;

import java.util.List;

public interface IAuthorService {
    Author create(AuthorRequest request);
    List<Author> getAll();
    Author getById(long id);
    Author update(long id, AuthorRequest request);
    void delete(long id);
    void save(Author author);
}
