package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.Store;
import websale.sale.model.StoreAndItem;

@Mapper
public interface StoreAndItemDao {

    @Insert("insert into storeanditem values (#{storeId},#{itemId})")
    int insertStoreAndItem(StoreAndItem storeAndItem);

    @Select("select * from store where id in(select storeid from storeanditem where itemid=#{itemId}) ")
    Store selectStoreByItemId(int itemId);

}
