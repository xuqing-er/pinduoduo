package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.*;
import websale.sale.model.Client;
import websale.sale.model.Item;
import websale.sale.model.Order;
import websale.sale.model.Store;
import websale.sale.utils.MD5;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientService {
    @Resource
    ClientDao clientDao;
    @Resource
    OrderDao orderDao;
    @Resource
    ItemDao itemDao;
    @Resource
    CartItemDao cartItemDao;
    @Resource
    StoreAndItemDao storeAndItemDao;

    public int addClient(Client client){
        client.setPassword(MD5.next(client.getPassword()));
        clientDao.insertClient(client);
        return clientDao.selectMaxId();
    }

    public List<String> getAllLable(){
        return itemDao.getAllLable();
    }

    public List<Item> getItemsByLable(String lable){
        return itemDao.selectItemsByLable(lable);
    }

    public Client getClient(int clientId){
        return clientDao.selectClientById(clientId);
    }

    public Store getStore(int itemId){
        return storeAndItemDao.selectStoreByItemId(itemId);
    }

    public Client getClient(String phoneNumber){
        return clientDao.selectClient(phoneNumber);
    }

    public Item getItem(int itemId){
        return itemDao.selectItem(itemId);
    }

    public List<Item> getItems(int start){
        return itemDao.selectItemsByStart(start*8);
    }

    public List<Order> getOrders(int clientId,int status){ return orderDao.selectByClientIdAndStatus(clientId,status); }

    public List<Order> getOrders(int clientId){
        return orderDao.selectByClientId(clientId);
    }

}
