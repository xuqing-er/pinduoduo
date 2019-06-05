package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.ManagerAndStore;

@Mapper
public interface ManagerAndStoreDao {

    @Insert("insert into managerandstore(managerid,storeid) values (#{managerId},#{storeId})")
    int insertManagerAndStore(ManagerAndStore managerAndStore);

    @Select("select storeId from managerandstore where managerId=#{managerId}")
    Integer selectByMangerId(int mangerId);
}
