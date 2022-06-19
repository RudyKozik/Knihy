package sk.stuba.fei.uim.oop.assignment3.Domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private int pages;
    private int amount;
    private int lendCount;
    @ManyToOne
    private Author author;

    public Book(){
        author = new Author();
    }
}
