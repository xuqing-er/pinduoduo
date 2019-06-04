package websale.sale.dao;

import org.apache.ibatis.annotations.*;
import websale.sale.model.Manager;

@Mapper
public interface ManagerDao {

    @Insert("insert into manager(username,phoneNumber,password) values(#{userName},#{phoneNumber},#{password})")
    @SelectKey(keyProperty = "id",resultType = int.class,before = false,statement = "SELECT LAST_INSERT_ID()")
    int insertManager(Manager manager);

    @Update("update manager set password=#{password} where phonenumber=#{phoneNumber}")
    void updateManagerPassword(@Param("phoneNumber") String phoneNumber,@Param("password") String password);

    @Update("update manager set phonenumber=#{newPhoneNumber} where phonenumber=#{phoneNumber}")
    void updateManagerPhoneNumber(@Param("phoneNumber") String phoneNumber, @Param("newPhoneNumber") String newPhoneNumber);

    @Select("select * from manager where phonenumber=#{phoneNumber}")
    Manager selectManager(String phoneNumber);

    @Select("select max(id) from manager")
    int selectMaxId();
}
