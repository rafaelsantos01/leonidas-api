package com.spantanTech.leonidas.modules.users.services.confirmationUser;

import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.modules.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfirmationStatusUserService {

    @Autowired
    UsersRepository usersRepository;


    public void execute(UUID idUser) {

        Users users = usersRepository.findById(idUser).orElse(null);

        if(users == null){
            throw new Error("UserNotFound");
        }

        if(users.isStatus()){
            throw new Error("StatusIsActive");
        }

        users.setStatus(true);

        usersRepository.saveAndFlush(users);
    }
}
