package com.project.Administration.controller;

import com.project.Administration.model.UserModel;
import com.project.Administration.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void testing_DELETE_user() throws Exception {
        when(userService.delete(anyLong())).thenReturn("User deleted " + anyLong());

        mvc.perform(
                delete("/api/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted 0"))
                .andReturn().getResponse();
    }

    @Test
    public void testing_GET_listAdminIsBool_true() throws Exception {
        UserModel user = new UserModel();

        List<UserModel> list = new ArrayList<>();
        list.add(user);

        when(userService.whereAdminIsBool(anyBoolean())).thenReturn(list);

        mvc.perform(
                get("/api/users/admin=true"))
                .andExpect((status().isOk()))
                .andExpect(content().json(listJSON.write(list).getJson()))
                .andReturn().getResponse();
    }

    @Test
    public void testing_GET_listAdminIsBool_false() throws Exception {
        UserModel user = new UserModel();

        List<UserModel> list = new ArrayList<>();
        list.add(user);

        when(userService.whereAdminIsBool(anyBoolean())).thenReturn(list);

        mvc.perform(
                get("/api/users/admin=false"))
                .andExpect((status().isOk()))
                .andExpect(content().json(listJSON.write(list).getJson()))
                .andReturn().getResponse();
    }

    @Test
    public void testing_usersDateAfter() throws Exception {
        UserModel user = new UserModel();

        List<UserModel> list = new ArrayList<>();
        list.add(user);

        when(userService.dateAfter(any(Date.class))).thenReturn(list);

        mvc.perform(
                get("/api/users/registration-after/2020-01-01"))
                .andExpect((status().isOk()))
                .andExpect(content().json(listJSON.write(list).getJson()))
                .andReturn().getResponse();
    }

    @Test
    public void testing_ageGreaterThanEqual() throws Exception {
        UserModel user = new UserModel();

        List<UserModel> list = new ArrayList<>();
        list.add(user);

        when(userService.ageGreaterThanEqual(anyInt())).thenReturn(list);

        mvc.perform(
                get("/api/users/age=45"))
                .andExpect((status().isOk()))
                .andExpect(content().json(listJSON.write(list).getJson()))
                .andReturn().getResponse();
    }

    @Test
    public void testing_orderBySurname() throws Exception {
        UserModel user = new UserModel();

        List<UserModel> list = new ArrayList<>();
        list.add(user);

        when(userService.orderBySurname()).thenReturn(list);

        mvc.perform(
                get("/api/users/order-by-surname"))
                .andExpect((status().isOk()))
                .andExpect(content().json(listJSON.write(list).getJson()))
                .andReturn().getResponse();
    }
}
