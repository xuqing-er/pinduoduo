package websale.sale.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import websale.sale.model.ManagerAndStore;

@Mapper
public interface ManagerAndStoreDao {

    @Insert("insert into managerandstore values (#{manageId},#{storeId})")
    int insertManagerAndStore(ManagerAndStore managerAndStore);

    @Select("select storeId from mangerandstore where mangerId=#{managerId}")
    int selectByMangerId(int mangerId);
}
