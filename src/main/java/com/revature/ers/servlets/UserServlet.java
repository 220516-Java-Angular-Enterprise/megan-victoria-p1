package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.ChangePasswordRequest;
import com.revature.ers.dtos.requests.ChangeUserRole;
import com.revature.ers.dtos.requests.IsActiveRequest;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        super.doPost(req, resp);
        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
//            get uris so we can map the route and see permissions
            String[] uris = req.getRequestURI().split("/");

//            Returns single user
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

//            creates a new user
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
//    will get info (request sorts/info users would need)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this will allow us to get the actual flow of the user
        String[] uris = req.getRequestURI().split("/");

        if (uris.length == 4 && uris[3].equals("is-active")) {
            Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

            if (requester == null) {
                resp.setStatus(401); //unauthorized error
                return;
            }

            if (!requester.getRole_id().equals("8")) {
                resp.setStatus(403); //forbidden access
                return;
            }


//            List<User> users = userService.getUserStatus();
//            resp.setContentType("application/json");
//            resp.getWriter().write(mapper.writeValueAsString(users));

        }
    }

        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            String[] uris = req.getRequestURI().split("/");

            if (requester == null) {
                resp.setStatus(401); //unauthorized
                return;
            }

            if (!requester.getRole_id().equals("8")) {
                resp.setStatus(403); // FORBIDDEN
                return;
            }

//            to update password
            if(uris.length == 4 && uris[3].equals("change-pswd")) {
                ChangePasswordRequest changePswd = mapper.readValue(req.getInputStream(), ChangePasswordRequest.class);
                userService.updatePassword(changePswd);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(changePswd.getPassword()));
            }

//            update role
            else if(uris.length == 4 && uris[3].equals("change-role")) {
                ChangeUserRole changeRole = mapper.readValue(req.getInputStream(), ChangeUserRole.class);
                userService.changeUserRole(changeRole);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(changeRole.getRole_id()));
            }

//            update is active
            else if(uris.length == 4 && uris[3].equals("change-status")) {
                IsActiveRequest isActive = mapper.readValue(req.getInputStream(), IsActiveRequest.class);
                userService.changeUserStatus(isActive);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(isActive.getIs_active()));
            }
    }
}

