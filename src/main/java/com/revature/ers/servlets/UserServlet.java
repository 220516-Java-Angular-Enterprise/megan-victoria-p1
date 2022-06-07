package com.revature.ers.servlets;

import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;
import com.revature.ers.services.UserService;
import com.revature.ers.utils.annotations.Inject;
import com.revature.ers.utils.custom_exceptions.InvalidRequestException;
import com.revature.ers.utils.custom_exceptions.ResourceConflictException;

import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;

    @Inject
    public UserServlet(ObjectMapper mapper, UserService userService){
        this.mapper=mapper;
        this.userService= userService;
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
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

}