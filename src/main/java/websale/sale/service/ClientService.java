package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.CartItemDao;
import websale.sale.dao.ClientDao;
import websale.sale.dao.ItemDao;
import websale.sale.dao.OrderDao;
import websale.sale.model.CartItem;
import websale.sale.model.Client;
import websale.sale.model.Item;
import websale.sale.model.Order;
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

    public int addClient(Client client){
        client.setPassword(MD5.next(client.getPassword()));
        return clientDao.insertClient(client);
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

    public List<Order> getOrders(int clientId){ return orderDao.selectByClientId(clientId); }

    public void addItemToCart(CartItem cartItem){
        cartItemDao.insertCartItem(cartItem);
    }

}
