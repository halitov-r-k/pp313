package ru.kata.pp313.dao;

import ru.kata.pp313.models.User;

import java.util.List;

public interface AppDao {
    List<User> getUserList();

    void saveUser(User user);

    User getUser(Integer id);

    void updateUser(User user);

    void deleteUser(Integer id);
}
