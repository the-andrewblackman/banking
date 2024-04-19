package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Trxnsxctions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrxnsxctionRepository extends JpaRepository<Trxnsxctions,Integer> {
    List<Trxnsxctions> findAllByCheckingId(Integer checkingId);
    List<Trxnsxctions> findAllBySavingsId(Integer savingsId);

    List<Trxnsxctions> findByCheckingIdAndCheckingAccountId(Integer checkingId, Integer accountId);
    List<Trxnsxctions> findBySavingsId(Integer savingsId);

}