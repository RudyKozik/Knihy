package sk.stuba.fei.uim.oop.assignment3.Application.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.Domain.Author;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();
    Author findAuthorById(long id);
}
