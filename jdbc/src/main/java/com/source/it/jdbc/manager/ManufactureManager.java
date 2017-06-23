package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.Manufacture;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class ManufactureManager {
    @Autowired
    protected DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(ManufactureManager.class);

    public Manufacture getManufactureByName(String name) {
        try (Connection con = dataSource.getConnection()) {
            Manufacture manufacture = new Manufacture();
            String sql = generateSelectByFieldSql(manufacture, "MANUFACTURENAME");
            LOGGER.debug("Created sql for Manufacture:" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                SqlGeneratorUtils.fillObjectFromResultSet(resultSet, manufacture);
                return manufacture;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception during connection to db - ", e);
            throw new GenericDaoException("Error reading manufacture from DB");
        }
        return null;
    }
}
