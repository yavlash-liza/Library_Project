package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.Role;
import com.yavlash.library.entity.User;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.UserDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yavlash.library.model.dao.ColumnName.*;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    private static final String FIELDS =
        "u.last_name, u.first_name, u.birth_date, u.email, u.password, u.passport_number, u.address, u.role_id, u.user_status";

    private static final String SELECT_BY_ID_QUERY = "SELECT u.id, " + FIELDS + ", r.role_name FROM users u " +
            " LEFT JOIN roles r ON u.role_id = r.id " +
            " WHERE u.id = ? and u.user_status != 'DELETED'";
    private static final String SELECT_ALL_QUERY =
            "SELECT u.id, " + FIELDS + ", u.user_status, role_name FROM users u LEFT JOIN roles r ON u.role_id = r.id";
    private static final String INSERT_QUERY = "INSERT INTO users (last_name, first_name, birth_date, email, password, passport_number, address, role_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE users SET last_name = ?, first_name = ?, birth_date = ?, email = ?, " +
                    " password = ?, passport_number = ?,address = ?, role_id=? " +
                    " WHERE id = %d and user_status != 'DELETED'";
    private static final String DELETE_QUERY = "UPDATE users SET user_status = ? WHERE id = ?";
    private static final String CHANGE_ROLE_QUERY = "UPDATE users SET role_id = ? WHERE id = ?";

    private static final String SELECT_ALL_READERS_QUERY = "SELECT u.id, " + FIELDS + ", r.role_name FROM users u " +
            " LEFT JOIN roles r ON u.role_id = r.id " +
            " WHERE r.role_name = ? and u.user_status != 'DELETED'";

    private static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT u.id, " + FIELDS + ", r.role_name FROM users u " +
            " LEFT JOIN roles r ON u.role_id = r.id " +
            " WHERE u.email = ? and u.user_status != 'DELETED'";

    private static final String SELECT_USER_PASSWORD_BY_ID = "SELECT password FROM users " +
            " WHERE id = ? and user_status != 'DELETED'";

    private static final String UPDATE_PASSWORD_QUERY = "UPDATE users SET password = ? " +
            " WHERE id = %d and user_status != 'DELETED'";

    public UserDaoImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public List<User> findAllReaders(String roleName) throws DaoException {
        List<User> entities = new ArrayList<>();
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_READERS_QUERY);
        ) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    entities.add(construct(resultSet));
                }
                return entities;
            }
        } catch (Exception e) {
            throw new DaoException("{} findAllReaders in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_QUERY);
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = Optional.ofNullable(construct(resultSet));
                }
                return user;
            }
        } catch (Exception e) {
            throw new DaoException("{} findUserByEmail in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    public String findUserPasswordById(Long userId) throws DaoException {
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_PASSWORD_BY_ID);
        ) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(PASSWORD_COLUMN);
                }
            }
        } catch (Exception e) {
            throw new DaoException("{} findUserPasswordById in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean activateUser(Long id) throws DaoException {
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)
        ) {
            preparedStatement.setString(1, "ACTIVE");
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new DaoException("{} activate user in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean changeRoleToLibrarian(Long id) throws DaoException {
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROLE_QUERY)
        ) {
            preparedStatement.setString(1, "2");
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new DaoException("{} change user role to librarian in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean changeRoleToReader(Long id) throws DaoException {
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROLE_QUERY)
        ) {
            preparedStatement.setString(1, "1");
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new DaoException("{} change user role to librarian in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    protected String defineSelectByIdQuery() {
        return SELECT_BY_ID_QUERY;
    }

    @Override
    protected String defineSelectAllQuery() {
        return SELECT_ALL_QUERY;
    }

    @Override
    protected String defineInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    protected String defineUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String defineDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected User construct(ResultSet resultSet) throws SQLException {
        Role role = Role.builder()
                .id(resultSet.getLong(ROLE_ID_COLUMN))
                .roleName(resultSet.getString(ROLE_NAME_COLUMN)).build();
        return User.builder()
                .id(resultSet.getLong(ID_COLUMN))
                .lastName(resultSet.getString(LAST_NAME_COLUMN))
                .firstName(resultSet.getString(FIRST_NAME_COLUMN))
                .birthDate(
                        resultSet.getDate(BIRTH_DATE_COLUMN) == null
                                ? null
                                : resultSet.getDate(BIRTH_DATE_COLUMN).toLocalDate()
                )
                .email(resultSet.getString(EMAIL_COLUMN))
                .password(resultSet.getString(PASSWORD_COLUMN))
                .passportNumber(resultSet.getString(PASSPORT_NUMBER_COLUMN))
                .address(resultSet.getString(ADDRESS_COLUMN))
                .role(role)
                .userStatus(resultSet.getString(USER_STATUS).toUpperCase())
                .build();
    }

    @Override
    protected void settingPreparedParameter(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLastName());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setDate(3, user.getBirthDate() == null ? null : Date.valueOf(user.getBirthDate()));
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getPassportNumber());
        preparedStatement.setString(7, user.getAddress());
        preparedStatement.setLong(8, user.getRole().getId());
    }
}