package com.bank.bank.controller;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import com.bank.bank.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;
    @Test
    public void getAllAccounts() throws Exception {

        given(bankService.getAllAccounts()).willReturn(Arrays.asList(new Account(1,"Sample Account", new ArrayList<>(), new ArrayList<>()),
                new Account(2, "Second Account",new ArrayList<>(),new ArrayList<>())));

        mockMvc.perform(get("/admin/accounts")
                .header("X-Allow-Testing", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Sample Account"))
                .andExpect(jsonPath("$[1].name").value("Second Account"));
    }

    @Test
    public void testCreateAccount() throws Exception {
        given(bankService.createAccount(any(Account.class))).willReturn("Account New Account made successfully");

        mockMvc.perform(post("/admin/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"New Account\"}")
                        .header("X-Allow-Testing", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Account New Account made successfully")));
    }

    @Test
    public void testGetAllChecking() throws Exception {
        given(bankService.getAllChecking()).willReturn(Arrays.asList(new Checking(1,"checking1",9, 100,new Account(), new ArrayList<>()),
                new Checking(2, "checking2", 3,5, new Account(), new ArrayList<>())));

        mockMvc.perform(get("/admin/checking")
                        .header("X-Allow-Testing", "true"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[1].present_balance").value(5));
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

        mockMvc.perform(get("/admin/savings")
                        .header("X-Allow-Testing", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].present_balance").value(100))
                .andExpect(jsonPath("$[1].present_balance").value(5));
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        Account account = new Account(1, "Bill", null, null);
        Checking checking = new Checking(1, "checking_1", 5, 5, account, new ArrayList<>());
        Trxnsxctions transaction1 = new Trxnsxctions(1, "credit", "Applebees dinner", 30, checking, null);

        Account account2 = new Account(2, "Edgar", null, null);
        Checking checking2 = new Checking(1, "checking_1", 5, 5, account2, new ArrayList<>());
        Trxnsxctions transaction2 = new Trxnsxctions(1, "credit", "Best Buy gadget", 30, checking2, null);

        given(bankService.getAllTransactions()).willReturn(Arrays.asList(transaction1,transaction2));

        mockMvc.perform(get("/admin/txn/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Allow-Testing", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].trxnsxctiontype").value("credit"))
                .andExpect(jsonPath("$[0].trxnsxctiondescription").value("Applebees dinner"))
                .andExpect(jsonPath("$[0].amount").value(30))
                .andExpect(jsonPath("$[0].checking.id").value(1))
                .andExpect(jsonPath("$[0].checking.name").value("checking_1"))
                .andExpect(jsonPath("$[0].checking.available_balance").value(5))
                .andExpect(jsonPath("$[0].checking.present_balance").value(5))
                .andExpect(jsonPath("$[0].checking.account.id").value(1))
                .andExpect(jsonPath("$[0].checking.account.name").value("Bill"))
                .andExpect(jsonPath("$[1].id").value(1))
                .andExpect(jsonPath("$[1].trxnsxctiontype").value("credit"))
                .andExpect(jsonPath("$[1].trxnsxctiondescription").value("Best Buy gadget"))
                .andExpect(jsonPath("$[1].amount").value(30))
                .andExpect(jsonPath("$[1].checking.id").value(1))
                .andExpect(jsonPath("$[1].checking.name").value("checking_1"))
                .andExpect(jsonPath("$[1].checking.available_balance").value(5))
                .andExpect(jsonPath("$[1].checking.present_balance").value(5))
                .andExpect(jsonPath("$[1].checking.account.id").value(2))
                .andExpect(jsonPath("$[1].checking.account.name").value("Edgar"));

    }
    @Test
    void getTransactionsByCheckingIdAndAccountId() throws Exception {


        Account billAccount = new Account(1,"Bill",new ArrayList<>(),new ArrayList<>());
        Checking checking = new Checking(1,"Bill's checking",1,2,billAccount, new ArrayList<>());

        Trxnsxctions trxnsxctions = new Trxnsxctions(1, "credit", "Applebees dinner", 100, checking, null );

        given(bankService.getTransactionsByCheckingIdAndAccountId(eq(1),eq(1))).willReturn(Arrays.asList(trxnsxctions));

        mockMvc.perform(get("/admin/txn/checking/{accountId}/{checkingId}", 1,1)
                        .header("X-Allow-Testing", "true"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))  // Checks that one checking account is returned
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].trxnsxctiontype").value("credit"))
                .andExpect(jsonPath("$[0].trxnsxctiondescription").value("Applebees dinner"))
                .andExpect(jsonPath("$[0].amount").value(100))
                .andExpect(jsonPath("$[0].checking.id").value(1))
                .andExpect(jsonPath("$[0].checking.account.id").value(1))
                .andExpect(jsonPath("$[0].checking.account.name").value("Bill"))
                .andExpect(jsonPath("$[0].savings").isEmpty());

    }

    @Test
    void getTransactionsBySavingsAndAccountIds() throws Exception {

        Account billAccount = new Account(1,"Bill", new ArrayList<>(), new ArrayList<>());
        Savings savings = new Savings(1,"Bill's savings",1,2,billAccount, new ArrayList<>());

        Trxnsxctions trxnsxctions = new Trxnsxctions(1, "credit", "Applebees dinner", 100, null, savings );

        given(bankService.getTransactionsBySavingsIdAndAccountId(eq(1),eq(1))).willReturn(Arrays.asList(trxnsxctions));

        mockMvc.perform(get("/admin/txn/savings/{savingsId}/{accountId}", 1, 1)
                        .header("X-Allow-Testing", "true"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))) // Checks that one transaction is returned
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].trxnsxctiontype").value("credit"))
                .andExpect(jsonPath("$[0].trxnsxctiondescription").value("Applebees dinner"))
                .andExpect(jsonPath("$[0].amount").value(100))
                .andExpect(jsonPath("$[0].checking").isEmpty())
                .andExpect(jsonPath("$[0].savings.id").value(1))
                .andExpect(jsonPath("$[0].savings.account.id").value(1))
                .andExpect(jsonPath("$[0].savings.account.name").value("Bill"));
    }
}