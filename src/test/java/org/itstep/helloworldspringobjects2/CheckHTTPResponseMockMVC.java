package org.itstep.helloworldspring;

import org.itstep.helloworldspringobjects2.SpringApplicationObjects;
import org.itstep.helloworldspringobjects2.UserController;
import org.itstep.helloworldspringobjects2.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= SpringApplicationObjects.class)
@WebMvcTest(UserController.class)
class CheckHTTPResponseMockMVC {

    @Autowired
    private static MockMvc mvc;

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    public static void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void getUsers() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.users").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.users[*].id").isNotEmpty());
    }

    @Test
    public void getUserById() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    }
