package com.project.Administration.service;

import com.project.Administration.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserModel createUser (UserModel userModel);

    List<UserModel> listUsers();

    UserModel updateUser (UserModel userModel, Long id);

    String delete (long id);
}
