package com.source.it.jdbc.dao;


import com.source.it.jdbc.model.BaseEntity;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.*;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.*;

public class GenericDaoImpl <T extends BaseEntity<PK>, PK extends Serializable> implements GenericDao <T, PK> {
    private static final Logger LOGGER = Logger.getLogger(GenericDaoImpl.class);
    private Class<T> type;
    protected DataSource dataSource;

    protected GenericDaoImpl(DataSource dataSource, Class<T> type) {
        this.dataSource = dataSource;
        this.type = type;
        LOGGER.debug("GenericDaoImpl created");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public PK create(T objectToCreate) {
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            String sql = generateCreateSql(objectToCreate);
            LOGGER.debug("Created sql for " + objectToCreate + ":" + sql);
            PreparedStatement stmt =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objectToCreate.prepareCreateStatement(stmt);
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                PK id = (PK) new Long(resultSet.getLong(1)); //Don't do such way.
                objectToCreate.setId(id);                    //PK could be String e.g.
                con.commit();
                return id;
            }
        } catch (SQLException e) {
            LOGGER.error("Error saving object to DB ", e);
            throw new GenericDaoException("Error saving "
                    + objectToCreate.getClass().getSimpleName()
                    + " to data base", e);
        }
        throw new GenericDaoException("Error saving "
                + objectToCreate.getClass().getSimpleName()
                + " to data base");
    }

    @Override
    public T read(PK id) {
        T result = null;
        try (Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            result = type.newInstance();
            String sql = generateSelectSql(result);
            LOGGER.debug("Created sql for " + result.getClass().getSimpleName() + ":" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            result.prepareReadOrDeleteStatement(stmt, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                fillObjectFromResultSet(resultSet, result);
                con.commit();
                return result;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            LOGGER.error("Error reading " + type + " from data base", e);
            throw new GenericDaoException("Error reading "
                    + (result == null
                        ? "unknown"
                        : result.getClass().getSimpleName().toLowerCase())
                    + " from data base", e);
        }
        String type = result == null
                ? "unknown"
                : result.getClass().getSimpleName().toLowerCase();
        LOGGER.debug("Error reading "
                                + type + " from data base - no " + type +"s were found with id = " + id);
        throw new GenericDaoException("Error reading "
                + type +  " from data base - no " + type +"s were found with id = " + id);
    }

    @Override
    public void update(T objectToUpdate) {
        try(Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            String sql = generateUpdateSql(objectToUpdate);
            LOGGER.debug("Created sql for " + objectToUpdate + ":" + sql);
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int countOfUpdatedRows = stmt.executeUpdate();
            if (countOfUpdatedRows == 1) {
                con.commit();
                return;
            } else {
                con.rollback();
                throw new GenericDaoException("Error updating user 0 or more than 1 user was found. ROLLBACK!");
            }

        } catch (SQLException e) {
            throw new GenericDaoException("Error updating user", e);
        }

    }

    @Override
    public void delete(T objectToDelete) {
        try(Connection con = dataSource.getConnection()) {
            prepareConnection(con);
            String sql = generateDeleteSql(objectToDelete);
            LOGGER.debug("Created sql for " + objectToDelete + ":" + sql);
            PreparedStatement stmt = con.prepareStatement(generateDeleteSql(objectToDelete));
            objectToDelete.prepareReadOrDeleteStatement(stmt, objectToDelete.getId());
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            throw new GenericDaoException("Error deleting " + objectToDelete.getClass().getSimpleName(), e);
        }
    }

    private void prepareConnection(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
}
