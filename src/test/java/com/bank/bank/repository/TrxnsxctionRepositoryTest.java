package com.bank.bank.repository;

import com.bank.bank.entity.Account;
import com.bank.bank.entity.Checking;
import com.bank.bank.entity.Savings;
import com.bank.bank.entity.Trxnsxctions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TrxnsxctionRepositoryTest {

    @MockBean
    TrxnsxctionRepository trxnsxctionRepository;

    @Test
    void findAllByCheckingId() {
        Account account = new Account(1,"Bill",null,null);
        Checking checking = new Checking(1, "Bill's Checking",3,4,account,null);
        Trxnsxctions trxnsxctions1 = new Trxnsxctions(1,"Credit", "ApplesBees Restaurant", 30, checking,null);
        Trxnsxctions trxnsxctions2 = new Trxnsxctions(1,"Credit", "Bestbuy Gadget", 22, checking,null);
        List<Trxnsxctions> trxnsxctionsList = Arrays.asList(trxnsxctions1,trxnsxctions2);


        when(trxnsxctionRepository.findAllByCheckingId(1)).thenReturn(trxnsxctionsList);

        List<Trxnsxctions> fetchTrxnsxctions = trxnsxctionRepository.findAllByCheckingId(1);

        assertNotNull(fetchTrxnsxctions);
        assertEquals("ApplesBees Restaurant", trxnsxctionsList.get(0).getTrxnsxctiondescription());
        assertEquals("Bestbuy Gadget",trxnsxctionsList.get(1).getTrxnsxctiondescription());

        verify(trxnsxctionRepository).findAllByCheckingId(1);

    }

    @Test
    void findAllBySavingsId() {
        Account account = new Account(1,"Bill",null,null);
        Savings savings = new Savings(1, "Bill's Checking",3,4,account,null);
        Trxnsxctions trxnsxctions1 = new Trxnsxctions(1,"Credit", "ApplesBees Restaurant", 30, null,savings);
        Trxnsxctions trxnsxctions2 = new Trxnsxctions(1,"Credit", "Bestbuy Gadget", 22,null,savings);
        List<Trxnsxctions> trxnsxctionsList = Arrays.asList(trxnsxctions1,trxnsxctions2);
        savings.setTrxnsxctions(trxnsxctionsList);

        when(trxnsxctionRepository.findAllBySavingsId(1)).thenReturn(trxnsxctionsList);

        List<Trxnsxctions> fetchTrxnsxctions = trxnsxctionRepository.findAllBySavingsId(1);

        assertNotNull(fetchTrxnsxctions);
        assertEquals("ApplesBees Restaurant", trxnsxctionsList.get(0).getTrxnsxctiondescription());
        assertEquals("Bestbuy Gadget",trxnsxctionsList.get(1).getTrxnsxctiondescription());

        verify(trxnsxctionRepository).findAllBySavingsId(1);
    }

    @Test
    void findByCheckingIdAndCheckingAccountId() {
        Account account = new Account(1,"Bill",null,null);
        Checking checking = new Checking(1, "Bill's Checking",3,4,account,null);
        Trxnsxctions trxnsxctions1 = new Trxnsxctions(1,"Credit", "ApplesBees Restaurant", 30, checking,null);
        Trxnsxctions trxnsxctions2 = new Trxnsxctions(1,"Credit", "Bestbuy Gadget", 22,checking,null);
        List<Trxnsxctions> trxnsxctionsList = Arrays.asList(trxnsxctions1,trxnsxctions2);
        checking.setTrxnsxctions(trxnsxctionsList);

        when(trxnsxctionRepository.findByCheckingIdAndCheckingAccountId(1,1)).thenReturn(trxnsxctionsList);

        List<Trxnsxctions> fetchTrxnsxctions = trxnsxctionRepository.findByCheckingIdAndCheckingAccountId(1,1);

        assertNotNull(fetchTrxnsxctions);
        assertEquals("ApplesBees Restaurant", trxnsxctionsList.get(0).getTrxnsxctiondescription());
        assertEquals("Bestbuy Gadget",trxnsxctionsList.get(1).getTrxnsxctiondescription());

        verify(trxnsxctionRepository).findByCheckingIdAndCheckingAccountId(1,1);
    }

    @Test
    void findBySavingsIdAndSavingsAccountId() {

        Account account = new Account(1,"Bill",null,null);
        Savings savings = new Savings(1, "Bill's Checking",3,4,account,null);
        Trxnsxctions trxnsxctions1 = new Trxnsxctions(1,"Credit", "ApplesBees Restaurant", 30, null,savings);
        Trxnsxctions trxnsxctions2 = new Trxnsxctions(1,"Credit", "Bestbuy Gadget", 22,null,savings);
        List<Trxnsxctions> trxnsxctionsList = Arrays.asList(trxnsxctions1,trxnsxctions2);
        savings.setTrxnsxctions(trxnsxctionsList);

        when(trxnsxctionRepository.findBySavingsIdAndSavingsAccountId(1,1)).thenReturn(trxnsxctionsList);

        List<Trxnsxctions> fetchTrxnsxctions = trxnsxctionRepository.findBySavingsIdAndSavingsAccountId(1,1);

        assertNotNull(fetchTrxnsxctions);
        assertEquals("ApplesBees Restaurant", trxnsxctionsList.get(0).getTrxnsxctiondescription());
        assertEquals("Bestbuy Gadget",trxnsxctionsList.get(1).getTrxnsxctiondescription());

        verify(trxnsxctionRepository).findBySavingsIdAndSavingsAccountId(1,1);
    }
    @Test
    void findBySavingsId() {

        Account account = new Account(1,"Bill",null,null);
        Savings savings = new Savings(1, "Bill's Checking",3,4,account,null);
        Trxnsxctions trxnsxctions1 = new Trxnsxctions(1,"Credit", "ApplesBees Restaurant", 30, null,savings);
        Trxnsxctions trxnsxctions2 = new Trxnsxctions(1,"Credit", "Bestbuy Gadget", 22,null,savings);
        List<Trxnsxctions> trxnsxctionsList = Arrays.asList(trxnsxctions1,trxnsxctions2);
        savings.setTrxnsxctions(trxnsxctionsList);

        when(trxnsxctionRepository.findBySavingsId(1)).thenReturn(trxnsxctionsList);

        List<Trxnsxctions> fetchTrxnsxctions = trxnsxctionRepository.findBySavingsId(1);

        assertNotNull(fetchTrxnsxctions);
        assertEquals("ApplesBees Restaurant", trxnsxctionsList.get(0).getTrxnsxctiondescription());
        assertEquals("Bestbuy Gadget",trxnsxctionsList.get(1).getTrxnsxctiondescription());

        verify(trxnsxctionRepository).findBySavingsId(1);
    }

}