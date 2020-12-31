package com.project.Administration.controller;

import com.project.Administration.model.UserModel;
import com.project.Administration.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private final MockMvc mvc;
    private final JacksonTester<UserModel> userJSON;
    private final JacksonTester<List<UserModel>> listJSON;

    @Autowired
    public UserControllerTest(MockMvc mvc, JacksonTester<UserModel> userJSON, JacksonTester<List<UserModel>> listJSON) {
        this.mvc = mvc;
        this.userJSON = userJSON;
        this.listJSON = listJSON;
    }

    @MockBean
    private UserService userService;

    @Test
    public void testing_GET_users_list() throws Exception {
        List<UserModel> userList = new ArrayList<>();

        when(userService.listUsers()).thenReturn(userList);

        mvc.perform(
                get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(listJSON.write(userList).getJson()))
                .andReturn().getResponse();
    }

    @Test
    public void testing_POST_create_user() throws Exception {
        UserModel user = new UserModel();
        user.setName("Name");

        when(userService.createUser(any(UserModel.class))).thenReturn(user);

        mvc.perform(
                post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON.write(user).getJson()))
                .andExpect(status().isCreated())
                .andExpect(content().json(userJSON.write(user).getJson()))
                .andReturn().getResponse();
    }

    @Test
    public void testing_PUT_update_user() throws Exception {
        UserModel user = new UserModel();
        user.setName("Name");

        when(userService.updateUser(any(UserModel.class), anyLong())).thenReturn(user);

        mvc.perform(
                put("/api/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON.write(user).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().json(userJSON.write(user).getJson()))
                .andReturn().getResponse();
    }
}
