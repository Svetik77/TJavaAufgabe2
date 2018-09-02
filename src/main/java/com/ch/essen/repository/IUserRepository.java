package com.ch.essen.repository;

import java.util.List;

import com.ch.essen.model.User;

public interface IUserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}