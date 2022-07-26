package org.itstep.helloworldspringobjects2;

import org.itstep.helloworldspringobjects2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping(value="/user")
    public User getUserById2(@RequestParam("id") long id){
        return userService.getUser(id);
    }
}
