package websale.sale.dao;

import org.apache.ibatis.annotations.*;
import websale.sale.model.CartItem;

import java.util.List;

@Mapper
public interface CartItemDao {

    @Insert("insert into cartitem values(#{clientId},#{itemId},#{number})")
    int insertCartItem(CartItem cartItem);

    @Update("update cartitem set number=#{number} where clientId=#{clientId} and itemid=#{itemId}")
    void updateCartItem(int clientId,int itemId,int number);

    @Delete("delete cartitem where clientId=#{clientId} and itemid={itemId}")
    void deleteCartItem(int clientId,int itemId);

    @Delete("delete cartitem where clientid=#{clientId}")
    void deleteCartItems(int clientId);

    @Select("select * from cartitem where clientid=#{clientId}")
    List<CartItem> selectCartItems(int clientId);

    @Select("select * from cartitem where clientId=#{clientId} and itemid=#{itemId}")
    CartItem selectCartItem(int clientId,int itemId);
}
