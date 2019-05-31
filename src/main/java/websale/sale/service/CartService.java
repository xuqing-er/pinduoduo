package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.CartItemDao;
import websale.sale.dao.ItemDao;
import websale.sale.model.CartItem;
import websale.sale.model.Item;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Resource
    CartItemDao cartItemDao;

    @Resource
    ItemDao itemDao;

    public int addCartItem(CartItem cartItem){
        return cartItemDao.insertCartItem(cartItem);
    }

    public List<Item> getItems(int clientId){
        List<CartItem> cartItems=cartItemDao.selectCartItems(clientId);
        List<Integer> Ids=new ArrayList<>();
        for (CartItem c:cartItems
             ) {
            Ids.add(c.getItemId());
        }
        List<Item> items=itemDao.selectItemsByIds(Ids);
        return items;
    }

    public void removeItem(int clientId,int itemId){
        cartItemDao.deleteCartItem(clientId,itemId);
    }

    public void updateItem(int clientId,int itemId,int number){
        cartItemDao.updateCartItem(clientId,itemId,number);
    }
}
