package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;
import com.revature.ers.utils.annotations.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;

    private final ReimbursementService reimbursementService;

    private final TokenService tokenService;

    @Inject
    public ReimbursementServlet(ObjectMapper mapper, ReimbursementService reimbursementService,TokenService tokenService) {
        this.mapper = mapper;
        this.reimbursementService = reimbursementService;
        this.tokenService=tokenService;
    }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        try{
            NewReimbursementRequest request= mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);

            }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);

        }
    }
}
