package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.ItemDao;
import websale.sale.dao.ManagerDao;
import websale.sale.dao.StoreAndItemDao;
import websale.sale.model.Item;
import websale.sale.model.Manager;
import websale.sale.model.StoreAndItem;

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

    public List<Item> getItems(int storeId){
        return itemDao.selectItemsByStoreId(storeId);
    }

    public int createItem(int storeId,Item item,int number){
        StoreAndItem storeAndItem=new StoreAndItem();
        storeAndItem.setItemId(item.getId());
        storeAndItem.setStoreId(storeId);
        storeAndItem.setNumber(number);
        storeAndItemDao.insertStoreAndItem(storeAndItem);
        return itemDao.insertItem(item);
    }

    public void addItem(int storeId,int itemId,int number){
        storeAndItemDao.updateStoreAndItemNumber(storeId,itemId,number);
    }

    public int addManager(Manager manager){
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
