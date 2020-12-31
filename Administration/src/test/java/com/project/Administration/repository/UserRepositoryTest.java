package com.project.Administration.repository;

import com.project.Administration.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testing_save_and_findById() {
        UserEntity toSave = new UserEntity();
        toSave.setName("NAME");
        toSave.setAdministrator(true);

        UserEntity created = userRepository.save(toSave);
        UserEntity toFind = userRepository.findById(toSave.getId()).orElse(null);

        assertNotNull(toFind);
        assertEquals(created, toFind);
    }

    @Test
    public void testing_findAll_and_saveAll() {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity());
        list.add(new UserEntity());
        list.add(new UserEntity());

        List<UserEntity> saved = userRepository.saveAll(list);
        List<UserEntity> find = userRepository.findAll();

        assertNotNull(find);
        assertEquals(saved.size(), 3);
        assertEquals(find.size(), saved.size());
        assertEquals(find, saved);
    }

    @Test
    public void testing_update_user() {
        UserEntity oldUser = new UserEntity();
        oldUser.setAdministrator(false);

        userRepository.save(oldUser);
        oldUser.setAdministrator(true);
        UserEntity update = userRepository.save(oldUser);

        assertTrue(oldUser.isAdministrator());
        assertEquals(oldUser.getId(), update.getId());
    }

    @Test
    public void testing_delete_user_using_findAll() {
        UserEntity toDelete = new UserEntity();
        toDelete.setName("Name");
        toDelete.setSurname("Surname");

        userRepository.delete(toDelete);
        List<UserEntity> emptyList = userRepository.findAll();

        assertTrue(emptyList.isEmpty());
    }

}
