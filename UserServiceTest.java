package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;
import com.revature.ers.utils.custom_exceptions.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT )
class UserServiceTest {
    @Spy
    private UserDAO mockDao;
    @InjectMocks
    private UserService userService;

@Spy
User testUser=new User();
    private UserDAO userDao;
@Spy
    NewUserRequest testUserRequest=new NewUserRequest();
@Spy
    List<String> testUsername= new ArrayList<>();
@Spy
List<User> testUserList= new ArrayList<User>();






    @Test


    void login() {

    LoginRequest testerrequest=new LoginRequest();
    testerrequest.setUsername("Username42");
    testerrequest.setPassword("P@ssw0rd86");
    testUser=new User("1",testerrequest.getUsername(),"",testerrequest.getPassword(),"","",true,"9");

   /* doReturn(testUser).when(mockDao).getUserByUsernameAndPassword("Username42","P@ssw0rd86");
    assertThrows(AuthenticationException.class,()->userService.login(testerrequest));*/


    }
@Mock
private UserService underTest;


@BeforeEach
void setUp(){
    underTest =new UserService(mockDao);
}

    @Test
    void cangetAllUsers(){
    //when
        underTest.getAllUsers();
        //then
        Mockito.verify(mockDao).getAll();
    }



    @Test
    void getUserByUsername() {
    underTest.getUserByUsername(String.valueOf(testUser));
    Mockito.verify(mockDao).getUsersByUsername(String.valueOf(testUser));
    }

    @Test

    void register() {
    testUser.setUsername("Taken123");
    testUserList.add(testUser);
    testUserRequest.setUsername("Taken123");
    doReturn(testUserList).when(mockDao).getAll();
    //assertThrows(ResourceConflictException.class,()->userService.register(testUserRequest));
    /*Invalid Username*/
        testUserRequest.setUsername("null");
        assertThrows(InvalidRequestException.class,()->userService.register(testUserRequest));
        /*Invalid Password*/
        testUserRequest.setUsername("VicTrott9");
        testUserRequest.setPassword("not");
        assertThrows(InvalidRequestException.class,()->userService.register(testUserRequest));
        /*Valid User*/
        testUserRequest.setUsername("VicTrott95");
        testUserRequest.setPassword("P@ssw0rd");

        doNothing().when(mockDao).save(any());
        assertEquals(testUserRequest.extractUser().getUsername(),userService.register(testUserRequest).getUsername());
        assertEquals(testUserRequest.extractUser().getPassword(),userService.register(testUserRequest).getPassword());

    }

    @Test
    void getUserByRole() {underTest.getUserByRole(String.valueOf(testUser));
        Mockito.verify(mockDao).getUsersByRole(String.valueOf(testUser));
    }

    @Test
    void getUserStatus() {
    }

   /* @Test
    @Disabled
    void updatePassword() {
    doNothing().when(mockDao).updateUserPassword(any(),any());
    userService.updatePassword(test);
    }*/

    @Test
    void changeUserStatus() {
    }

    @Test
    void changeUserRole() {
    }
}

/*Trying to push still*/