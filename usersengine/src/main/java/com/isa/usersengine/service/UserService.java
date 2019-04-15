package com.isa.usersengine.service;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserService {

    @EJB
    UsersRepositoryDao usersRepositoryDao;

    public void saveUser(User user) {
        usersRepositoryDao.addUser(user);
    }

    public User findUserById(Long id) {
        return usersRepositoryDao.getUserById(id);
    }
}
