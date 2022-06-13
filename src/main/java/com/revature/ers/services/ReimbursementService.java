package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.dtos.requests.*;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.utils.annotations.Inject;

import java.util.List;

public class ReimbursementService {
    @Inject
    private final ReimbursementDAO reimbursementDAO;
@Inject
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
    this.reimbursementDAO=reimbursementDAO;
    }
    public Reimbursement saveReimbursement(NewReimbursementRequest reimbursement){
    Reimbursement reimb = reimbursement.extractReimbursement();
    reimbursementDAO.save(reimb);
    return reimb;
}
    public void updateReimbursement(Reimbursement reimbursement){reimbursement.update(reimbursement);}

    public void deleteReimbursement(String id){reimbursementDAO.delete(id);}
    public Reimbursement getById(String id){return reimbursementDAO.getById(id);}
    public List<Reimbursement>getAll(){return  reimbursementDAO.getAll();}

    public List<Reimbursement> getAllByType(String type_id) {
        return reimbursementDAO.getReimbursementByTypeId(type_id);
    }

    public List<Reimbursement> getAllByStatus(String status_id) {
        return reimbursementDAO.getReimbursementByStatusId(status_id);
    }

    public List<Reimbursement> getAllByUser(String author_id) {
        return reimbursementDAO.getAllByUser(author_id);
    }

    public void updateAmount(UpdateReimbAmountRequest updateReimbAmountRequest) {
        reimbursementDAO.updateReimbAmount(updateReimbAmountRequest.getAmount(), updateReimbAmountRequest.getId());
    }

    public void updateReimbStatus(ApproveDenyRequest approveDenyRequest) {
        reimbursementDAO.updateStatus(approveDenyRequest.getStatus_id(), approveDenyRequest.getId());
    }

    public void updateDescription(UpdateReimbDescRequest updateReimbDescRequest) {
        reimbursementDAO.updateReimbDescription(updateReimbDescRequest.getDescription(), updateReimbDescRequest.getId());
    }
}

