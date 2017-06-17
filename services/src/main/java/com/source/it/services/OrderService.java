package com.source.it.services;

import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.OrderManager;
import com.source.it.jdbc.model.Order;
import com.source.it.jdbc.model.User;
import com.source.it.utils.GetClassUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class OrderService {
    public static final Logger LOGGER = Logger.getLogger(GetClassUtil.getClassName());
    @Autowired
    @Qualifier("orderDao")
    private GenericDao<Order, Long> orderDao;

    @Autowired
    @Qualifier("orderManager")
    private OrderManager orderManager;


    public Order getOrderById(Long id) {
        try {
            return orderDao.read(id);
        } catch (GenericDaoException e) {
            LOGGER.info("Didn't found any orders by id  " + id, e);
            return null;
        }
    }

    public List<Order> getAllOrdersByUser(User user) {
        return orderManager.getOrdersByUser(user);
    }

    public long createOrder(Order order) {
        return orderDao.create(order);
    }
}
