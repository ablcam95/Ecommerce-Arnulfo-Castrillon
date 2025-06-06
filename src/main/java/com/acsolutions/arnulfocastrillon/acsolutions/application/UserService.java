package com.acsolutions.arnulfocastrillon.acsolutions.application;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IUserRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public class UserService {
    private final IUserRepository iUserRepository;
    private UserEntity userEntity;
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;

    }

    public User save(User user) {
        return this.iUserRepository.save(user);
    }

    public User findById(Integer id) {
        return this.iUserRepository.findById(id);
    }

    public User findByEmail(String email){
        return this.iUserRepository.findByEmail(email);}

    public Iterable<User> findByActivoTrue() {
        return iUserRepository.findByActivoTrue();
    }

    @Transactional
    public boolean updateActivo(Integer id) {
        if (iUserRepository.existsById(id)) {
            iUserRepository.updateActivo(id);
            return true; // Operación exitosa
        }
        return false; // Usuario no encontrado
    }

    @Transactional
    public void reactivateUser(Integer userId) {
        iUserRepository.reactivateUser(userId);
    }

    public Iterable<User> findByActivo() {
        return iUserRepository.findByActivo();
    }


}
