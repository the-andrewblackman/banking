package com.bank.bank.repository;

import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SavingsRepository extends JpaRepository<Savings,Integer> {
    List<Savings> findAllByAccountId(Integer accountId);
    Savings findByName(String accountName);
    @Modifying
    @Transactional
    @Query("DELETE FROM Savings s WHERE s.name = :name AND s.id = :id")
    void deleteByNameAndId(@Param("name") String name, @Param("id") Integer id);



}
