package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.Order;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class OrderManager {
    private static final Logger LOGGER = Logger.getLogger(UserManager.class);
    private static final String SELECT_ALL_ORDERS_BY_USER_SQL = "SELECT O.ID, O.USER_ID, O.ITEM_ID, O.DATE, O.WARRANTY, O.STATUS, O.AMOUNT, U.NAME, U.LASTNAME, U.LOGIN, U.PASSWORD, U.EMAIL, U.USER_ROLE_ID, UR.ROLE, I.MANUFACTURE_ID, I.ITEM_TYPE_ID, I.WARRANTY_PERIOD_ID, I.DATEOFSALE, I.SERIALNUMBER, M.MANUFACTURENAME, IT.ITEMTYPENAME, WP.DAYS, WP.WPNAME FROM ORDERS O JOIN USERS U ON O.USER_ID=U.ID JOIN USER_ROLES UR ON U.USER_ROLE_ID=UR.ID JOIN ITEMS I ON O.ITEM_ID=I.ID JOIN MANUFACTURES M ON I.MANUFACTURE_ID=M.ID JOIN ITEM_TYPES IT ON I.ITEM_TYPE_ID=IT.ID JOIN WARRANTY_PERIODS WP ON I.WARRANTY_PERIOD_ID=WP.ID WHERE O.USER_ID=?";

    @Autowired
    protected DataSource dataSource;

    public List<Order> getOrdersByUser(User user) {
        List<Order> orders = new LinkedList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SELECT_ALL_ORDERS_BY_USER_SQL);
            stmt.setLong(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                try {
                    SqlGeneratorUtils.fillObjectFromResultSet(resultSet, order);
                } catch (GenericDaoException e) {/*NOP*/}
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception during connection to db - ", e);
            throw new GenericDaoException("Error reading Order from DB");
        }
        return orders;
    }
}
