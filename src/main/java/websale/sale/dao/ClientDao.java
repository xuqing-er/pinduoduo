package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import websale.sale.model.Client;

@Mapper
public interface ClientDao {

    @Select("select * from client where phonenumber=#{phonenumber}")
    Client selectClient(String phoneNumber);

    @SelectKey(keyProperty = "id",resultType = int.class,before = false,statement = "SELECT LAST_INSERT_ID()")
    @Insert("insert into client(phonenumber,password,username,address) values(#{phoneNumber},#{password},#{userName},#{address})")
    int insertClient(Client client);
}
