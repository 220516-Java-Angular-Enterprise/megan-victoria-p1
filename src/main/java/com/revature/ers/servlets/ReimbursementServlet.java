package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;
import com.revature.ers.utils.annotations.Inject;

import javax.servlet.http.HttpServlet;

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
    }}

   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        try{
            NewReimbursementRequest newReimbursementRequest= mapper.readValue(req.getInputStream(),NewReimbursementRequest.class);
            String[] uris= req.getRequestURI().split("/");

            if(uris.length==4 && uris[3].equals("reimbursement")){
                Principal requester=tokenService.extractRequesterDetails(req.getHeader("Reimbursement"));
            }

        }
    }
}*/
