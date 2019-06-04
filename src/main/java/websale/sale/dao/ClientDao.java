package websale.sale.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.Client;

@Mapper
public interface ClientDao {

    @Select("select * from client where phonenumber=#{phonenumber}")
    Client selectClient(String phoneNumber);

    int insertClient(Client client);
}
