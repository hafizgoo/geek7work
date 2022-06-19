package com.hafizgoo.masterslave.service;

import com.hafizgoo.masterslave.dao.User;



public interface UserOp {
    User getUserById(int id);
    void addUser(User user);
}