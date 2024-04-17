package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Trxnsxctions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrxnsxctionRepository extends JpaRepository<Trxnsxctions,Integer> {
    List<Trxnsxctions> findAllByChecking_IdAndChecking_Account_Id(Integer checkingId, Integer accountId);
    List<Trxnsxctions> findAllBySavings_IdAndSavings_Account_Id(Integer SavingsId, Integer accountId);

    List<Trxnsxctions> findAllBySavings_Account_Id(Integer accountId);
}