package com.yavlash.library.service.impl;

import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.ReaderDto;
import com.yavlash.library.entity.dto.UserListDto;
import com.yavlash.library.entity.dto.UserSaveDto;
import com.yavlash.library.entity.dto.converter.UserConverter;
import com.yavlash.library.exception.ServiceException;
import com.yavlash.library.model.dao.OrderDao;
import com.yavlash.library.model.dao.UserDao;
import com.yavlash.library.service.UserService;
import com.yavlash.library.util.PasswordEncryptor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncryptor passwordEncryptor;
    private final UserDao userDao;
    private final OrderDao orderDao;

    @Override
    public ReaderDto findReaderById(Long id) throws ServiceException {
        try {
            User user = userDao.findById(id);
            List<Order> orders = orderDao.findOrdersByUserId(id);
            return UserConverter.toDto(user, orders);
        } catch (Exception e) {
            logger.error("Exception in method findReaderById()", e);
            throw new ServiceException("Exception when find reader by id", e);
        }
    }

    @Override
    public List<UserListDto> findAll() throws ServiceException {
        try {
            List<User> users = userDao.findAll();
            return UserConverter.toListDto(users);
        } catch (Exception e) {
            logger.error("Exception in method findAllPeople()", e);
            throw new ServiceException("Exception when find all registered people", e);
        }
    }

    @Override
    public List<UserListDto> findAllReaders(String roleName) throws ServiceException {
        try {
            List<User> users = userDao.findAllReaders(roleName);
            return UserConverter.toListDto(users);
        } catch (Exception e) {
            logger.error("Exception in method findAllReaders()", e);
            throw new ServiceException("Exception when find all readers", e);
        }
    }

    @Override
    public boolean save(UserSaveDto userSaveDto) throws ServiceException {
        try {
            User user = UserConverter.fromDto(userSaveDto);
            user.setPassword(passwordEncryptor.hashPassword(user.getPassword()));
            return userDao.save(user);
        } catch (Exception e) {
            logger.error("Exception in method saveReader()", e);
            throw new ServiceException("Exception when save a reader", e);
        }
    }

    @Override
    public boolean update(UserSaveDto userSaveDto) throws ServiceException {
        try {
            User user = userDao.findById(userSaveDto.getId());
            fillUpdates(user, userSaveDto);
            return userDao.update(user);
        } catch (Exception e) {
            logger.error("Exception in method update()", e);
            throw new ServiceException("updateUser: user wasn't updated. User=" + userSaveDto, e);
        }
    }

    private void fillUpdates(User user, UserSaveDto userSaveDto) {
        if(user == null) {
            return;
        }
        if(userSaveDto.getFirstName() != null) {
            user.setFirstName(userSaveDto.getFirstName());
        }
        if(userSaveDto.getLastName() != null) {
            user.setLastName(userSaveDto.getLastName());
        }
        if(userSaveDto.getBirthDate() != null) {
            user.setBirthDate(userSaveDto.getBirthDate());
        }
        if(userSaveDto.getPassportNumber() != null) {
            user.setPassportNumber(userSaveDto.getPassportNumber());
        }
        if(userSaveDto.getAddress() != null) {
            user.setAddress(userSaveDto.getAddress());
        }
    }

    @Override
    public boolean softDelete(Long userId) throws ServiceException {
        try {
            return userDao.softDelete(userId);
        } catch (Exception e) {
            logger.error("Exception in method removeUser()", e);
            throw new ServiceException("Exception when remove a user", e);
        }
    }

    @Override
    public boolean activateUser(Long userId) throws ServiceException {
        try {
            return userDao.activateUser(userId);
        } catch (Exception e) {
            logger.error("Exception in method activateUser()", e);
            throw new ServiceException("Exception when activate a user", e);
        }
    }

    @Override
    public Optional<User> checkUser(String email, String password) throws ServiceException {
        Optional<User> user;
        try {
            user = userDao.findUserByEmail(email);
            if (user.isPresent()) {
                String dbPassword = userDao.findUserPasswordById(user.get().getId());
                user = passwordEncryptor.verifyPassword(password, dbPassword) ? user : Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Exception in method checkUser()", e);
            throw new ServiceException("Exception while check user by email and password", e);
        }
        return user;
    }

    @Override
    public boolean changeRoleToLibrarian(Long userId) throws ServiceException {
        try {
            return userDao.changeRoleToLibrarian(userId);
        } catch (Exception e) {
            logger.error("Exception in method changeToLibrarian()", e);
            throw new ServiceException("Exception when change reader to librarian", e);
        }
    }

    @Override
    public boolean changeRoleToReader(Long userId) throws ServiceException {
        try {
            return userDao.changeRoleToReader(userId);
        } catch (Exception e) {
            logger.error("Exception in method changeRoleToReader()", e);
            throw new ServiceException("Exception when change librarian to reader", e);
        }
    }
}