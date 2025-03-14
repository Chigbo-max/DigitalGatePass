package org.bytebuilders.services.auth;

import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.dtos.Requests.LogInRequest;
import org.bytebuilders.dtos.Requests.SignUpRequest;
import org.bytebuilders.exceptions.IllegalAuthException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplementTest {

    @Autowired
    private AuthService authService;

    private LogInRequest logInRequest;

    private SignUpRequest signUpRequest;

    @Autowired
    private UsersRepository UsersRepository;
    @Autowired
    private UsersRepository usersRepository;


    @BeforeEach
    void setUp() {
        logInRequest = new LogInRequest();
        signUpRequest = new SignUpRequest();
    }

//    @BeforeEach
//    void tearDown() {
//        usersRepository.deleteAll();
//    }

    @Test
    void testThatUserCanSignUp_getOne() {
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setPassword("password");
        signUpRequest.setRole("RESIDENT");
        signUpRequest.setHomeAddress("BLOCK A");
        authService.signUp(signUpRequest);
        assertEquals(1, usersRepository.count());
    }

    @Test
    void testThatExceptionIsThrownIfHomeAddressIsBlank() {
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setPassword("password");
        signUpRequest.setRole("RESIDENT");
        IllegalAuthException exception = assertThrows(IllegalAuthException.class, () -> authService.signUp(signUpRequest));
        assertEquals("Home address is required", exception.getMessage());

    }


    @Test
    void testThatExceptionIsThrownIfRoleDoesNotExist() {
        signUpRequest.setEmail("test2@email.com");
        signUpRequest.setPassword("password");
        signUpRequest.setRole("TEN");
        IllegalAuthException e = assertThrows(IllegalAuthException.class, () -> authService.signUp(signUpRequest));
        assertEquals("Invalid role: TEN", e.getMessage());
    }
    @Test
    void testThatExceptionIsThrownIfUserAlreadyExistsOnSignUp() {
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setPassword("password");
        signUpRequest.setRole("RESIDENT");
        IllegalAuthException illegalAuthException = assertThrows(IllegalAuthException.class, () -> authService.signUp(signUpRequest));
        assertEquals(illegalAuthException.getMessage(), "User already exists");
    }

    @Test
    void testThatExceptionIsThrowIfPasswordIsWrongUponLogIn() {
        logInRequest.setEmail("test@email.com");
        logInRequest.setPassword("pass");
        IllegalAuthException illegalAuthException = assertThrows(IllegalAuthException.class, () -> authService.login(logInRequest));
        assertEquals("Invalid credentials", illegalAuthException.getMessage());
    }


    @Test
    void testThatExceptionIsThrownIfEmailIsWrongUponLogIn() {
        logInRequest.setEmail("@email.com");
        logInRequest.setPassword("password");
        IllegalAuthException illegalAuthException = assertThrows(IllegalAuthException.class, () -> authService.login(logInRequest));
        assertEquals("Invalid credentials", illegalAuthException.getMessage());
    }

}