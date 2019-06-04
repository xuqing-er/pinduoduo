package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import websale.sale.model.Order;

import java.util.List;

@Mapper
public interface OrderDao {

    @Insert("insert into orders(status,sum,date) values (#{status},#{sum},#{date})")
    int insertOrder(Order order);

    @Update("update orders set status=#{status} where id=#{id}")
    void updateOrderStatus(int id,int status);

    @Select("select * from order where id in (select orderid from clientandorder where clientid=#{clientId})")
    List<Order> selectByClientId(int clientId);
}
