package websale.sale.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import websale.sale.model.Client;
import websale.sale.model.exceptions.LoginRegisterException;
import websale.sale.service.ClientService;
import websale.sale.utils.MD5;

@Component
public class ClientLoginBiz {
    @Autowired
    private ClientService clientService;

    public int login(String phoneNumber,String password) throws Exception{
        Client client =clientService.getClient(phoneNumber);

        if (client==null){
            throw new LoginRegisterException("账户不存在");
        }
        if (!StringUtils.pathEquals(MD5.next(password),client.getPassword())){
            throw new LoginRegisterException("密码不正确");
        }

        return client.getId();
    }
}
