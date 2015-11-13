package com.levkip.phrases.webapp.persistance;

import com.levkip.phrases.domain.User;
import org.slf4j.LoggerFactory;

/**
 * Created by levy on 30.10.15.
 */
public interface UserDao {


    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public boolean isUserExist(String email, String password);
}
