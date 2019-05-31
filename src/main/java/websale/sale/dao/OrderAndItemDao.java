package websale.sale.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.OrderAndItem;

import java.util.List;

@Mapper
public interface OrderAndItemDao {

    int insertOrderAndItems(List<OrderAndItem> orderAndItem);

    @Select("select itemid,number from orderanditem where orderid=#{orderId}")
    List<OrderAndItem> selectOederAndItems(int orderId);
}