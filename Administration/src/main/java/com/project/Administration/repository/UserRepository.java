package com.project.Administration.repository;

import com.project.Administration.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByAdministrator(boolean bool);

    List<UserEntity> findByRegistrationAfter(Date date);

    List<UserEntity> findByAgeGreaterThanEqual(int age);

    List<UserEntity> findAllByOrderBySurname();
}
