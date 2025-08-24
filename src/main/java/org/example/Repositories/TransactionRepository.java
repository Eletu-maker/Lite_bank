package org.example.Repositories;

import org.example.Model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    boolean existsByAccountNumber(String accountNumber);

    Optional<Transaction> findById(String id);


    List<Transaction> findTransactionByAccountNumber(String accountNumber);
}
