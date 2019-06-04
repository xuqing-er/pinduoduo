package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.*;
import websale.sale.model.*;
import websale.sale.utils.MD5;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerService {
    @Resource
    ManagerDao managerDao;
    @Resource
    ItemDao itemDao;
    @Resource
    StoreAndItemDao storeAndItemDao;
    @Resource
    StoreDao storeDao;
    @Resource
    ManagerAndStoreDao managerAndStoreDao;

    public List<Item> getItems(int managerId){
        return itemDao.selectItemsByManagerId(managerId);
    }

    public int createStore(int managerId,Store store){
        int storeId=storeDao.insertStore(store);
        ManagerAndStore managerAndStore=new ManagerAndStore();
        managerAndStore.setManagerId(managerId);
        managerAndStore.setStoreId(storeId);
        managerAndStoreDao.insertManagerAndStore(managerAndStore);
        return storeId;
    }

    public int createItem(int storeId,Item item,int number){
        item.setInventory(number);
        int itemId=itemDao.insertItem(item);
        item.setId(itemId);
        StoreAndItem storeAndItem=new StoreAndItem();
        storeAndItem.setItemId(itemId);
        storeAndItem.setStoreId(storeId);
        storeAndItemDao.insertStoreAndItem(storeAndItem);
        return itemId;
    }

    public void addItem(int itemId,int number){
        itemDao.updateItemInventory(itemId,number);
    }

    public int addManager(Manager manager){

        manager.setPassword(MD5.next(manager.getPassword()));
        return managerDao.insertManager(manager);
    }

    public Manager getManager(String phoneNumber){
        return managerDao.selectManager(phoneNumber);
    }


    public void updateManagerPassword(String phoneNumber,String password){
        managerDao.updateManagerPassword(phoneNumber,password);
    }

    public void updateManagerPhoneNumber(String phoneNumber, String newPhoneNumber){
        managerDao.updateManagerPhoneNumber(phoneNumber,newPhoneNumber);
    }
}
