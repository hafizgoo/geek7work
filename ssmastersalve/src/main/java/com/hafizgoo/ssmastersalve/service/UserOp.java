package com.hafizgoo.ssmastersalve.service;


import com.hafizgoo.ssmastersalve.dao.User;

public interface UserOp {
    User getUserById(int id);
    void addUser(User user);
}
