package com.project.Administration.service;

import com.project.Administration.model.UserModel;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface UserService {

    UserModel createUser (UserModel userModel);

    List<UserModel> listUsers();

    UserModel userById(Long id);

    UserModel updateUser (UserModel userModel, Long id);

    String delete (long id);

    List<UserModel> whereAdminIsBool(boolean bool);

    List<UserModel> dateAfter(Date date);

    List<UserModel> ageGreaterThanEqual(int age);

    List<UserModel> orderBySurname();
}
