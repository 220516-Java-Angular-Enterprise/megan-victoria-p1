package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.dtos.requests.UpdateReimbDescRequest;
import com.revature.ers.models.Reimbursement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ReimbursementServiceTest {
@Spy
private ReimbursementDAO mockReimbDAO;
@InjectMocks
private ReimbursementService testReimbService;
@Spy
    Reimbursement testReimb=new Reimbursement();
@Spy
    NewReimbursementRequest mocknewRRequest= new NewReimbursementRequest();
@Spy
    UpdateReimbDescRequest mockupdateReimbRequest=new UpdateReimbDescRequest();
    @Test
    void saveReimbursement() {
        mocknewRRequest.setAmount(10.00);
        mocknewRRequest.setDescription("food");
        mocknewRRequest.setType_id("Burger and Fries");
        doNothing().when(mockReimbDAO).save(any());
        assertEquals(mocknewRRequest.getDescription(),testReimbService.saveReimbursement(mocknewRRequest).getDescription());
        //tests the exception
        /*mocknewRRequest.setType_id("invalid");
        assertThrows(InvalidRequestException.class,()->testReimbService.saveReimbursement(mocknewRRequest));*/
    }

    @Test
    void updateReimbursement() {
    }

    @Test
    void deleteReimbursement() {
    }

    @Test
    void getById() {
    }

    @Test
    void updateAmount() {
    }

    @Test
    void updateReimbStatus() {
    }

    @Test
    void updateDescription() {
    }
}