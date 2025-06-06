package com.acsolutions.arnulfocastrillon.acsolutions.application;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IUserRepository;

public class RegistrationService {
    private final IUserRepository iUserRepository;

    public RegistrationService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User register (User user){
        return iUserRepository.save(user);
    }
}
