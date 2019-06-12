package websale.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import websale.sale.biz.ManagerLoginBiz;
import websale.sale.model.Item;
import websale.sale.model.Manager;
import websale.sale.model.Store;
import websale.sale.service.ManagerService;
import websale.sale.utils.BasePhotoUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
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
            Manager manager= managerLoginBiz.login(phoneNumber,password);
            Integer storeid=managerService.getStoreId(manager.getId());
            if (storeid==null){
                request.getSession().setAttribute("storeid",0);
            }else {
                request.getSession().setAttribute("storeid",storeid);
            }
            System.out.println(storeid);
            request.getSession().setAttribute("mid",manager.getId());
            request.getSession().setAttribute("username",manager.getUserName());
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "/store";
        }
        return "redirect:/index/0";
    }

    @RequestMapping(path = "/manager/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(path = "/manager/register",method = RequestMethod.POST)
    public String doRegister(
            @Valid Manager manager,
            Errors errors
    ){
        if (errors.hasErrors()){
            System.out.println("manager error");
            return "redirect:/manager/register";
        }
        managerService.addManager(manager);
        return "redirect:/index/0";
    }

    @RequestMapping(path = "/manager/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("mid");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("storeid");
        return "index";
    }

    @RequestMapping(path = "/create/item",method = RequestMethod.POST)
    public String createItem(
            @RequestParam("file") MultipartFile file,
            @RequestParam("number")  int number,
            @Valid Item item,
            Errors errors,
            HttpServletRequest request
    ){
        if (errors.hasErrors()){
            System.out.println("item error");
            return "redirect:/create/item";
        }
        int storeId=(Integer) request.getSession().getAttribute("storeid");
        try {
            item.setPhoto(file.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        //String path=ImageUtils.saveFile(file,request);
        //item.setImagePath(path);
        managerService.createItem(storeId,item,number);
        return "redirect:/store";
    }

    @RequestMapping(path = "/create/item" ,method = RequestMethod.GET)
    public String getItemPage(){
        return "item_post_test";
    }

    @RequestMapping(path = "/create/store",method = RequestMethod.POST)
    public String createStore(
            @Valid Store store,
            Errors errors,
            HttpServletRequest request,
            Model model
    ){
        if (errors.hasErrors()){
            System.out.println("store error");
            return "redirect:/create/store";
        }
        int mid=(Integer) request.getSession().getAttribute("mid");
        int storeId=managerService.createStore(mid,store);
        store.setId(storeId);
        model.addAttribute("storeid",storeId);
        request.getSession().setAttribute("storeid", storeId);
        return "redirect:/store";
    }

    @RequestMapping(path = "/create/store",method = RequestMethod.GET)
    public String getCreateStore(
    ){
        return "store_post_test";
    }

    @RequestMapping(path ="/store")
    public String getStore(
            HttpServletRequest request,
            Model model
    ){
        int managerId=(Integer) request.getSession().getAttribute("mid");
        List<Item> items=managerService.getItems(managerId);
        List<String> strings=BasePhotoUtil.encodes(items);
        int storeid=(Integer) request.getSession().getAttribute("storeid");
        model.addAttribute("store",managerService.getStore(storeid));
        model.addAttribute("photos",strings);
        model.addAttribute("items",items);
        return "store";
    }

    @RequestMapping(path = "/store/stock")
    @ResponseBody
    public int addItemInventory(
            @RequestParam("itemId") int itemId,
            @RequestParam("number") int number
    ){
        managerService.addItem(itemId,number);
        return number;
    }

    @RequestMapping(path = "/item/update",method = RequestMethod.POST)
    public String updateItem(
            @RequestParam("id") int id,
            @RequestParam("number") int number
            //Item item
    ){
        //item.setInventory(number);
        System.out.println("id:"+id);
        //System.out.println(item.getId());
        //managerService.updateItem(item);

        return "redirect:/store";
    }

    @RequestMapping(path = "/delete/item")
    public String removeItem(
            @RequestParam("itemid") int itemid
    ){
        managerService.deleteItem(itemid);
        return "redirect:/store";
    }
}