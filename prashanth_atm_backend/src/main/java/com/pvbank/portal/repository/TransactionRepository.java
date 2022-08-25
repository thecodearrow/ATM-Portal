package com.pvbank.portal.repository;

import com.pvbank.portal.entity.Account;
import com.pvbank.portal.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query(value = "select t.id,t.timestamp,t.type,t.transactionAmount from Transaction t where t.account=?1")
    List<Object> findByAccount(Account account);

}
