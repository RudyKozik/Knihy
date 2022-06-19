package sk.stuba.fei.uim.oop.assignment3.Application.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.Domain.LendingList;

import java.util.List;

@Repository
public interface ILendingListRepository extends JpaRepository<LendingList, Long> {
    List<LendingList> findAll();
    LendingList findLendingListById(long id);
}
