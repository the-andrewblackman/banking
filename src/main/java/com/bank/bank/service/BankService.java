package com.bank.bank.service;

import com.bank.bank.controller.BankController;
import com.bank.bank.dto.CreateChecking;
import com.bank.bank.dto.CreateSavings;
import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import com.bank.bank.exception.InvalidRequestException;
import com.bank.bank.repository.AccountRepository;
import com.bank.bank.repository.CheckingRepository;
import com.bank.bank.repository.SavingsRepository;
import com.bank.bank.repository.TrxnsxctionRepository;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService implements BankServiceImpl{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    TrxnsxctionRepository trxnsxctionRepository;

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    public List<Account> getAllAccounts(){
        logger.info("SERVICE: GET ALL ACCOUNTS.");
        List<Account> list = accountRepository.findAll();

        return list;
    }
    public String createAccount(Account account) throws InvalidRequestException {
        logger.info("SERVICE: CREATE ACCOUNT.");
        try {
            Account accountMade = accountRepository.save(account);
            if(accountMade != null){
                return String.format("Account %s made successfully.",account.getName());
            }
        } catch (DataException e){
            String message = String.format("Invalid request, please check data: %s", account);
            throw new InvalidRequestException(message);
        }
        return String.format("Account %s was unsuccessful. Please try again.", account.getName());
    }
    public List<Checking> getAllChecking() throws InvalidRequestException {

        logger.info("SERVICE: GET ALL CHECKING.");

        try {
            List<Checking> list = checkingRepository.findAll();
            return list.stream().map(Checking::checkingDTO).collect(Collectors.toList());

        } catch (DataAccessException e) {
            String message = "Data access problem. Please try again.";
            throw new InvalidRequestException(message);
        }
    }
    public String createChecking(CreateChecking createChecking) {
        logger.info("SERVICE: CREATE CHECKING.");
        Account existingAccount = accountRepository.findByName(createChecking.getUserName());

        if (existingAccount != null) {
            Checking existingCheckingName = checkingRepository.findByName(createChecking.getAccountName());

            if (existingCheckingName == null) {
                Checking checkingMade = Checking.builder()
                        .name(createChecking.getAccountName())
                        .available_balance(0)
                        .present_balance(0)
                        .account(existingAccount)
                        .build();

                Checking newAccount = checkingRepository.save(checkingMade);
                return new String("Checking account created successfully.");
            } else {
                return new String("Please use unique name. Checking account name already used : " + createChecking.getAccountName());
            }
        }else{
            return new String("Username not found : " + createChecking.getUserName());
        }
    }
    public String deleteCheckingAccount(String nameOfAccount){
        logger.info("SERVICE: DELETE CHECKING ACCOUNT.");
        try{

            checkingRepository.deleteByName(nameOfAccount);
        }catch(DataAccessException e){
            return new String("Server error, please try again.");
        }
        return new String("Checking account deleted.");
    }
    public List<Checking> getAllCheckingByAccountId(Integer accountId) throws InvalidRequestException{
        logger.info("SERVICE: GET ALL CHECKING BY ACCOUNT ID.");
        try {
            List<Checking> list = checkingRepository.findAllByAccountId(accountId);
            return list;
        }catch(DataAccessException e){
            String message = String.format("Data access problem. Please try again. %",e);
            throw new InvalidRequestException(message);
        }
    }
    public List<Savings> getAllSavings() throws InvalidRequestException{
        logger.info("SERVICE: GET ALL SAVINGS.");
        try {
            List<Savings> list = savingsRepository.findAll();
            List<Savings> savings = list.stream().map(Savings::savingsDTO).collect(Collectors.toList());
            return savings;
        }catch(DataAccessException e){
            String message =  String.format("Data access problem. Please try again. %",e);
            throw new InvalidRequestException(message);
        }
    }
    public List<Savings> getAllSavingsByAccountId(Integer accountId) throws InvalidRequestException{
        logger.info("SERVICE: GET ALL SAVINGS BY ACCOUNT ID.");
        try {
            List<Savings> list = savingsRepository.findAllByAccountId(accountId);
            return list;
        } catch (DataAccessException e){
            String message = String.format("Data access problem. Please try again. %",e);
            throw new InvalidRequestException(message);
        }
    }
    public String createSavings(CreateSavings createSavings) throws InvalidRequestException {
        logger.info("SERVICE: CREATE SAVINGS ACCOUNT");
        Account existingAccount = accountRepository.findByName(createSavings.getUserName());
        try {
            if (existingAccount != null) {
                Savings existingSavingsName = savingsRepository.findByName(createSavings.getAccountName());

                if (existingSavingsName == null) {
                    Savings savingsMade = Savings.builder()
                            .name(createSavings.getAccountName())
                            .available_balance(0)
                            .present_balance(0)
                            .account(existingAccount)
                            .build();

                    Savings newAccount = savingsRepository.save(savingsMade);
                    return new String("Savings account created successfully.");
                } else {
                    return new String("Please use unique name. Savings account name already used : " + createSavings.getAccountName());
                }
            } else {
                return new String("Username not found : " + createSavings.getUserName());
            }
        }catch(DataAccessException e){
            String message = String.format("Data access problem. Please try again. %",e);
            throw new InvalidRequestException(message);
        }
    }

    public String deleteSavingsAccount(Integer savingsId){
        logger.info("SERVICE: DELETE SAVINGS ACCOUNT");
        List<Trxnsxctions> relatedTransactions = trxnsxctionRepository.findBySavingsId(savingsId);

        if (!relatedTransactions.isEmpty()) {
            // Handle related transactions, e.g., delete or reassign
            trxnsxctionRepository.deleteAll(relatedTransactions);
        }

        savingsRepository.deleteById(savingsId);
        return "savings account deleted";
    }
    public List<Trxnsxctions> getTransactionsByCheckingId(Integer checkingId) throws InvalidRequestException {
        logger.info("SERVICE: GET TRANSACTIONS BY CHECKING ID");
        try {
            List<Trxnsxctions> transactions = trxnsxctionRepository.findAllByCheckingId(checkingId);
            return transactions.stream()
                    .map(Trxnsxctions::checkingDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            String message = String.format("Data access problem. Please try again. %s", e.getMessage());
            throw new InvalidRequestException(message);
        }
    }
    public List<Trxnsxctions> getTransactionsBySavingsId(Integer savingsId) throws InvalidRequestException{
        logger.info("SERVICE: GET TRANSACTIONS BY SAVINGS ID");
        try {
            List<Trxnsxctions> transactions = trxnsxctionRepository.findAllBySavingsId(savingsId);
            return transactions.stream()
                    .map(Trxnsxctions::savingsDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            String message = String.format("Data access problem. Please try again. %s", e.getMessage());
            throw new InvalidRequestException(message);
        }
    }
    public List<Trxnsxctions> getAllTransactions() throws InvalidRequestException{
        logger.info("SERVICE: GET ALL TRANSACTIONS");
        List<Trxnsxctions> list = trxnsxctionRepository.findAll();
        return list;
    }
    public List<Trxnsxctions> getTransactionsByCheckingIdAndAccountId(Integer checkingId, Integer accountId) throws InvalidRequestException {
        logger.info("SERVICE: GET TRANSACTIONS BY ACCOUNT ID AND CHECKING ID");
        try {
            List<Trxnsxctions> transactions = trxnsxctionRepository.findByCheckingIdAndCheckingAccountId(checkingId, accountId);
            return transactions.stream()
                    .map(Trxnsxctions::checkingDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            String message = String.format("Data access problem. Please try again. %s", e.getMessage());
            throw new InvalidRequestException(message);
        }
    }
    public List<Trxnsxctions> getTransactionsBySavingsIdAndAccountId(Integer savingsId, Integer accountId) throws InvalidRequestException {
        logger.info("SERVICE: GET TRANSACTIONS BY ACCOUNT ID AND SAVINGS ID");
        try {
            List<Trxnsxctions> transactions = trxnsxctionRepository.findBySavingsIdAndSavingsAccountId(savingsId, accountId);
            return transactions.stream()
                    .map(Trxnsxctions::savingsDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            String message = String.format("Data access problem. Please try again. %s", e.getMessage());
            throw new InvalidRequestException(message);
        }
    }


    }
