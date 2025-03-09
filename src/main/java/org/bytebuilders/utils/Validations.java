package org.bytebuilders.utils;

import org.bytebuilders.data.repositories.UsersRepository;
import org.bytebuilders.exceptions.IllegalAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Validations {

    @Autowired
    private UsersRepository usersRepository;


    public void validateEmailAddress(String email) {
        if(usersRepository.existsByEmailAddress(email)){
            throw new IllegalAuthException("User already exists");
        }
    }
}
