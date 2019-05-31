package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import websale.sale.model.ClientAndOrder;

@Mapper
public interface ClientAndOrderDao {

    @Insert("insert into clientandorder values (#{clientId},#{orderId})")
    int insertClientAndOrder(ClientAndOrder clientAndOrder);
}
