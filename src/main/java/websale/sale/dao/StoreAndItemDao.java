package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import websale.sale.model.StoreAndItem;

@Mapper
public interface StoreAndItemDao {

    @Insert("insert into storeanditem values (#{storeId},#{itemId})")
    int insertStoreAndItem(StoreAndItem storeAndItem);


}
