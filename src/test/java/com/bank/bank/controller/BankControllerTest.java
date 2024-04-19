package com.bank.bank.controller;

import com.bank.bank.dto.CreateChecking;
import com.bank.bank.dto.CreateSavings;
import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import com.bank.bank.exception.InvalidRequestException;
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
    public void testGetAllChecking() throws Exception {
        Account account1 = new Account();
        List<Trxnsxctions> list = new ArrayList<>();

        Account account2 = new Account();
        List<Trxnsxctions> kids_list = new ArrayList<>();

        given(bankService.getAllChecking()).willReturn(Arrays
                .asList(new Checking(1,"checking1",9, 100,account1,list),
                        new Checking(2, "checking2", 3,5,account2, kids_list)));

        mockMvc.perform(get("/api/bank/checking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[1].present_balance").value(5));
    }

    @Test
    void getAllCheckingByAccountId() throws Exception {
        List<Checking> checking = new ArrayList<>();
        List<Savings> savings = new ArrayList<>();
        Account billAccount = new Account(1,"bill",checking, savings);
        List<Trxnsxctions> listForBill = new ArrayList<>();

        List<Checking> checking2 = new ArrayList<>();
        List<Savings> savings2 = new ArrayList<>();
        Account edgarAccount = new Account(2,"edgar",checking2,savings2);
        List<Trxnsxctions> listForEdgar = new ArrayList<>();

        // Mock service to return only relevant data based on account ID
        given(bankService.getAllCheckingByAccountId(eq(1))).willReturn(Arrays.asList(
                new Checking(1, "BillsChecking", 9, 100, billAccount, listForBill)
        ));

        mockMvc.perform(get("/api/bank/checking/{accountId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))  // Checks that one checking account is returned
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("BillsChecking"))
                .andExpect(jsonPath("$[0].available_balance").value(9))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[0].account.id").value(1));

        given(bankService.getAllCheckingByAccountId(eq(2))).willReturn(Arrays.asList(
                new Checking(2, "EdgarsChecking", 3, 5, edgarAccount, listForEdgar)
        ));

        mockMvc.perform(get("/api/bank/checking/{accountId}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))  // Checks that one checking account is returned
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].name").value("EdgarsChecking"))
                .andExpect(jsonPath("$[0].available_balance").value(3))
                .andExpect(jsonPath("$[0].present_balance").value(5))
                .andExpect(jsonPath("$[0].account.id").value(2));

    }

    @Test
    void getAllSavings() throws Exception {
        Account account1 = new Account();
        List<Trxnsxctions> list1 = new ArrayList<>();

        Account account2 = new Account();
        List<Trxnsxctions> list2 = new ArrayList<>();

        given(bankService.getAllSavings()).willReturn(Arrays
                .asList(new Savings(1,"Savings1",9, 100, account1,list1),
                        new Savings(2, "Savings2", 3,5, account2, list2)));

        mockMvc.perform(get("/api/bank/savings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[1].present_balance").value(5));
    }

    @Test
    void getAllSavingsByAccountId() throws Exception{

        List<Checking> checking = new ArrayList<>();
        List<Savings> savings = new ArrayList<>();
        Account billAccount = new Account(1,"bill",checking, savings);
        List<Trxnsxctions> listForBill = new ArrayList<>();

        // Mock service to return only relevant data based on account ID
        given(bankService.getAllSavingsByAccountId(eq(1))).willReturn(Arrays.asList(
                new Savings(1, "BillsSavings", 9, 100, billAccount, listForBill)
        ));

        mockMvc.perform(get("/api/bank/savings/{accountId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))  // Checks that one checking account is returned
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("BillsSavings"))
                .andExpect(jsonPath("$[0].available_balance").value(9))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[0].account.id").value(1));
    }

    @Test
    void createSavings() throws Exception{
        given(bankService.createSavings(any(CreateSavings.class))).willReturn("Savings Account Created");

        mockMvc.perform(post("/api/bank/savings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"initial Savings transfer\":500}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Savings Account Created")));
    }

    @Test
    void deleteSavingsAccount() throws Exception {
        given(bankService.deleteSavingsAccount(anyString())).willReturn("Savings Account Deleted");

        mockMvc.perform(delete("/api/bank/savings/delete")
                        .param("name", "Savings Account"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Savings Account Deleted")));
    }

    @Test
    void getTransactionsByCheckingAndAccountIds() throws Exception {
//        List<Checking> checking = new ArrayList<>();
//        List<Savings> savings = new ArrayList<>();
//        Account billAccount = new Account(1,"bill",checking, savings);
//        List<Trxnsxctions> listForBill = new ArrayList<>();
//
//        // Mock service to return only relevant data based on account ID
//        given(bankService.getTransactionsByCheckingAndAccountIds(eq(1),eq(2))).willReturn(Arrays.asList(
//                new Trxnsxctions(1, "Credit", "Applebees dinner", 100, checking, savings );
//        ));
//
//        mockMvc.perform(get("/api/bank/savings/{accountId}", 1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))  // Checks that one checking account is returned
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("BillsSavings"))
//                .andExpect(jsonPath("$[0].available_balance").value(9))
//                .andExpect(jsonPath("$[0].present_balance").value(100))
//                .andExpect(jsonPath("$[0].account.id").value(1));
    }

    @Test
    void getTransactionsBySavingsAndAccountIds() {
    }
}