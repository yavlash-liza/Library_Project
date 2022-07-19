package com.yavlash.library.model.dao;

import com.yavlash.library.entity.User;
import com.yavlash.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    List<User> findAllReaders(String roleName) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    String findUserPasswordById(Long userId) throws DaoException;
    boolean activateUser(Long id) throws DaoException;
    boolean changeRoleToLibrarian(Long id) throws DaoException;
    boolean changeRoleToReader(Long id) throws DaoException;
}