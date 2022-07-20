package com.yavlash.library.service;

import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.LibrarianDto;
import com.yavlash.library.entity.dto.ReaderDto;
import com.yavlash.library.entity.dto.UserListDto;
import com.yavlash.library.entity.dto.UserSaveDto;
import com.yavlash.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service{
    ReaderDto findReaderById(Long id) throws ServiceException;
    List<UserListDto> findAllReaders(String roleName) throws ServiceException;
    List<UserListDto> findAll() throws ServiceException;
    boolean save(UserSaveDto userSaveDto) throws ServiceException;
    boolean softDelete(Long userId) throws ServiceException;
    boolean activateUser(Long userId) throws ServiceException;
    boolean changeRoleToReader(Long userId) throws ServiceException;
    boolean changeRoleToLibrarian(Long userId) throws ServiceException;
    Optional<User> checkUser(String email, String password) throws ServiceException;
    boolean update(UserSaveDto userSaveDto) throws ServiceException;
}