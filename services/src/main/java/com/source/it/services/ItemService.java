package com.source.it.services;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.ItemManager;
import com.source.it.jdbc.model.Item;
import com.source.it.utils.GetClassUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ItemService {
    public static final Logger LOGGER = Logger.getLogger(GetClassUtil.getClassName());
    @Autowired
    @Qualifier("itemManager")
    private ItemManager itemManager;

    public Item getBySerialNumber(String serialNumber) {
        try {
            return itemManager.getBySerialNumber(serialNumber);
        } catch (GenericDaoException e) {
            LOGGER.info("Didn't found any items by serial number  " + serialNumber, e);
            return null;
        }
    }
}
