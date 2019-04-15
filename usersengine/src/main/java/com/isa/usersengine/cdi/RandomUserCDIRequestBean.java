package com.isa.usersengine.cdi;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.domain.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.Random;

@RequestScoped
public class RandomUserCDIRequestBean {

    int index = Math.abs(new Random().nextInt()) % 3;

    @EJB
    UsersRepositoryDao usersRepositoryDao;

    public User getRandomUser() {
        return usersRepositoryDao.getUsersList().get(index);
    }
}
