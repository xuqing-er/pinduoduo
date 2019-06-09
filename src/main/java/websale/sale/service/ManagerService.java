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
        storeDao.insertStore(store);
        int storeId=storeDao.selectMaxId();
        System.out.println(storeId);

        ManagerAndStore managerAndStore=new ManagerAndStore();      //这四行感觉可删
        managerAndStore.setManagerId(managerId);
        managerAndStore.setStoreId(storeId);
        managerAndStoreDao.insertManagerAndStore(managerAndStore);


        return storeId;
    }

    public int createItem(int storeId,Item item,int number){
        item.setInventory(number);
        itemDao.insertItem(item);
        int itemId=itemDao.selectMaxId();
        item.setId(itemId);


        StoreAndItem storeAndItem=new StoreAndItem();           //这四行感觉可删
        storeAndItem.setItemId(itemId);
        storeAndItem.setStoreId(storeId);
        storeAndItemDao.insertStoreAndItem(storeAndItem);
        return itemId;
    }

    public void addItem(int itemId,int inventory){

        itemDao.updateItemInventory(itemId,inventory);
    }

    public int addManager(Manager manager){

        manager.setPassword(MD5.next(manager.getPassword()));
        managerDao.insertManager(manager);
        return managerDao.selectMaxId();
    }

    public Manager getManager(String phoneNumber){
        return managerDao.selectManager(phoneNumber);
    }

    public Integer getStoreId(int mid){
        return managerAndStoreDao.selectByMangerId(mid);
    }

    public Store getStore(int storeid){
        return storeDao.selectById(storeid);
    }

    public void updateItem(Item item){
        itemDao.updateItem(item);
    }

    public void updateManagerPassword(String phoneNumber,String password){
        managerDao.updateManagerPassword(phoneNumber,password);
    }

    public void updateManagerPhoneNumber(String phoneNumber, String newPhoneNumber){
        managerDao.updateManagerPhoneNumber(phoneNumber,newPhoneNumber);
    }
}
