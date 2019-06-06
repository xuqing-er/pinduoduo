package websale.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import websale.sale.biz.ClientLoginBiz;
import websale.sale.model.*;
import websale.sale.service.BuyService;
import websale.sale.service.CartService;
import websale.sale.service.ClientService;
import websale.sale.utils.BasePhotoUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public String doRegister(
            @Valid Client client,
            Errors errors,
            Model model
    ){
        if (errors.hasErrors()){
            System.out.println("error");
            return "redirect:/index/0";
        }
        int clientid=clientService.addClient(client);
        model.addAttribute("clientId",clientid);
        return "redirect:/index/0";
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
            Integer itemnum= cartService.getItemNum(client.getId());
            request.getSession().setAttribute("id",client.getId());
            request.getSession().setAttribute("username",client.getUserName());
            if (itemnum==null){
                request.setAttribute("itemnum",0);
            }
            else {
                request.getSession().setAttribute("itemnum",itemnum);
            }
            //System.out.println(request.getSession().getMaxInactiveInterval());
        }catch (Exception e){
            e.printStackTrace();
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
            Model model,
            HttpServletRequest request
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        Client client=clientService.getClient(clientId);
        model.addAttribute("client",client);
        Store store=clientService.getStore(id);
        model.addAttribute("store",store);
        Item item=clientService.getItem(id);
        String photo=BasePhotoUtil.encode(item);
        model.addAttribute("item",item);
        model.addAttribute("photo",photo);
        return "item";
    }

    //在主页或者商店添加商品到购物车
    @RequestMapping(path = "/cart/add",method = RequestMethod.GET)
    @ResponseBody
    public int addItemToCart(
            @RequestParam("id") int id,
            HttpServletRequest request
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        CartItem cartItem=new CartItem();
        cartItem.setClientId(clientId);
        cartItem.setItemId(id);
        cartItem.setNumber(1);
        cartService.addCartItem(cartItem);
        int num = cartService.getItemNum(clientId);
        System.out.println(num);
        return num;
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
        return number;
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

    @RequestMapping(path = "/cart/buy",method = RequestMethod.GET)
    public String buy(
            HttpServletRequest request,
            @RequestParam("sum") String sum,
            Model model
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        int orderId=buyService.buy(clientId,sum);
        model.addAttribute("orderid",orderId);
        return "redirect:/orders";
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
        List<Order> orders=clientService.getOrders(clientId,0);
        model.addAttribute("orders",orders);
        return "orders";
    }

    @RequestMapping(path = "/history",method = RequestMethod.GET)
    public String getHistoryOrders(
            HttpServletRequest request,
            Model model
    ){
        int clientId=(Integer) request.getSession().getAttribute("id");
        List<Order> orders=clientService.getOrders(clientId);
        model.addAttribute("orders",orders);
        return "history";
    }

    @RequestMapping(path = "/**")
    public String defaultErrorHandler(Model model){
        model.addAttribute("error", "unknown url");
        return "404";
    }
}