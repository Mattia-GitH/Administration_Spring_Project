package com.project.Administration.service;

import com.project.Administration.converter.UserConverter;
import com.project.Administration.entity.UserEntity;
import com.project.Administration.exception.UserNotFoundException;
import com.project.Administration.model.UserModel;
import com.project.Administration.repository.UserRepository;
import com.project.Administration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository repository;
    private final UserConverter converter;

    @Autowired
    public UserServiceImp(UserRepository repository, UserConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        UserEntity toSave = repository.save(converter.toEntity(userModel));
        return converter.toModel(toSave);
    }

    @Override
    public List<UserModel> listUsers() {
        List<UserEntity> userEntities = repository.findAll();
        return converter.listToModels(userEntities);
    }

    @Override
    public UserModel updateUser(UserModel userModel, Long id) {
        Optional<UserEntity> optionalUserEntity = repository.findById(id);
        if (optionalUserEntity.isPresent()){
            UserEntity update = optionalUserEntity.get();
            update.setName(userModel.getName());
            update.setSurname(userModel.getSurname());
            update.setAge(userModel.getAge());
            update.setEmail(userModel.getEmail());
            update.setPassword(userModel.getPassword());
            update.setRegistration(userModel.getRegistration());
            update.setAdministrator(userModel.isAdministrator());
            repository.save(update);
            return converter.toModel(update);
        } else {
            throw new UserNotFoundException("User not found id: " + userModel.getId());
        }
    }

    @Override
    public String delete(long id) {
        Optional<UserEntity> userEntityOptional = repository.findById(id);
        if (userEntityOptional.isPresent()){
            repository.delete(userEntityOptional.get());
            return "User deleted " + userEntityOptional.get();
        } else {
            throw new UserNotFoundException("User not found id: " + id);
        }
    }
}
