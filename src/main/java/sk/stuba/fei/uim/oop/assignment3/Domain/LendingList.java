package sk.stuba.fei.uim.oop.assignment3.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class LendingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Book> books;
    private boolean lended;

    public LendingList() {
        books = new ArrayList<>();
    }
}
