package com.source.it.itemreceiver.impl;

import com.source.it.itemreceiver.ItemReceiver;
import com.source.it.itemreceiver.exceptions.ItemFault;
import com.source.it.itemreceiver.model.Item;
import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.ItemTypeManager;
import com.source.it.jdbc.manager.ManufactureManager;
import com.source.it.jdbc.manager.WarrantyPeriodManager;
import com.source.it.jdbc.model.ItemType;
import com.source.it.jdbc.model.Manufacture;
import com.source.it.jdbc.model.WarrantyPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.jws.WebService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@WebService(endpointInterface = "com.source.it.itemreceiver.ItemReceiver",
        targetNamespace = "http://itemreceiver.it.source.com/itemReceiver",
        serviceName = "ItemReceiver",
        portName = "ItemReceiverPort",
        wsdlLocation = "ItemReceiver.wsdl")
public class ItemReceiverImpl implements ItemReceiver {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    @Qualifier("manufactureManager")
    private ManufactureManager manufactureManager;

    @Autowired
    @Qualifier("warrantyPeriodManager")
    private WarrantyPeriodManager warrantyPeriodManager;

    @Autowired
    @Qualifier("itemTypeManager")
    private ItemTypeManager itemTypeManager;

    @Autowired
    @Qualifier("manufactureDao")
    private GenericDao manufactureDao;

    @Autowired
    @Qualifier("warrantyPeriodDao")
    private GenericDao warrantyPeriodDao;

    @Autowired
    @Qualifier("itemTypeDao")
    private GenericDao itemTypeDao;

    @Autowired
    @Qualifier("itemDao")
    private GenericDao itemDao;

    @Override
    public String processItem(Item item) throws ItemFault {
        try {
            Manufacture manufacture = checkManufactureAndCreateIfNotExists(item);
            ItemType itemType = checkItemTypeAndCreateIfNotExists(item);
            WarrantyPeriod warrantyPeriod = checkItemWarrantyPeriodAndCreateIfNotExists(item);
            com.source.it.jdbc.model.Item dbItem = createAndSaveItem(item, manufacture, itemType, warrantyPeriod);
            return dbItem.toString();
        } catch (GenericDaoException e) {
            throw new ItemFault("Error saving item to DB", e);
        }
    }

    private com.source.it.jdbc.model.Item createAndSaveItem(Item item, Manufacture manufacture, ItemType itemType, WarrantyPeriod warrantyPeriod) {
        com.source.it.jdbc.model.Item dbItem = new com.source.it.jdbc.model.Item();
        try {
            dbItem.setDateOfSale(new Date(DATE_FORMAT.parse(item.getDateOfSale()).getTime()));
        } catch (ParseException e) {
            throw new ItemFault("Date wrong format, only 'DD-mm-yyyy' is acceptable", e);
        }
        dbItem.setManufacture(manufacture);
        dbItem.setItemType(itemType);
        dbItem.setWarrantyPeriod(warrantyPeriod);
        dbItem.setSerialNumber(item.getSerialNumber());
        itemDao.create(dbItem);
        return dbItem;
    }

    private WarrantyPeriod checkItemWarrantyPeriodAndCreateIfNotExists(Item item) {
        WarrantyPeriod warrantyPeriod = warrantyPeriodManager.getWarrantyPeriodByDays(item.getWarrantyPeriod());
        if (warrantyPeriod == null) {
            warrantyPeriod = new WarrantyPeriod();
            warrantyPeriod.setDays(item.getWarrantyPeriod());
            warrantyPeriod.setWpName(item.getWpName());
            warrantyPeriodDao.create(warrantyPeriod);

        }
        return warrantyPeriod;
    }

    private ItemType checkItemTypeAndCreateIfNotExists(Item item) {
        ItemType itemType = itemTypeManager.getItemTypeByName(item.getItemType());

        if (itemType == null) {
            itemType = new ItemType();
            itemType.setItemTypeName(item.getItemType());
            itemTypeDao.create(itemType);
        }
        return itemType;
    }

    private Manufacture checkManufactureAndCreateIfNotExists(Item item) {
        Manufacture manufacture = manufactureManager.getManufactureByName(item.getManufacture());
        if (manufacture == null) {
            manufacture = new Manufacture();
            manufacture.setManufactureName(item.getManufacture());
            manufactureDao.create(manufacture);
        }
        return manufacture;
    }


}
