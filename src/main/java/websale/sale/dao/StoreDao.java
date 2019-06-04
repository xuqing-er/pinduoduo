package websale.sale.dao;

import org.apache.ibatis.annotations.*;
import websale.sale.model.Store;

import java.util.List;

@Mapper
public interface StoreDao {

    @SelectKey(keyProperty = "id",resultType = int.class,before = false,statement = "SELECT LAST_INSERT_ID()")
    @Insert("insert into store (name,category,phonenumber,level) values (#{name},#{category},#{phoneNumber},#{level})")
    int insertStore(Store store);

    @Select("select * from store where store.id in " +
            "(select storeid from storeanditem where itemid=" +
            "(select id from item where itemname=#{itemName}))")
    List<Store> selectByItem(String itemName);

    @Update("update store set name=#{name} where id=#{id}")
    void updateStoreName(@Param("id") String id, @Param("name") String name);

    @Update("update store set phonenumber=#{phoneNumber} where id={id}")
    void updateStorePhoneNumber(@Param("id") String id, @Param("phoneNumber") String phoneNumber);
}
