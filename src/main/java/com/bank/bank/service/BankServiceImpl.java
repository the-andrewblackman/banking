package com.bank.bank.service;

import com.bank.bank.dto.CreateChecking;
import com.bank.bank.dto.CreateSavings;
import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import com.bank.bank.exception.InvalidRequestException;

import java.util.List;

public interface BankServiceImpl {
    List<Account> getAllAccounts();

    String createAccount(Account account) throws InvalidRequestException;

    List<Checking> getAllChecking() throws InvalidRequestException;

    String createChecking(CreateChecking createChecking);

    String deleteCheckingAccount(String nameOfAccount);

    List<Checking> getAllCheckingByAccountId(Integer accountId);

    List<Savings> getAllSavings();

    List<Savings> getAllSavingsByAccountId(Integer accountId);

    String createSavings(CreateSavings createSavings);

    String deleteSavingsAccount(String nameOfAccount);

    List<Trxnsxctions> getTransactionsByCheckingAndAccountIds(Integer checkingId, Integer accountId);

    List<Trxnsxctions> getTransactionsBySavingsAndAccountIds(Integer savingsId, Integer accountId);

}
