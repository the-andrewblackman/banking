package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Trxnsxctions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CheckingRepository extends JpaRepository<Checking,Integer> {

    List<Checking> findAllByAccountId(Integer accountId);
    Checking findByName(String accountName);
    @Modifying
    @Transactional
    @Query("DELETE FROM Checking c WHERE c.name = :name")
    void deleteByName(@Param("name") String name);
}
