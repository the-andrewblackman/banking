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

    List<Checking> getAllCheckingByAccountId(Integer accountId) throws InvalidRequestException;

    List<Savings> getAllSavings() throws InvalidRequestException;

    List<Savings> getAllSavingsByAccountId(Integer accountId) throws InvalidRequestException;

    String createSavings(CreateSavings createSavings) throws InvalidRequestException;

    String deleteSavingsAccount(Integer id) throws InvalidRequestException;

    public List<Trxnsxctions> getTransactionsByCheckingId(Integer checkingId) throws InvalidRequestException;

    public List<Trxnsxctions> getTransactionsBySavingsId(Integer savingsId) throws InvalidRequestException;
    List<Trxnsxctions> getAllTransactions() throws InvalidRequestException;

    public List<Trxnsxctions> getTransactionsByCheckingIdAndAccountId(Integer checkingId, Integer accountId) throws InvalidRequestException;

    public List<Trxnsxctions> getTransactionsBySavingsIdAndAccountId(Integer savingsId, Integer accountId) throws InvalidRequestException;



    }
