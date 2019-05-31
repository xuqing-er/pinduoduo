package websale.sale.service;

import org.springframework.stereotype.Service;
import websale.sale.dao.*;
import websale.sale.model.CartItem;
import websale.sale.model.ClientAndOrder;
import websale.sale.model.Order;
import websale.sale.model.OrderAndItem;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BuyService {
    @Resource
    OrderAndItemDao orderAndItemDao;

    @Resource
    StoreAndItemDao storeAndItemDao;

    @Resource
    CartItemDao cartItemDao;

    @Resource
    OrderDao orderDao;

    @Resource
    ClientAndOrderDao clientAndOrderDao;

    public void pay(int clientId,int orderId){
        List<OrderAndItem> list=orderAndItemDao.selectOederAndItems(orderId);
        storeAndItemDao.updateStoreAndItemNumbers(list);
        cartItemDao.deleteCartItems(clientId);
    }

    public void buy(int clientId){
        Order order=new Order();
        order.setDate(new Date());
        int orderId=orderDao.insertOrder(order);
        ClientAndOrder clientAndOrder=new ClientAndOrder();
        clientAndOrder.setOrderId(orderId);
        clientAndOrder.setClientId(clientId);
        clientAndOrderDao.insertClientAndOrder(clientAndOrder);

        List<CartItem> cartItems=cartItemDao.selectCartItems(clientId);
        List<OrderAndItem> orderAndItems=new ArrayList<>();
        OrderAndItem orderAndItem;
        for (CartItem c:cartItems
             ) {
            orderAndItem=new OrderAndItem();
            orderAndItem.setOrderId(orderId);
            orderAndItem.setItemId(c.getItemId());
            orderAndItem.setNumber(c.getNumber());
            orderAndItems.add(orderAndItem);
        }
        orderAndItemDao.insertOrderAndItems(orderAndItems);
    }
}
