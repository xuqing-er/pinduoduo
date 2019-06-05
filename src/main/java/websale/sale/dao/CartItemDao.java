package websale.sale.dao;

import org.apache.ibatis.annotations.*;
import websale.sale.model.CartItem;

import java.util.List;

@Mapper
public interface CartItemDao {

    @Insert("insert into cartitem values(#{clientId},#{itemId},#{number})")
    int insertCartItem(CartItem cartItem);

    @Select("select sum(number) as itemnum from cartitem where clientid=#{clientId}")
    int selectItemNum(int clientId);

    @Update("update cartitem set number=#{number} where clientId=#{clientId} and itemid=#{itemId}")
    void updateCartItem(@Param("clientId") int clientId,@Param("itemId") int itemId,@Param("number") int number);

    @Delete("delete from cartitem where clientId=#{clientId} and itemid=#{itemId}")
    void deleteCartItem(@Param("clientId") int clientId,@Param("itemId") int itemId);

    @Delete("delete from cartitem where clientid=#{clientId}")
    void deleteCartItems(int clientId);

    @Select("select * from cartitem where clientid=#{clientId}")
    List<CartItem> selectCartItems(int clientId);

    @Select("select * from cartitem where clientId=#{clientId} and itemid=#{itemId}")
    CartItem selectCartItem(@Param("clientId") int clientId,@Param("itemId") int itemId);
}
