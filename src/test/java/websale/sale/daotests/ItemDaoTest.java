package websale.sale.daotests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import websale.sale.dao.ItemDao;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {
    @Resource
    ItemDao itemDao;

    @Test
    public void test(){
        /*Item item=new Item();
        item.setPrice("16.9");
        item.setId(10);
        //item.setDiscount(20);
        itemDao.updateItem(item);*/
    }
}
