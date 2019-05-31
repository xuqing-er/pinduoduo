package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import websale.sale.model.OrderAndItem;
import websale.sale.model.StoreAndItem;

import java.util.List;

@Mapper
public interface StoreAndItemDao {

    @Insert("insert into storeanditem values (#{storeId},#{itemId},#{number})")
    int insertStoreAndItem(StoreAndItem storeAndItem);

    void updateStoreAndItemNumbers(List<OrderAndItem> list);

    @Update("update storeanditem set number=#{number} where storeid=#{storeId} and itemId=#{itemId}")
    void updateStoreAndItemNumber(int storeId,int itemId,int number);
}
