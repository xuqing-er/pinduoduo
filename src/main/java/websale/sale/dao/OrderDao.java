package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import websale.sale.model.Order;

@Mapper
public interface OrderDao {

    @Insert("insert into order values (#{status},#{sum},#{date})")
    int insertOrder(Order order);

    @Update("update order set status=#{status} where id=#{id}")
    void updateOrderStatus(int id,int status);
}
