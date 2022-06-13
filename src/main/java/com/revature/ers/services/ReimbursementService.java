package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
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
    public void saveReimbursement(Reimbursement reimbursement){reimbursementDAO.save(reimbursement);}
    public void updateReimbursement(Reimbursement reimbursement){reimbursement.update(reimbursement);}

    public void deleteReimbursement(String id){reimbursementDAO.delete(id);}
    public Reimbursement getById(String id){return reimbursementDAO.getById(id);}
    public List<Reimbursement>getAll(){return  reimbursementDAO.getAll();}
}

