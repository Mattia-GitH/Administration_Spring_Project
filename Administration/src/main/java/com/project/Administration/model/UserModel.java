package com.project.Administration.model;

import java.sql.Date;
import java.util.Objects;

public class UserModel {

    private long id;
    private int age;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean administrator;
    private Date registration;

    public UserModel() {
    }

    public UserModel(long id, int age, String name, String surname, String email, String password, boolean administrator, Date registration) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.administrator = administrator;
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                age == userModel.age &&
                administrator == userModel.administrator &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(surname, userModel.surname) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(registration, userModel.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, surname, email, password, administrator, registration);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", administrator=" + administrator +
                ", registration=" + registration +
                '}';
    }
}
