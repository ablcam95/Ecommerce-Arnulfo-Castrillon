package com.acsolutions.arnulfocastrillon.acsolutions.domain.port;


import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;

public interface IUserRepository {
    //listar todos los usuarios
    Iterable<User> findByActivoTrue();
    // buscar usuario por ID
    User findById(Integer id);

    User findByEmail(String email);
    // guardar un usuario
    User save(User user);


    void updateActivo(Integer id);

    Iterable<User> findByActivo(boolean activo);

    Iterable<User> findByActivo();

    boolean existsById(Integer id);

    void reactivateUser(Integer userId);
}
