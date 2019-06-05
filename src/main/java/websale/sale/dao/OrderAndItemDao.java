package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.OrderAndItem;

import java.util.List;

@Mapper
public interface OrderAndItemDao {

    @Insert({"<script> insert into orderanditem (orderid,itemid,number) values" +
            "        <foreach collection=\"list\" item=\"item\" open=\"\" separator=\",\" close=\"\">" +
            "            (#{item.orderId},#{item.itemId},#{item.number})" +
            "        </foreach>" +
            "</script>"})
    int insertOrderAndItems(List<OrderAndItem> orderAndItem);

    @Insert("insert into orderanditem (orderid,itemid,number) values (#{orderId},#{itemId},#{number})")
    int insertOrderAndItem(OrderAndItem orderAndItem);

    @Select("select itemid,number from orderanditem where orderid=#{orderId}")
    List<OrderAndItem> selectOederAndItems(int orderId);
}