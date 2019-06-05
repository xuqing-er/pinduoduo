package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.CartItemDao;
import websale.sale.dao.ItemDao;
import websale.sale.model.CartItem;
import websale.sale.model.Item;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Resource
    CartItemDao cartItemDao;

    @Resource
    ItemDao itemDao;

    public void addCartItem(CartItem cartItem){

        CartItem c=cartItemDao.selectCartItem(cartItem.getClientId(),cartItem.getItemId());
        if (c!=null){
            cartItemDao.updateCartItem(c.getClientId(),c.getItemId(),c.getNumber()+1);
            return;
        }
        cartItemDao.insertCartItem(cartItem);
    }

    public Integer getItemNum(int clientId){
        return cartItemDao.selectItemNum(clientId);
    }

    public Map<Item,Integer> getItems(int clientId){
        List<CartItem> cartItems=cartItemDao.selectCartItems(clientId);
        Map<Integer,Integer> itemIdAndSale=new HashMap<>();
        for (CartItem cartitem:cartItems
             ) {
            itemIdAndSale.put(cartitem.getItemId(),cartitem.getNumber());
        }
        List<Item> items=itemDao.selectItemsByClientId(clientId);
        Map<Item,Integer> itemIntegerMap=new LinkedHashMap<>();
        for (Item item:items
             ) {
            itemIntegerMap.put(item,itemIdAndSale.get(item.getId()));
        }
        return itemIntegerMap;
    }

    public void removeItem(int clientId,int itemId){
        cartItemDao.deleteCartItem(clientId,itemId);
    }

    public void updateItem(int clientId,int itemId,int number){
        cartItemDao.updateCartItem(clientId,itemId,number);
    }
}
