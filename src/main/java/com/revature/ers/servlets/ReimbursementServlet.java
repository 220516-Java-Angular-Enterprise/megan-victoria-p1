package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.*;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.User;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;
import com.revature.ers.utils.annotations.Inject;
import com.revature.ers.utils.custom_exceptions.InvalidRequestException;
import com.revature.ers.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//        ONLY DEFAULT USERS CAN POST A REIMBURSEMENT
        try{
            NewReimbursementRequest request = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
            String[] uris = req.getRequestURI().split("/");

            if (uris.length == 4 && uris[3].equals("reimbursement")) {
                Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

                if (requester == null) {
                    resp.setStatus(401);
                    return;
                }

                if (!requester.getRole_id().equals("9")) {
                    resp.setStatus(403); //forbidden access
                    return;
                }
                if (request.getId().equals("")) {
                    resp.setStatus(404); //input error (can't put in blank)
                    return;
                }

//                GETS REIMB BY ID
//                List<Reimbursement> reimb = (List<Reimbursement>) reimbursementService.getById(request.getId());
//                resp.setContentType("application/json");
//                resp.getWriter().write(mapper.writeValueAsString(reimb));
//                return;
            }

            Reimbursement createdReimb = reimbursementService.saveReimbursement(request);
            resp.setStatus(201);//Created
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(createdReimb.getId()));

        } catch (
    InvalidRequestException e){
        resp.setStatus(404);//Bad Requests
    }catch (
    ResourceConflictException e){
        resp.setStatus(409);//Resource Conflict
    }catch (Exception e){
        e.printStackTrace();
        resp.setStatus(500);
    }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        String[] uris = req.getRequestURI().split("/");

        if (requester == null) {
            resp.setStatus(401); //unauthorized
            return;
        }

//        employee update reimb
        if (requester.getRole_id().equals("9")) {
            if (uris.length == 4 && uris[3].equals("new-amt")) {
                UpdateReimbAmountRequest changeAmt = mapper.readValue(req.getInputStream(), UpdateReimbAmountRequest.class);
                reimbursementService.updateAmount(changeAmt);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(changeAmt.getAmount()));
            }

            else if (uris.length == 4 && uris[3].equals("new-desc")) {
                UpdateReimbDescRequest changeDec = mapper.readValue(req.getInputStream(), UpdateReimbDescRequest.class);
                reimbursementService.updateDescription(changeDec);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(changeDec.getDescription()));
            }
        }

        if (requester.getRole_id().equals("10") && uris.length == 4 && uris[3].equals("status")) {
            ApproveDenyRequest status = mapper.readValue(req.getInputStream(), ApproveDenyRequest.class);
            reimbursementService.updateReimbStatus(status);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(status.getStatus_id()));
        }
    }

//    just need to add gets
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
    String[] uris = req.getRequestURI().split("/");

    if (requester == null) {
        resp.setStatus(401); // UNAUTHORIZED
        return;
    }

//    employee gets
    if (requester.getRole_id().equals("9")) {
        if(uris.length == 4 && uris[3].equals("my-reimb")) {
                List<Reimbursement> reimbs;
                reimbs = reimbursementService.getAllByUser(requester.getId());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
//        if(uris.length == 4 && uris[3].equals("pending")) {
//            List<Reimbursement> reimbs = reimbursementService.getAllByS(requester.getId());
//            resp.setContentType("application/json");
//            resp.getWriter().write(mapper.writeValueAsString(reimbs));
//        }
    }

//    FIN MAN GETS
    if (requester.getRole_id().equals("10")) {
        if (uris.length == 4 && uris[3].equals("all-reimb")) {
            List<Reimbursement> reimbs = reimbursementService.getAll();
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
        else if (uris.length == 4 && uris[3].equals("pending")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByStatus("5");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }

        else if (uris.length == 4 && uris[3].equals("approved")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByStatus("6");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
        else if (uris.length == 4 && uris[3].equals("denied")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByStatus("7");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
        else if (uris.length == 4 && uris[3].equals("lodging")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByType("1");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }

        else if (uris.length == 4 && uris[3].equals("travel")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByType("2");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
        else if (uris.length == 4 && uris[3].equals("food")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByType("3");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
        else if (uris.length == 4 && uris[3].equals("other")) {
            List<Reimbursement> reimbs = reimbursementService.getAllByType("4");
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(reimbs));
        }
    }
    }
}

