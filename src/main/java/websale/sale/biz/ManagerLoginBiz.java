package websale.sale.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import websale.sale.model.Manager;
import websale.sale.model.exceptions.LoginRegisterException;
import websale.sale.service.ManagerService;
import websale.sale.utils.MD5;

@Component
public class ManagerLoginBiz {
    @Autowired
    ManagerService managerService;

    public Manager login(String phoneNumber,String password) throws Exception{
        Manager manager=managerService.getManager(phoneNumber);

        if (manager==null){
            throw new LoginRegisterException("账户不存在");
        }
        if (!StringUtils.pathEquals(MD5.next(password),manager.getPassword())){
            throw new LoginRegisterException("密码不正确");
        }

        return manager;
    }
}
