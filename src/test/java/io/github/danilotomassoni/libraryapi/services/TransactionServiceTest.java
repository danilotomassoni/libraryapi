package io.github.danilotomassoni.libraryapi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void saveBookAndAuthorServiceTest(){
        transactionService.saveBookAndAuthor();
    }

    @Test
    public void updateWithoutUpdatingServiceTest(){
        transactionService.updateWithoutUpdating();
    }
}
