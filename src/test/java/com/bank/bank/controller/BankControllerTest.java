package com.bank.bank.controller;

import com.bank.bank.dto.CreateChecking;
import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import com.bank.bank.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BankControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Test
    public void testGetAllAccounts() throws Exception {
        List<Checking> checking = new ArrayList<>();
        List<Savings> savings = new ArrayList<>();

        List<Checking> checking2 = new ArrayList<>();
        List<Savings> savings2 = new ArrayList<>();

        given(bankService.getAllAccounts()).willReturn(Arrays.asList(new Account(1,"Sample Account", checking, savings),
                new Account(2, "Second Account",checking2,savings2)));

        mockMvc.perform(get("/api/bank/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Sample Account"))
                .andExpect(jsonPath("$[1].name").value("Second Account"));
    }

    @Test
    public void testCreateAccount() throws Exception {
        given(bankService.createAccount(any(Account.class))).willReturn("Account New Account made successfully");

        mockMvc.perform(post("/api/bank/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"New Account\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Account New Account made successfully")));
    }

    @Test
    public void testGetAllChecking() throws Exception {
        Account newAccount = new Account();
        List<Trxnsxctions> list = new ArrayList<>();

        Account kidsAccount = new Account();
        List<Trxnsxctions> kids_list = new ArrayList<>();

        given(bankService.getAllChecking()).willReturn(Arrays
                .asList(new Checking(1,"myChecking",9, 100,newAccount,list),
                        new Checking(2, "kidsChecking", 3,5,kidsAccount, kids_list)));

        mockMvc.perform(get("/api/bank/checking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[1].present_balance").value(5));
    }

    @Test
    public void testCreateChecking() throws Exception {
        given(bankService.createChecking(any(CreateChecking.class))).willReturn("Checking Account Created");

        mockMvc.perform(post("/api/bank/checking/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"initialDeposit\":500}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Checking Account Created")));
    }

    @Test
    public void testDeleteCheckingAccount() throws Exception {
        given(bankService.deleteCheckingAccount(anyString())).willReturn("Checking Account Deleted");

        mockMvc.perform(delete("/api/bank/checking/delete")
                        .param("name", "Sample Account"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Checking Account Deleted")));
    }

    @Test
    void getAllCheckingByAccountId() {
    }

    @Test
    void getAllSavings() {
    }

    @Test
    void getAllSavingsByAccountId() {
    }

    @Test
    void createSavings() {
    }

    @Test
    void deleteSavingsAccount() {
    }

    @Test
    void getTransactionsByCheckingAndAccountIds() {
    }

    @Test
    void getTransactionsBySavingsAndAccountIds() {
    }
}