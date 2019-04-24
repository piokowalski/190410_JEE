package com.isa.usersengine.service;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.interceptors.AddUserInterceptor;
import com.isa.usersengine.interceptors.AddUserSetGenderInterceptor;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;
import java.util.List;

@RequestScoped
public class UserService {

    @EJB
    UsersRepositoryDao usersRepositoryDao;

    @Interceptors({AddUserInterceptor.class, AddUserSetGenderInterceptor.class})
    public void saveUser(User user) {
        usersRepositoryDao.addUser(user);
    }

    public User findUserById(Long id) {
        return usersRepositoryDao.getUserById(id);
    }

    public List<User> findAllUsers() {
        return usersRepositoryDao.getUsersList();
    }

    public void deleteUserById(Long id) {
        usersRepositoryDao.deleteUserById(id);
    }
}
