package websale.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import websale.sale.biz.ManagerLoginBiz;
import websale.sale.model.Item;
import websale.sale.model.Manager;
import websale.sale.model.Store;
import websale.sale.service.ManagerService;
import websale.sale.utils.ImageUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class ManagerController {
    @Resource
    ManagerService managerService;

    @Autowired
    ManagerLoginBiz managerLoginBiz;

    @RequestMapping(path = "/manager/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/manager/login",method = RequestMethod.POST)
    public String doLogin(
            @RequestParam String phoneNumber,
            @RequestParam String password,
            Model model,
            HttpServletRequest request
    ){
        try{
            int manageId= managerLoginBiz.login(phoneNumber,password);
            request.getSession().setAttribute("id",manageId);
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "404";
        }
        return "redirect:/index";
    }

    @RequestMapping(path = "/manager/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(path = "/manager/register",method = RequestMethod.POST)
    public String doRegister(Manager manager){
        managerService.addManager(manager);
        return "store";
    }

    @RequestMapping(path = "/manager/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("id");
        return "index";
    }

    @RequestMapping(path = "/create/item",method = RequestMethod.POST)
    public String createItem(
            @RequestParam("storeId") int storeId,
            @RequestParam("number")  int number,
            @RequestParam("file") MultipartFile file,
            Item item,
            HttpServletRequest request
    ){

        String path=ImageUtils.saveFile(file,request);
        item.setImagePath(path);
        managerService.createItem(storeId,item,number);
        return "store";
    }

    @RequestMapping(path = "/create/store",method = RequestMethod.POST)
    public String createStore(Store store){
        return "store";
    }

    @RequestMapping(path ="/store")
    public String addItem(
            @RequestParam("storeId") int storeId,
            @RequestParam("itemId") int itemId,
            @RequestParam("number") int number
    ){
        managerService.addItem(storeId,itemId,number);
        return "store";
    }
}
