package com.project.Administration.converter;

import com.project.Administration.entity.UserEntity;
import com.project.Administration.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserConverterTest {

    public final UserConverter converter;

    @Autowired
    public UserConverterTest(UserConverter converter) {
        this.converter = converter;
    }

    @Test
    public void testing_converter_bidirectional_toModel_toEntity() {
        UserEntity entity = new UserEntity();
        entity.setName("Name");

        UserModel toModel = converter.toModel(entity);
        UserEntity toEntity = converter.toEntity(toModel);

        assertEquals(entity, toEntity);
    }

    @Test
    public void testing_converter_list_bidirectional_listToModels_listToEntities() {
        List<UserEntity> entityList = new ArrayList<>();
        entityList.add(new UserEntity());
        entityList.add(new UserEntity());
        entityList.add(new UserEntity());

        List<UserModel> listToModels = converter.listToModels(entityList);
        List<UserEntity> listToEntities = converter.listToEntities(listToModels);

        assertEquals(entityList, listToEntities);
        assertEquals(3, listToEntities.size());
    }
}
