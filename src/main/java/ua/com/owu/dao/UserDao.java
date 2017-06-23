package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.entity.User;

/**
 * Created by okten22 on 23.06.17.
 */
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);

}
