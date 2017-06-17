package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
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

public class ItemTypeManager {
    @Autowired
    protected DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(ItemTypeManager.class);

    public ItemType getItemTypeByName(String name) {
        try (Connection con = dataSource.getConnection()) {
            ItemType itemType = new ItemType();
            String sql = generateSelectByFieldSql(itemType, "ITEMTYPENAME");
            LOGGER.debug("Created sql for ItemType:" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                SqlGeneratorUtils.fillObjectFromResultSet(resultSet, itemType);
                return itemType;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception during connection to db - ", e);
            throw new GenericDaoException("Error reading item type from DB");
        }
        return null;
    }
}
