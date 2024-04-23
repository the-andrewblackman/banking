package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import org.junit.jupiter.api.Test;
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
class CheckingRepositoryTest {
    @MockBean
    CheckingRepository checkingRepository;

    @Test
    void findAllByAccountId() {
        Account account = new Account(1,"Bill", null, null);
        Checking checking = new Checking(1, "Bill's Checking",32,4,account,new ArrayList<>());
        List<Checking> list = Arrays.asList(checking);

        //Stub
        when(checkingRepository.findAllByAccountId(1)).thenReturn(list);

        List<Checking> fetchedList = checkingRepository.findAllByAccountId(1);

        assertFalse(fetchedList.isEmpty());
        assertEquals(1, fetchedList.size());
        assertEquals("Bill's Checking", fetchedList.get(0).getName());

        verify(checkingRepository).findAllByAccountId(1);

    }

    @Test
    void findByName() {

        Account account = new Account(1,"Bill", null, null);
        Checking checking = new Checking(1, "Bill's Checking",32,4,account,new ArrayList<>());
        //Stub
        when(checkingRepository.findByName("Bill's Checking")).thenReturn(checking);

        Checking fetchChecking = checkingRepository.findByName("Bill's Checking");

        assertNotNull(fetchChecking);
        assertEquals("Bill's Checking", checking.getName());

        verify(checkingRepository).findByName("Bill's Checking");
    }
}