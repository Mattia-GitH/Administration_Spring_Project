package com.project.Administration.service;

import com.project.Administration.converter.UserConverter;
import com.project.Administration.entity.UserEntity;
import com.project.Administration.model.UserModel;
import com.project.Administration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class UserServiceTest {

    @InjectMocks
    UserServiceImp service;

    @Mock
    UserRepository repository;

    @Spy
    UserConverter converter;

    @Test
    public void testing_createUser() {
        UserModel newUser = new UserModel();
        newUser.setName("Name User");
        newUser.setSurname("Surname User");

        UserEntity toSave = new UserEntity();
        toSave.setName("Name User");
        toSave.setSurname("Surname User");

        when(repository.save(any(UserEntity.class))).thenReturn(toSave);

        UserModel created = service.createUser(newUser);

        assertNotNull(created);
        verify(repository, times(1)).save(toSave);
        verify(converter, times(1)).toModel(toSave);
        verify(converter, times(1)).toEntity(created);
        assertEquals(created, converter.toModel(toSave));
    }

    @Test
    public void testing_listUsers() {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity());
        list.add(new UserEntity());
        list.add(new UserEntity());

        when(repository.findAll()).thenReturn(list);

        List<UserModel> findAll = service.listUsers();

        assertNotNull(findAll);
        assertEquals(3, findAll.size());
        verify(repository, times(1)).findAll();
        verify(converter, times(1)).listToModels(list);
        assertEquals(list, converter.listToEntities(findAll));
    }

    @Test
    public void testing_updateUser() {
        UserEntity entitySave = new UserEntity();
        entitySave.setName("Name");

        UserEntity entityFind = new UserEntity();
        entityFind.setName("Name");

        UserModel modelCreate = new UserModel();
        modelCreate.setName("Name");

        UserModel modelUpdate = new UserModel();
        modelUpdate.setName("Name");

        when(repository.save(any(UserEntity.class))).thenReturn(entitySave);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entityFind));

        service.createUser(modelCreate);
        UserModel updated = service.updateUser(modelUpdate, anyLong());

        assertNotNull(updated);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(2)).save(entitySave);
        verify(converter, times(2)).toModel(entitySave);
        verify(converter, times(2)).toModel(entityFind);
        verify(converter, times(1)).toEntity(modelCreate);
        verify(converter, times(1)).toEntity(modelUpdate);
        assertEquals(entityFind, converter.toEntity(updated));
    }

    @Test
    public void testing_delete() {
        UserEntity entitySave = new UserEntity();
        entitySave.setName("Name");

        UserEntity entityFind = new UserEntity();
        entityFind.setName("Name");

        when(repository.save(any(UserEntity.class))).thenReturn(entitySave);
        when(repository.findById(anyLong())).thenReturn(Optional.of(entityFind));
        repository.save(entitySave);

        String delete = service.delete(anyLong());
        String response = "User deleted " + entityFind.toString();

        assertEquals(response, delete);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(entityFind);
    }

    @Test
    public void testing_whereAdminIsBool() {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity());

        when(repository.findByAdministrator(anyBoolean())).thenReturn(list);

        List<UserModel> listFind = service.whereAdminIsBool(anyBoolean());

        assertNotNull(listFind);
        assertEquals(list.size(), listFind.size());
        verify(repository, times(1)).findByAdministrator(anyBoolean());
        verify(converter, times(1)).listToModels(list);
        assertEquals(list, converter.listToEntities(listFind));
    }

    @Test
    public void testing_dateAfter() {
        List<UserEntity> list = new ArrayList<>();

        when(repository.findByRegistrationAfter(any(Date.class))).thenReturn(list);

        List<UserModel> listAfterDate = service.dateAfter(Date.valueOf("2020-01-01"));

        assertNotNull(listAfterDate);
        assertEquals(list.size(), listAfterDate.size());
        verify(repository, times(1)).findByRegistrationAfter(any(Date.class));
        verify(converter, times(1)).listToModels(list);
        assertEquals(list, converter.listToEntities(listAfterDate));
    }

    @Test
    public void testing_ageGreaterThanEqual() {
        List<UserEntity> list = new ArrayList<>();

        when(repository.findByAgeGreaterThanEqual(anyInt())).thenReturn(list);

        List<UserModel> listAfterDate = service.ageGreaterThanEqual(anyInt());

        assertNotNull(listAfterDate);
        assertEquals(list.size(), listAfterDate.size());
        verify(repository, times(1)).findByAgeGreaterThanEqual(anyInt());
        verify(converter, times(1)).listToModels(list);
        assertEquals(list, converter.listToEntities(listAfterDate));
    }

    @Test
    public void testing_orderBySurname() {
        List<UserEntity> list = new ArrayList<>();

        when(repository.findAllByOrderBySurname()).thenReturn(list);

        List<UserModel> listAfterDate = service.orderBySurname();

        assertNotNull(listAfterDate);
        assertEquals(list.size(), listAfterDate.size());
        verify(repository, times(1)).findAllByOrderBySurname();
        verify(converter, times(1)).listToModels(list);
        assertEquals(list, converter.listToEntities(listAfterDate));
    }
}
