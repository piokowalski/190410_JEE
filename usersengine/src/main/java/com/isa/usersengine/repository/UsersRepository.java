package com.isa.usersengine.repository;

import com.isa.usersengine.domain.Gender;
import com.isa.usersengine.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private static List<User> usersRepository = new ArrayList<>();

    public static List<User> getRepository() {
        if (usersRepository.size() == 0) {
            fillRepositoryWithDefaults();
        }
        return usersRepository;
    }

    private static void fillRepositoryWithDefaults() {

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Jan");
        user1.setLogin("janko");
        user1.setAge(21);
        user1.setGender(Gender.MAN);
        usersRepository.add(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Adam");
        user2.setLogin("ano");
        user2.setAge(20);
        user2.setGender(Gender.MAN);
        usersRepository.add(user2);

        User user3 = new User();
        user3.setId(3L);
        user3.setName("Anna");
        user3.setLogin("anmi");
        user3.setAge(20);
        user3.setGender(Gender.WOMAN);
        usersRepository.add(user3);
    }

    public static boolean contains(User user) {
        return getRepository().stream().anyMatch(u -> u.getId() == user.getId());
    }
}
