package sk.stuba.fei.uim.oop.assignment3.Contracts.Requests;

import lombok.Getter;

@Getter
public class UpdateBookRequest {
    private String name;
    private String description;
    private long author;
    private int pages;
}
