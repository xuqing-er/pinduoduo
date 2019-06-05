package websale.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import websale.sale.biz.ClientLoginBiz;
import websale.sale.model.CartItem;
import websale.sale.model.Client;
import websale.sale.model.Item;
import websale.sale.model.Order;
import websale.sale.service.BuyService;
import websale.sale.service.CartService;
import websale.sale.service.ClientService;
import websale.sale.utils.BasePhotoUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {
    @Autowired
    private ClientLoginBiz clientLoginBiz;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BuyService buyService;

    @RequestMapping(path = "/index/{start}", method = RequestMethod.GET)
    public String index(
            @PathVariable("start") int start,
            Model model,
            HttpServletRequest request
    ){
        List<Item> items=clientService.getItems(start);
        List<String> photos=BasePhotoUtil.encodes(items);
        model.addAttribute("items",items);
        model.addAttribute("photos",photos);
        request.getSession().setAttribute("pagenum",start);
        return "index";
    }

    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String doRegister(Client client,Model model){
        int clientid=clientService.addClient(client);
        model.addAttribute("clientId",clientid);
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password,
            Model model,
            HttpServletRequest request
    ){
        try{
            Client client= clientLoginBiz.login(phoneNumber,password);
            int itemnum= cartService.getItemNum(client.getId());
            request.getSession().setAttribute("id",client.getId());
            request.getSession().setAttribute("username",client.getUserName());
            request.getSession().setAttribute("itemnum",itemnum);
            //System.out.println(request.getSession().getMaxInactiveInterval());
        }catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "404";
        }
        return "redirect:/index/0";
    }

    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("username");
        return "redirect:/index/0";
    }

    //当点击商品时，进入单品显示
    @RequestMapping(path = "/item/{id}",method = RequestMethod.GET)
    public String getItem(
            @PathVariable("id") int id,
            Model model){
        model.addAttribute("item",clientService.getItem(id));
        return "item";
    }

    //在主页或者商店添加商品到购物车
    @RequestMapping(path = "/cart/add",method = RequestMethod.GET)
    @ResponseBody
    public CartItem addItemToCart(
            @RequestParam("id") int id,
            HttpServletRequest request
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        CartItem cartItem=new CartItem();
        cartItem.setClientId(clientId);
        cartItem.setItemId(id);
        cartItem.setNumber(1);
        cartService.addCartItem(cartItem);
        return cartItem;
    }

    //在购物车界面增加商品数量
    @RequestMapping(path = "/cart/increase")
    @ResponseBody
    public int increaseItemToCart(
            @RequestParam("id") int id,
            @RequestParam("number") int number,
            HttpServletRequest request
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        cartService.updateItem(clientId,id,number);
        return clientId;
    }

    //用户进入购物车
    @RequestMapping(path = "/cart",method = RequestMethod.GET)
    public String getCart(
            Model model,
            HttpServletRequest request
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        //查询商品
        Map<Item,Integer> items=cartService.getItems(clientId);
        List<String> photos=BasePhotoUtil.encodes(items);
        model.addAttribute("itemmap",items);
        model.addAttribute("photos",photos);
        return "cart";
    }
    //移除商品
    @RequestMapping(path = "/cart/remove")
    @ResponseBody
    public int removeItemFromCart(
            @RequestParam("id") int itemId,
            HttpServletRequest request
    ){
        int clientId=(Integer)request.getSession().getAttribute("id");
        cartService.removeItem(clientId,itemId);
        return itemId;
    }

    @RequestMapping(path = "/cart/buy",method = RequestMethod.POST)
    @ResponseBody
    public int  buy(
            HttpServletRequest request,
            Model model
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        int orderId=buyService.buy(clientId,"1000");
        model.addAttribute("orderid",orderId);
        return orderId;
    }

    @RequestMapping(path = "/cart/pay")
    @ResponseBody
    public int pay(
            @RequestParam("orderId") int orderId,
            HttpServletRequest request
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        buyService.pay(clientId,orderId);
        return 1;
    }

    @RequestMapping(path = "/orders",method = RequestMethod.GET)
    public String getOrders(
            HttpServletRequest request,
            Model model
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        List<Order> orders=clientService.getOrders(clientId);
        model.addAttribute("orders",orders);
        return "orders";
    }
}