package com.project.Administration.converter;

import com.project.Administration.entity.UserEntity;
import com.project.Administration.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserModel toModel(UserEntity entity) {
        UserModel model = new UserModel();
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setAge(entity.getAge());
        model.setAdministrator(entity.isAdministrator());
        model.setEmail(entity.getEmail());
        model.setPassword(entity.getPassword());
        model.setRegistration(entity.getRegistration());
        return model;
    }

    public List<UserModel> listToModels(List<UserEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    public UserEntity toEntity(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setAge(model.getAge());
        entity.setAdministrator(model.isAdministrator());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setRegistration(model.getRegistration());
        return entity;
    }

    public List<UserEntity> listToEntities(List<UserModel> modelList) {
        return modelList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
