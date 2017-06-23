package ua.com.owu.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.entity.User;

/**
 * Created by okten22 on 23.06.17.
 */
public interface UserService extends UserDetailsService{

    void save(User user);

    User findUserByUsername(String username);


}
