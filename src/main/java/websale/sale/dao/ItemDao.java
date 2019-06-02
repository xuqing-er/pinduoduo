package websale.sale.dao;

import org.apache.ibatis.annotations.*;
import websale.sale.model.Item;

import java.util.List;
import java.util.Set;

@Mapper
public interface ItemDao {

    @Select("select * from item where id=#{id}")
    Item selectItem(int id);

    @Select("select * from item limit #{start},10")
    List<Item> selectItemsByStart(int start);

    List<Item> selectItemsByIds(Set<Integer> list);

    @Select("select * from item where storeid=#{storeId}")
    List<Item> selectItemsByStoreId(int storeId);

    @Update("update item set price=#{price} where id=#{id}")
    void updateItemPrice(String id,String price);

    @SelectKey(keyProperty = "id",resultType = int.class,before = false,statement = "SELECT LAST_INSERT_ID()")
    @Insert("insert into item values (#{name},#{category},#{price},#{discount},#{descriptor},#{imagePath})")
    int insertItem(Item item);
}
