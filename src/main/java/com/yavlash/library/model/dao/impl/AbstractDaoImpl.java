package com.yavlash.library.model.dao.impl;

import com.yavlash.library.entity.BaseEntity;
import com.yavlash.library.exception.DaoException;
import com.yavlash.library.model.dao.BaseDao;
import com.yavlash.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoImpl<E extends BaseEntity<Long>> implements BaseDao<E> {
    protected static final String ID_COLUMN = "id";
    protected Connection connection;
    private final ConnectionPool connectionPool;

    public AbstractDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    protected abstract String defineSelectByIdQuery();

    protected abstract String defineSelectAllQuery();

    protected abstract String defineInsertQuery();

    protected abstract String defineUpdateQuery();

    protected abstract String defineDeleteQuery();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public E findById(Long id) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(defineSelectByIdQuery())
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return construct(resultSet);
                }
            }
        } catch (Exception e) {
            throw new DaoException("{} findById in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
        return null;
    }

    protected abstract E construct(ResultSet resultSet) throws SQLException;

    @Override
    public List<E> findAll() throws DaoException {
        return findEntities(defineSelectAllQuery(), null);
    }

    protected List<E> findEntities(String query, Long id) throws DaoException {
        List<E> entities = new ArrayList<>();
        query = String.format(query, id);
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                entities.add(construct(resultSet));
            }
            return entities;
        } catch (Exception e) {
            throw new DaoException("{} find entities in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean save(E entity) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(defineInsertQuery(), Statement.RETURN_GENERATED_KEYS)
        ) {
            settingPreparedParameter(preparedStatement, entity);
            int effectiveRows = preparedStatement.executeUpdate();
            if (effectiveRows == 1) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        entity.setId(resultSet.getLong(1));
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            throw new DaoException("{} add in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(),
                e);
        }
        return false;
    }

    protected abstract void settingPreparedParameter(PreparedStatement preparedStatement, E entity) throws SQLException;

    @Override
    public boolean update(E entity) throws DaoException {
        String query = String.format(defineUpdateQuery(), entity.getId());
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            settingPreparedParameter(preparedStatement, entity);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new DaoException("{} update in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean softDelete(Long id) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(defineDeleteQuery())
        ) {
            preparedStatement.setString(1, "DELETED");
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new DaoException("{} delete in class: " + getClass().getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
    }

    protected boolean addEntityToEntity(String query, Long firstId, Long secondId) throws DaoException {
        boolean result = false;
        try (Connection connection = getConnectionPool().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setLong(1, firstId);
            preparedStatement.setLong(2, secondId);
            result = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            throw new DaoException("{} add in class: " + getClass(). getSimpleName() + " has been failed: " + e.getMessage(), e);
        }
        return result;
    }
}