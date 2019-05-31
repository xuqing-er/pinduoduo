package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import websale.sale.model.ManagerAndStore;

@Mapper
public interface ManagerAndStoreDao {

    @Insert("insert into managerandstore values (#{manageId},#{storeId})")
    int insertManagerAndStore(ManagerAndStore managerAndStore);
}
