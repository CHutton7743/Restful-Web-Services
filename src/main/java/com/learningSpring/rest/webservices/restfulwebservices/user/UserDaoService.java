package com.learningSpring.rest.webservices.restfulwebservices.user;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;
    static {
        users.add(new User(1, "Codey", new Date()));
        users.add(new User(2, "Natalie", new Date()));
        users.add(new User(3, "Victoria", new Date()));
        users.add(new User(4, "Valencia", new Date()));
    }
    public List<User> findAll() {
        return users;
    }
    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(usersCount++);
        }
        users.add(user);
        return user;
    }
    public User findUser(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
