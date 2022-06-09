package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.services.UserService;
import com.revature.ers.utils.annotations.Inject;
import com.revature.ers.utils.custom_exceptions.AuthenticationException;
import com.revature.ers.utils.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Inject
private final ObjectMapper mapper;
    private final UserService userService;

    @Inject
    public AuthServlet(ObjectMapper mapper, UserService userService){
        this.mapper=mapper;
        this.userService=userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        try {
            LoginRequest request = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = new Principal(userService.login(request));
            resp.setStatus(200);//Success
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(principal));
        }
        catch (InvalidRequestException e)
        {
            resp.setStatus(404);
        }
        catch (AuthenticationException e){
            resp.setStatus(401);
        }

        catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.getWriter().write("<h1> Hello!!</h1>");
    }
}
