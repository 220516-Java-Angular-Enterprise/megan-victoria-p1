package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.User;
import com.revature.ers.services.TokenService;
import com.revature.ers.services.UserService;
import com.revature.ers.utils.annotations.Inject;
import com.revature.ers.utils.custom_exceptions.InvalidRequestException;
import com.revature.ers.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;
    private final TokenService tokenService;

    @Inject
    public UserServlet(ObjectMapper mapper, UserService userService, TokenService tokenService){
        this.mapper=mapper;
        this.userService= userService;
        this.tokenService = tokenService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        super.doPost(req, resp);
        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
//            get uris so we can map the route and see permissions
            String[] uris = req.getRequestURI().split("/");

            if (uris.length == 4 && uris[3].equals("username")) {
                Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

                if (requester == null) {
                    resp.setStatus(401); //unauthorized error
                    return;
                }

                if (!requester.getRole_id().equals("8")) {
                    resp.setStatus(403); //forbidden access
                    return;
                }

                if (request.getUsername().equals("")) {
                    resp.setStatus(404); //input error (can't put in blank)
                    return;
                }

                List<User> users = userService.getUserByUsername(request.getUsername());
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(users));
                return;
            }

            User createdUser = userService.register(request);
            resp.setStatus(201);//Created
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getId()));
        }catch (InvalidRequestException e){
            resp.setStatus(404);//Bad Requests
        }catch (ResourceConflictException e){
            resp.setStatus(409);//Resource Conflict
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if (requester == null) {
            resp.setStatus(401); //unauthorized
            return;
        }

        if (!requester.getRole_id().equals("8")) {
            resp.setStatus(403); // FORBIDDEN
            return;
        }

        List<User> users = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(users));
    }
}

