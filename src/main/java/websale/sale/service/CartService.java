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

    public int addCartItem(CartItem cartItem){
        return cartItemDao.insertCartItem(cartItem);
    }

    public Map<Item,Integer> getItems(int clientId){
        List<CartItem> cartItems=cartItemDao.selectCartItems(clientId);
        Map<Integer,Integer> idNumberMap=new HashMap<>();
        for (CartItem c:cartItems
             ) {
            idNumberMap.put(c.getItemId(),c.getNumber());
        }
        List<Item> items=itemDao.selectItemsByIds(idNumberMap.keySet());
        Map<Item,Integer> itemIntegerMap=new LinkedHashMap<>();
        for (Item item:items
             ) {
            itemIntegerMap.put(item,idNumberMap.get(item.getId()));
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
