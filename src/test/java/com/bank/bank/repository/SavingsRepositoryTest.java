package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SavingsRepositoryTest {

    @MockBean
    SavingsRepository savingsRepository;

    @Test
    void findAllByAccountId() {

        Account account = new Account(1, "Bill", null, null);
        Savings savings = new Savings(1, "Bill's Savings", 4, 4, account, new ArrayList<>());
        List<Savings> mockedList = Arrays.asList(savings);

        // Mockito stubbing
        when(savingsRepository.findAllByAccountId(1)).thenReturn(mockedList);

        List<Savings> fetchedList = savingsRepository.findAllByAccountId(1);

        assertFalse(fetchedList.isEmpty());
        assertEquals(1, fetchedList.size());
        assertEquals("Bill's Savings", fetchedList.get(0).getName());

        // Verify that the method was called once
        verify(savingsRepository).findAllByAccountId(1);

    }

    @Test
    void findByName() {
        Account account = new Account(1,"Bill", null, null);
        Savings savings = new Savings(1,"Bill's Savings", 4,4, account, new ArrayList<>());

        // Stub
        when(savingsRepository.findByName("Bill's Savings")).thenReturn(savings);

        Savings saver = savingsRepository.findByName("Bill's Savings");

        assertNotNull(saver);
        assertEquals("Bill's Savings", saver.getName());

        verify(savingsRepository).findByName("Bill's Savings");
    }
}