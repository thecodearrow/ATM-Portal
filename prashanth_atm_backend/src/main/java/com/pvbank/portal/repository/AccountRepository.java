package com.pvbank.portal.repository;

import com.pvbank.portal.entity.Account;
import com.pvbank.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByDebitCardNumberAndPin(String debitCardNumber,String pin);
     @Query(value = "select a.debitCardNumber from Account a where a.user=?1")
    public List<String> findDebitCardNumbersByUser(User user);
}
