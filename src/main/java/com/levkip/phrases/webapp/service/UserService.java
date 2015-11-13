package com.levkip.phrases.webapp.service;

import com.levkip.phrases.domain.User;

/**
 * Created by levy on 31.10.15.
 */
public interface UserService {

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public boolean isUserExist(String email, String password);
}
