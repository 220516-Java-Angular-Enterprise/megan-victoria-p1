package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.LoginRequest;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
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


    @Test

    void login() {

    LoginRequest testerrequest=new LoginRequest();
    testerrequest.setUsername("Username42");
    testerrequest.setPassword("P@ssw0rd86");
    testUser=new User("1",testerrequest.getUsername(),"",testerrequest.getPassword(),"","",true,"9");

    doReturn(testUser).when(mockDao).getUserByUsernameAndPassword("Username42","P@ssw0rd86");
    assertThrows(InvalidRequestException.class,()->userService.login(testerrequest));


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
}
/*Trying to push*/