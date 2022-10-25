package com.thang.youtube.service.user;

import com.thang.youtube.model.entity.User;
import com.thang.youtube.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User saveAdmin(User user);

    User saveUser(User user);
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


    Optional<User> findByPhone(String phone);


}
