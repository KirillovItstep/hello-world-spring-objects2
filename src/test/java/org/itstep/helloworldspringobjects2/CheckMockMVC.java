package org.itstep.helloworldspringobjects2;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringApplicationObjects.class)
@ExtendWith(SpringExtension.class)
class CheckMockMVC {

    private static MockMvc mvc;

    @BeforeAll
    public static void setUp() {
        UserController userController = new UserController();
        userController.setUserService(new UserService());
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("Get index page")
    public void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("index")));
    }

    @Test
    @DisplayName("Get all users")
    public void users() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].username", Matchers.equalTo("admin")));
    }

    @ParameterizedTest
    @DisplayName("Get user by id")
    @ValueSource(ints = {-1, 0, 1, 2, 3, Integer.MAX_VALUE})
    @Tag("parameterized")
    //multiple times with different parameters
    public void userById(int id) throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/user?id=" + String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON));
        actions
                .andExpect(status().isOk());
        switch (id) {
            case 1:
                actions
                        .andExpect(jsonPath("$.username", Matchers.equalTo("admin")))
                        .andExpect(jsonPath("$.email", Matchers.equalTo("admin@gmail.com")));
                break;
            case 2:
                actions
                        .andExpect(jsonPath("$.username", Matchers.equalTo("user")))
                        .andExpect(jsonPath("$.email", Matchers.equalTo("user@gmail.com")));
                break;
            case 3:
                actions
                        .andExpect(jsonPath("$.username", Matchers.equalTo("guest")))
                        .andExpect(jsonPath("$.email", Matchers.equalTo("guest@gmail.com")));
                break;
            default:
                actions
                        .andExpect(content().string(""));
        }

    }
}
