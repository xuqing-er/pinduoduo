package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import websale.sale.model.Store;

import java.util.List;

@Mapper
public interface StoreDao {

    @Insert("insert into store values (#{name},#{category},#{phoneNumber},#{level)")
    int insertStore(Store store);

    @Select("select * from store where store.id in " +
            "(select storeid from storeanditem where itemid=" +
            "(select id from item where itemname=#{itemName}))")
    List<Store> selectByItem(String itemName);

    @Update("update store set name=#{name} where id=#{id}")
    void updateStoreName(String id, String name);

    @Update("update store set phonenumber=#{phoneNumber} where id={id}")
    void updateStorePhoneNumber(String id, String phoneNumber);
}
