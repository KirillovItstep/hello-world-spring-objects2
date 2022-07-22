package org.itstep.helloworldspringobjects2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;

@Data
@AllArgsConstructor
public class User {
        private Long id;
        private String username;
        private String password;
        private String email;
    }

