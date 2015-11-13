package com.levkip.phrases.webapp.service;

import com.levkip.phrases.domain.User;
import com.levkip.phrases.webapp.persistance.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean isUserExist(String email, String password) {
        return userDao.isUserExist(email, password);
    }
}
