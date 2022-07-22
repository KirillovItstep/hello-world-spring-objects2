package org.itstep.helloworldspringobjects2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    List<User> users = new ArrayList<>(){{
        add(new User(1L,"admin", "admin","admin@gmail.com"));
        add(new User(2L,"user", "user","user@gmail.com"));
        add(new User(3L,"guest", "guest","guest@gmail.com"));
    }};

public List<User> getUsers(){
    return users;
}

public User getUser(Long id){
    User user = users
            .stream()
            .filter(u->u.getId()==id)
            .findFirst().orElse(null);
    return user;
}
}
