package com.isa.usersengine.dao;

import com.isa.usersengine.domain.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersRepositoryDao {

    void addUser(User user);

    User getUserById(Long id);

    User getUserByLogin(String login);

    List<User> getUsersList();

    void deleteUserById(Long id);
}
