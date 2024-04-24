package com.bank.bank.controller;

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
import com.bank.bank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/bank")
@Transactional
public class BankController {

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

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);


    @GetMapping ("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        logger.info("GET: ALL USER ACCOUNTS.");
        return new ResponseEntity<>(bankService.getAllAccounts(), HttpStatus.OK);
    }
    @PostMapping ("/accounts/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) throws InvalidRequestException {
        logger.info("POST: CREATE ACCOUNT.");
        return new ResponseEntity<>(bankService.createAccount(account),HttpStatus.OK);
    }
    @GetMapping("/checking")
    public ResponseEntity<List<Checking>> getAllChecking() throws InvalidRequestException {
        logger.info("GET: ALL CHECKING ACCOUNTS.");
        return new ResponseEntity<>(bankService.getAllChecking(), HttpStatus.OK);
    }
    @PostMapping("/checking/create")
    public ResponseEntity<String> createChecking(@RequestBody CreateChecking createChecking) {
        logger.info("POST: CREATE CHECKING ACCOUNT.");
        return new ResponseEntity<>(bankService.createChecking(createChecking),HttpStatus.OK);
    }
    @DeleteMapping ("/checking/delete")
    public ResponseEntity<String> deleteCheckingAccount(@RequestParam("name") String nameOfAccount){
        logger.info("DELETE: CHECKING ACCOUNT.");
        return new ResponseEntity<>(bankService.deleteCheckingAccount(nameOfAccount),HttpStatus.OK);
    }
    @GetMapping("/checking/{accountId}")
    public ResponseEntity<List<Checking>> getAllCheckingByAccountId(@PathVariable Integer accountId) throws InvalidRequestException {
        logger.info("GET: ALL CHECKING BY ACCOUNT ID.");
        return new ResponseEntity<>(bankService.getAllCheckingByAccountId(accountId),HttpStatus.OK);
    }
    @GetMapping("/savings")
    public ResponseEntity<List<Savings>> getAllSavings() throws InvalidRequestException{
        logger.info("GET: ALL SAVINGS ACCOUNTS.");
        return new ResponseEntity<>(bankService.getAllSavings(),HttpStatus.OK);
    }
    @GetMapping("/savings/{accountId}")
    public ResponseEntity<List<Savings>> getAllSavingsByAccountId(@PathVariable Integer accountId) throws InvalidRequestException{
        logger.info("GET: ALL SAVINGS ACCOUNTS BY ACCOUNT ID.");
        return new ResponseEntity<>(bankService.getAllSavingsByAccountId(accountId),HttpStatus.OK);
    }
    @PostMapping("/savings/create")
    public ResponseEntity<String> createSavings(@RequestBody CreateSavings createSavings) throws InvalidRequestException{
        logger.info("POST: CREATE SAVINGS ACCOUNT.");
        return new ResponseEntity<>(bankService.createSavings(createSavings),HttpStatus.OK);
    }
    @DeleteMapping ("/savings/delete")
    public ResponseEntity<String> deleteSavingsAccount(@RequestParam("id") Integer id){
        logger.info("DELETE: SAVINGS ACCOUNT.");
        return new ResponseEntity<>(bankService.deleteSavingsAccount(id),HttpStatus.OK);
    }
    @GetMapping("/txn/checking/{checkingId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsByCheckingId(@PathVariable Integer checkingId) throws InvalidRequestException{
        logger.info("GET: TRANSACTIONS BY CHECKING ID.");
        return new ResponseEntity<>(bankService.getTransactionsByCheckingId(checkingId),HttpStatus.OK);
    }
    @GetMapping("/txn/savings/{savingsId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsBySavingsId(@PathVariable Integer savingsId) throws InvalidRequestException{
        logger.info("GET: TRANSACTIONS BY SAVINGS ID.");
        return new ResponseEntity<>(bankService.getTransactionsBySavingsId(savingsId),HttpStatus.OK);
    }
    @GetMapping("/txn/all")
    public ResponseEntity<List<Trxnsxctions>> getAllTransactions() throws InvalidRequestException{
        logger.info("GET: ALL TRANSACTIONS.");
        return new ResponseEntity<>(bankService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/txn/checking/{accountId}/{checkingId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsByAccountIdAndCheckingId(@PathVariable Integer checkingId,@PathVariable Integer accountId) throws InvalidRequestException {
        logger.info("GET: TRANSACTIONS BY ACCOUNT ID AND CHECKING ID.");
        return new ResponseEntity<>(bankService.getTransactionsByCheckingIdAndAccountId(checkingId,accountId),HttpStatus.OK);
    }

    @GetMapping("/txn/savings/{accountId}/{savingsId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsByAccountIdAndSavingsId(@PathVariable Integer savingsId,@PathVariable Integer accountId) throws InvalidRequestException {
        logger.info("GET: TRANSACTIONS BY ACCOUNT ID AND SAVINGS ID.");
        return new ResponseEntity<>(bankService.getTransactionsBySavingsIdAndAccountId(savingsId,accountId),HttpStatus.OK);
    }


}
