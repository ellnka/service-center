package com.source.it.jdbc.model;

import java.io.Serializable;

public abstract class BaseEntity <PK extends Serializable> implements BaseEntityInterface<PK> {
    protected PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : (int)(((Long)id).longValue() % Integer.MAX_VALUE);
    }
}