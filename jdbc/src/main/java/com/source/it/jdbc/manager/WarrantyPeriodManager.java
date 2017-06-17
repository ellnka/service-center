package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.ItemType;
import com.source.it.jdbc.model.WarrantyPeriod;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class WarrantyPeriodManager {
    @Autowired
    protected DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(WarrantyPeriodManager.class);

    public WarrantyPeriod getWarrantyPeriodByDays(int days) {
        try (Connection con = dataSource.getConnection()) {
            WarrantyPeriod warrantyPeriod = new WarrantyPeriod();
            String sql = generateSelectByFieldSql(warrantyPeriod, "DAYS");
            LOGGER.debug("Created sql for WarrantyPeriod:" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, days);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                SqlGeneratorUtils.fillObjectFromResultSet(resultSet, warrantyPeriod);
                return warrantyPeriod;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception during connection to db - ", e);
            throw new GenericDaoException("Error reading warranty period from DB");
        }
        return null;
    }
}
