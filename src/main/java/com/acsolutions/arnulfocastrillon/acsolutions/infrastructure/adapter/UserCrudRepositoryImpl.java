package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.adapter;

import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.port.IUserRepository;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

    private final IUserCrudRepository iUserCrudRepository;

    private final UserMapper userMapper;

    public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Iterable<User> findByActivoTrue() {

        return userMapper.toUsers(iUserCrudRepository.findAll());

    }

    @Override
    public User findById(Integer id) {
        return userMapper.toUser(iUserCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La el usuario con Id: "+id+" no existe")
        ));
    }

    @Override
    public User findByEmail(String email) {

        return userMapper.toUser(iUserCrudRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException ("User whit email:" + email + "not found")
        ));
    }


    @Override
    public User save(User user) {
        return userMapper.toUser(iUserCrudRepository.save(userMapper.toUserEntity(user)));
    }


    @Override
    public void updateActivo(Integer id) {
        this.iUserCrudRepository.updateActivo(id);

    }

    @Override
    public Iterable<User> findByActivo(boolean activo) {
        return null;
    }

    @Override
    public Iterable<User> findByActivo() {
        return userMapper.toUsers(iUserCrudRepository.findByActivo());
    }

    @Override
    public boolean existsById(Integer id) {
        return iUserCrudRepository.existsById(id);
    }

    @Override
    public void reactivateUser(Integer id) {
        this.iUserCrudRepository.reactivateUser(id);
    }

}
