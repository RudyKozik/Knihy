package sk.stuba.fei.uim.oop.assignment3.Application.Services;

import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Application.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.Application.Repositories.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.Domain.Author;
import sk.stuba.fei.uim.oop.assignment3.Contracts.Requests.AuthorRequest;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    private final IAuthorRepository repository;

    public AuthorService(IAuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setSurname(request.getSurname());

        return this.repository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author getById(long id) {
        return this.repository.findAuthorById(id);
    }

    @Override
    public Author update(long id, AuthorRequest request) {
        Author author = getById(id);
        if(author == null)
            return null;

        String name = (request.getName() == null || request.getName().equals("")) ? author.getName() : request.getName();
        author.setName(name);

        String surname = (request.getSurname() == null || request.getSurname().equals("")) ? author.getSurname() : request.getSurname();
        author.setSurname(surname);

        return this.repository.save(author);
    }

    @Override
    public void delete(long id) {
        this.repository.delete(getById(id));
    }

    @Override
    public void save(Author author) {
        this.repository.save(author);
    }
}
