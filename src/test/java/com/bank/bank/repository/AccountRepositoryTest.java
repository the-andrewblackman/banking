package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountRepositoryTest {

    @MockBean
    AccountRepository accountRepository;

    @Test
    void findByName() {

        Account account = new Account(1,"Bill",null,null);

        // stub
        when(accountRepository.findByName("Bill")).thenReturn(account);

        Account fetchAccount = accountRepository.findByName("Bill");

        assertNotNull(fetchAccount);
        assertEquals("Bill",account.getName());

        verify(accountRepository).findByName("Bill");

    }
}