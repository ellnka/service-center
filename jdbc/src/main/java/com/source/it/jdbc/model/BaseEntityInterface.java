package com.source.it.jdbc.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface BaseEntityInterface<PK extends Serializable> {
    PK getId();
    void setId(PK id);
    void prepareCreateStatement(PreparedStatement stmt) throws SQLException;
    void prepareReadOrDeleteStatement(PreparedStatement stmt, PK id) throws SQLException;
}
