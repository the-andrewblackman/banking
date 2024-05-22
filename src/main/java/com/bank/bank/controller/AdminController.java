package com.bank.bank.controller;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import com.bank.bank.exception.InvalidRequestException;
import com.bank.bank.repository.AccountRepository;
import com.bank.bank.repository.CheckingRepository;
import com.bank.bank.repository.SavingsRepository;
import com.bank.bank.repository.TrxnsxctionRepository;
import com.bank.bank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Transactional
@Validated

public class AdminController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    TrxnsxctionRepository trxnsxctionRepository;
    @Autowired
    BankService bankService;
    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        logger.info("GET: ALL USER ACCOUNTS.");
        return new ResponseEntity<>(bankService.getAllAccounts(), HttpStatus.OK);
    }
    @PostMapping("/accounts/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) throws InvalidRequestException {
        logger.info("POST: CREATE ACCOUNT.");
        return new ResponseEntity<>(bankService.createAccount(account),HttpStatus.OK);
    }
    @GetMapping("/checking")
    public ResponseEntity<List<Checking>> getAllChecking() throws InvalidRequestException {
        logger.info("GET: ALL CHECKING ACCOUNTS.");
        return new ResponseEntity<>(bankService.getAllChecking(), HttpStatus.OK);
    }
    @GetMapping("/savings")
    public ResponseEntity<List<Savings>> getAllSavings() throws InvalidRequestException{
        logger.info("GET: ALL SAVINGS ACCOUNTS.");
        return new ResponseEntity<>(bankService.getAllSavings(),HttpStatus.OK);
    }
    @GetMapping("/txn/all")
    public ResponseEntity<List<Trxnsxctions>> getAllTransactions() throws InvalidRequestException{
        logger.info("GET: ALL TRANSACTIONS.");
        return new ResponseEntity<>(bankService.getAllTransactions(), HttpStatus.OK);
    }
    @GetMapping("/txn/checking/{accountId}/{checkingId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsByAccountIdAndCheckingId(@PathVariable Integer checkingId, @PathVariable Integer accountId) throws InvalidRequestException {
        logger.info("GET: TRANSACTIONS BY ACCOUNT ID AND CHECKING ID.");
        return new ResponseEntity<>(bankService.getTransactionsByCheckingIdAndAccountId(checkingId,accountId),HttpStatus.OK);
    }
    @GetMapping("/txn/savings/{accountId}/{savingsId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsByAccountIdAndSavingsId(@PathVariable Integer savingsId,@PathVariable Integer accountId) throws InvalidRequestException {
        logger.info("GET: TRANSACTIONS BY ACCOUNT ID AND SAVINGS ID.");
        return new ResponseEntity<>(bankService.getTransactionsBySavingsIdAndAccountId(savingsId,accountId),HttpStatus.OK);
    }
}
