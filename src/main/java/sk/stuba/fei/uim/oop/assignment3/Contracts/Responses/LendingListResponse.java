package sk.stuba.fei.uim.oop.assignment3.Contracts.Responses;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.Domain.LendingList;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LendingListResponse {
    private final long id;
    private final List<BookResponse> lendingList;
    private final boolean lended;

    public LendingListResponse(LendingList list) {
        id = list.getId();
        lendingList = list.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
        lended = list.isLended();
    }
}
