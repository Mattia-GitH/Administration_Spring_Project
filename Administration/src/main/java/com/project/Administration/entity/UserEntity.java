package com.project.Administration.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ADMINISTRATION_TBL")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "AGE")
    private int age;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "MAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TELEPHONE")
    private int telephone;

    @Column(name = "ADMINISTRATOR")
    private boolean administrator;

    @Column(name = "REGISTRATION")
    private Date registration;

    public UserEntity() {
    }

    public UserEntity(long id, int age, String name, String surname, String email, String password, int telephone, boolean administrator, Date registration) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
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
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                age == that.age &&
                telephone == that.telephone &&
                administrator == that.administrator &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(registration, that.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, surname, email, password, telephone, administrator, registration);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", administrator=" + administrator +
                ", registration=" + registration +
                '}';
    }
}
