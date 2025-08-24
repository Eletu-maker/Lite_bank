package org.example.Repositories;

import org.example.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    boolean existsByAccountNumber(String accountNumber);


}
