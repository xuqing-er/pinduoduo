package websale.sale.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import websale.sale.model.Item;
import websale.sale.model.OrderAndItem;

import java.util.List;

@Mapper
public interface ItemDao {

    @Select("select * from item where id=#{id}")
    @Results({
            @Result(property = "photo",column = "samllphoto",jdbcType = JdbcType.BLOB)
    })
    Item selectItem(int id);

    @Select("select max(id) from item")
    int selectMaxId();

    @Select("select * from item limit #{start},8")
    @Results({
            @Result(property = "photo",column = "samllphoto",jdbcType = JdbcType.BLOB)
    })
    List<Item> selectItemsByStart(int start);

    List<Item> selectItemsByIds(List<Integer> list);

    @Select("select * from item where id in (select itemid from cartitem where clientid=#{clientid})")
    @Results({
        @Result(property = "photo",column = "samllphoto",jdbcType = JdbcType.BLOB)
    })
    List<Item> selectItemsByClientId(int clientId);


    @Select("select * from item where id in " +
            "(select itemid from storeanditem where storeid =(select storeid from managerandstore where managerId=#{managerId}))")
    @Results({
            @Result(property = "photo",column = "samllphoto",jdbcType = JdbcType.BLOB)
    })
    List<Item> selectItemsByManagerId(int managerId);

    @Update("update item set price=#{price} where id=#{id}")
    void updateItemPrice(@Param("id")String id,@Param("price") String price);

    @Update("update item set number=#{number} where id=#{itemId}")
    void updateItemInventory(@Param("itemId") int itemId,@Param("number") int number);

    @Insert("insert into item(name,category,price,discount,descriptor,imagepath,inventory,samllphoto) values " +
            "(#{name},#{category},#{price},#{discount},#{descriptor},#{imagePath},#{inventory},#{photo})")
    int insertItem(Item item);

    @Update("update item set inventory=inventory-#{number} where id=#{itemid}")
    void updateItemByOrder(OrderAndItem orderAndItem);
}
