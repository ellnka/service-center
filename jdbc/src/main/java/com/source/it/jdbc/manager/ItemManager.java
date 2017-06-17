package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.Item;
import com.source.it.jdbc.model.ItemType;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class ItemManager {
    private static final Logger LOGGER = Logger.getLogger(ItemManager.class);
    @Autowired
    protected DataSource dataSource;


    public Item getBySerialNumber(String serialNumber) {
        try (Connection con = dataSource.getConnection()) {
            Item item = new Item();
            String sql = generateSelectByFieldSql(item, "SERIALNUMBER");
            LOGGER.debug("Created sql for item:" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, serialNumber);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                SqlGeneratorUtils.fillObjectFromResultSet(resultSet, item);
                return item;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception during connection to db - ", e);
            throw new GenericDaoException("Error reading item from DB");
        }
        return null;
    }
}
