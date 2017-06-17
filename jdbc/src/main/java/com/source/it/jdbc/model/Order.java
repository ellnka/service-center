package com.source.it.jdbc.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Order extends BaseEntity<Long> {
    private User user;
    private Item item;
    private Date date;
    private boolean warranty;
    private Status status;

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setLong(1, user.getId());
        stmt.setLong(2, item.getId());
        stmt.setDate(3, date);
        stmt.setBoolean(4, warranty);
        stmt.setString(5, status.name());
    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (warranty != order.warranty) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (item != null ? !item.equals(order.item) : order.item != null) return false;
        if (date != null ? !date.toString().equals(order.date.toString()) : order.date != null) return false;
        return status == order.status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", item=" + item +
                ", date=" + date +
                ", warranty=" + warranty +
                ", status=" + status +
                '}';
    }
}
