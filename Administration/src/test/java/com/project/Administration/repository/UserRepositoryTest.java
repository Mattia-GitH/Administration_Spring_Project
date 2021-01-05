package com.project.Administration.repository;

import com.project.Administration.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
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

    @Test
    public void testing_findByAdministrator() {
        UserEntity adminTrue = new UserEntity();
        adminTrue.setAdministrator(true);

        UserEntity adminFalse = new UserEntity();
        adminFalse.setAdministrator(false);

        userRepository.save(adminTrue);
        userRepository.save(adminFalse);

        List<UserEntity> listTrue = userRepository.findByAdministrator(true);
        List<UserEntity> listFalse = userRepository.findByAdministrator(false);

        assertNotNull(listFalse);
        assertNotNull(listTrue);
        assertFalse(listFalse.isEmpty());
        assertFalse(listTrue.isEmpty());
        assertEquals(1, listFalse.size());
        assertEquals(1, listTrue.size());
        assertEquals(adminTrue.toString(), listTrue.get(0).toString());
        assertEquals(adminFalse.toString(), listFalse.get(0).toString());
    }

    @Test
    public void testing_findByRegistrationAfter() {
        UserEntity oldUser = new UserEntity();
        oldUser.setRegistration(Date.valueOf("2020-01-01"));

        UserEntity newUser = new UserEntity();
        newUser.setRegistration(Date.valueOf("2025-01-01"));

        userRepository.save(oldUser);
        userRepository.save(newUser);

        List<UserEntity> listAfterDate = userRepository.findByRegistrationAfter(Date.valueOf("2022-01-01"));

        assertNotNull(listAfterDate);
        assertFalse(listAfterDate.isEmpty());
        assertEquals(1, listAfterDate.size());
        assertEquals(newUser.toString(), listAfterDate.get(0).toString());
    }

    @Test
    public void testing_findByAgeGreaterThanEqual() {
        UserEntity oldAge = new UserEntity();
        oldAge.setAge(90);

        UserEntity middleAge = new UserEntity();
        middleAge.setAge(45);

        UserEntity youngAge = new UserEntity();
        youngAge.setAge(16);

        userRepository.save(oldAge);
        userRepository.save(middleAge);
        userRepository.save(youngAge);

        List<UserEntity> middleAndOld = userRepository.findByAgeGreaterThanEqual(45);

        assertNotNull(middleAndOld);
        assertFalse(middleAndOld.isEmpty());
        assertEquals(2, middleAndOld.size());
        assertEquals(oldAge.toString(), middleAndOld.get(0).toString());
        assertEquals(middleAge.toString(), middleAndOld.get(1).toString());
    }

    @Test
    public void testing_findAllByOrderBySurname() {
        UserEntity userA = new UserEntity();
        userA.setSurname("A");

        UserEntity userB = new UserEntity();
        userB.setSurname("B");

        userRepository.save(userB);
        userRepository.save(userA);

        List<UserEntity> shouldOrdered = userRepository.findAllByOrderBySurname();

        assertNotNull(shouldOrdered);
        assertFalse(shouldOrdered.isEmpty());
        assertEquals(2, shouldOrdered.size());
        assertEquals(userA.toString(), shouldOrdered.get(0).toString());
        assertEquals(userB.toString(), shouldOrdered.get(1).toString());
    }
}
