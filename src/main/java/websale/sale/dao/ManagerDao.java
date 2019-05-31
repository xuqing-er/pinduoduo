package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import websale.sale.model.Manager;

@Mapper
public interface ManagerDao {

    @Insert("insert into manager values()")
    int insertManager(Manager manager);

    @Update("update manager set password=#{password} where phonenumber=#{phonebnumber}")
    void updateManagerPassword(String phoneNumber,String password);

    @Update("update manager set phonenumber=#{newphonenumber} where phonenumber=#{phonenumber}")
    void updateManagerPhoneNumber(String phoneNumber, String newPhoneNumber);

    @Select("select * from manager where phonenumber=#{phoneNumber}")
    Manager selectManager(String phoneNumber);
}
