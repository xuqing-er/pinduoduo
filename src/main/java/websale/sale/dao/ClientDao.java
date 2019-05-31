package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.Client;

@Mapper
public interface ClientDao {

    @Select("select password from client where phonenumber=#{phonenumber}")
    Client selectClient(String phoneNumber);

    @Insert("insert into client(phonenumber,password,address) values(#{phoneNumber},#{password},#{address})")
    int insertClient(Client client);
}
