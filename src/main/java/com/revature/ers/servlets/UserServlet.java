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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//    will get info (request sorts/info users would need)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        NewUserRequest userRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);

//        this will allow us to get the actual flow of the user
        String[] uris = req.getRequestURI().split("/");
//        this will allow admin to get by what ever search param
//        look at 1st letter? search by: User, Role, Status
        String query = req.getQueryString();
        resp.setContentType("application/json");

        if (requester == null) {
            resp.setStatus(401); //unauthorized
            return;
        }

        if (!requester.getRole_id().equals("8")) {
            resp.setStatus(403); // FORBIDDEN
            return;
        }

        if (uris.length == 4 && uris[3].equals("users")) {
            List<User> users = userService.getAllUsers();
            resp.getWriter().write(mapper.writeValueAsString(users));
        }

//        wont work b/c contextLoadListener maps to auth and users/* need to figure out
        if (uris.length == 4 && uris[3].equals("username")) {
            List<User> users = userService.getUserByUsername(userRequest.getUsername());
            resp.getWriter().write(mapper.writeValueAsString(users));
        }

        if (uris.length == 5 && uris[4].equals("role")) {
            List<User> users = userService.getUserByRole(userRequest.getRole_id());
            resp.getWriter().write(mapper.writeValueAsString(users));
        }

    }

//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//    need to get the user info
//    need to get the updated info and call user service function to update the DAO
//    maybe need new dtos request for change password/change status

//    changing status same as pswd


//        Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
//        String newPassword = HttpServletRequest.getParameter("newPassword");
//        String reNewPassword = HttpServletRequest.getParameter("reNewPassword");
//        String id = HttpServletRequest.getRemoteUser();
//
//        try {
//
//            Principal user = requester.getUsername();
//
//        if (requester == null) {
//            resp.setStatus(401); //unauthorized
//            return;
//        }
//
//        if (!requester.getRole_id().equals("8")) {
//            resp.setStatus(403); // FORBIDDEN
//            return;
//        }
////          Will read input
//            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//            String newPassword = br.readLine();
//
//            User
//
//        }catch (InvalidRequestException e){
//            resp.setStatus(404);//Bad Requests
//        }catch (ResourceConflictException e){
//            resp.setStatus(409);//Resource Conflict
//        }catch (Exception e){
//            e.printStackTrace();
//            resp.setStatus(500);
//        }
//    }

        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            String id = retrieve
            Principal requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            NewUserRequest userRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            String[] uris = req.getRequestURI().split("/");
            String query = req.getQueryString();
            resp.setContentType("application/json");

            if (requester == null) {
                resp.setStatus(401); //unauthorized
                return;
            }

            if (!requester.getRole_id().equals("8")) {
                resp.setStatus(403); // FORBIDDEN
                return;
            }

//            to update password
//            if()
        }

        private static String getUserId(HttpServletRequest req) {
//        String pathInfo = req.getPathInfo();
//        if (pathInfo.startsWith("/")) {
//            pathInfo = pathInfo.substring(1);
//        }
//        return String.pathInfo;
            return null;
        }

        private void updatePassword() {


        }

}

