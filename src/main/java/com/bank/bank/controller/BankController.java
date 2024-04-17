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

    @GetMapping ("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(bankService.getAllAccounts(), HttpStatus.OK);
    }
    @PostMapping ("/accounts/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) throws InvalidRequestException {
        return new ResponseEntity<>(bankService.createAccount(account),HttpStatus.OK);
    }
    @GetMapping("/checking")
    public ResponseEntity<List<Checking>> getAllChecking() throws InvalidRequestException {
        return new ResponseEntity<>(bankService.getAllChecking(), HttpStatus.OK);
    }
    @PostMapping("/checking/create")
    public ResponseEntity<String> createChecking(@RequestBody CreateChecking createChecking) {
        return new ResponseEntity<>(bankService.createChecking(createChecking),HttpStatus.OK);
    }
    @DeleteMapping ("/checking/delete")
    public ResponseEntity<String> deleteCheckingAccount(@RequestParam("name") String nameOfAccount){
        return new ResponseEntity<>(bankService.deleteCheckingAccount(nameOfAccount),HttpStatus.OK);
    }
    @GetMapping("/checking/{accountId}")
    public ResponseEntity<List<Checking>> getAllCheckingByAccountId(@PathVariable Integer accountId) throws InvalidRequestException {
        return new ResponseEntity<>(bankService.getAllCheckingByAccountId(accountId),HttpStatus.OK);
    }
    @GetMapping("/savings")
    public ResponseEntity<List<Savings>> getAllSavings() throws InvalidRequestException{
        return new ResponseEntity<>(bankService.getAllSavings(),HttpStatus.OK);
    }
    @GetMapping("/savings/{accountId}")
    public ResponseEntity<List<Savings>> getAllSavingsByAccountId(@PathVariable Integer accountId) throws InvalidRequestException{
        return new ResponseEntity<>(bankService.getAllSavingsByAccountId(accountId),HttpStatus.OK);
    }
    @PostMapping("/savings/create")
    public ResponseEntity<String> createSavings(@RequestBody CreateSavings createSavings) throws InvalidRequestException{
        return new ResponseEntity<>(bankService.createSavings(createSavings),HttpStatus.OK);
    }
    @DeleteMapping ("/savings/delete")
    public ResponseEntity<String> deleteSavingsAccount(@RequestParam("name") String nameOfAccount){
        return new ResponseEntity<>(bankService.deleteSavingsAccount(nameOfAccount),HttpStatus.OK);
    }
    @GetMapping("/txn/checking/{checkingId}/{accountId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsByCheckingAndAccountIds(@PathVariable Integer checkingId, @PathVariable Integer accountId) throws InvalidRequestException{
        return new ResponseEntity<>(bankService.getTransactionsByCheckingAndAccountIds(checkingId,accountId),HttpStatus.OK);
    }
    @GetMapping("/txn/savings/{savingsId}/{accountId}")
    public ResponseEntity<List<Trxnsxctions>> getTransactionsBySavingsAndAccountIds(@PathVariable Integer savingsId, @PathVariable Integer accountId) throws InvalidRequestException{
        return new ResponseEntity<>(bankService.getTransactionsBySavingsAndAccountIds(savingsId,accountId),HttpStatus.OK);
    }



}
