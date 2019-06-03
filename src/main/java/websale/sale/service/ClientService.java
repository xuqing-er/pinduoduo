package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.ClientDao;
import websale.sale.dao.ItemDao;
import websale.sale.model.Client;
import websale.sale.model.Constants;
import websale.sale.model.Item;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientService {
    @Resource
    ClientDao clientDao;

    @Resource
    ItemDao itemDao;

    public int addClient(Client client){
        return clientDao.insertClient(client);
    }

    public Client getClient(String phoneNumber){
        return clientDao.selectClient(phoneNumber);
    }

    public Item getItem(int itemId){
        return itemDao.selectItem(itemId);
    }

    public List<Item> getItems(int start){
        return itemDao.selectItemsByStart(start*Constants.NUM,Constants.NUM);
    }

}
