package ru.kata.pp313.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pp313.dao.AppDao;
import ru.kata.pp313.models.User;

import java.util.List;

@Service
public class AppServiceImp implements AppService {
    private final AppDao appDao;

    public AppServiceImp(AppDao appDao) {
        this.appDao = appDao;
    }

    @Override
    public List<User> getUserList() {
        return appDao.getUserList();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        appDao.saveUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return appDao.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        appDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        appDao.deleteUser(id);
    }
}
