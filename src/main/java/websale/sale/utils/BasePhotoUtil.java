package websale.sale.utils;

import org.springframework.util.Base64Utils;
import websale.sale.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasePhotoUtil {
    public static List<String> encodes(List<Item> items){
        List<String> strings=new ArrayList<>();
        for (Item item:items
                ) {
            strings.add(Base64Utils.encodeToString(item.getPhoto()));
            item.setPhoto(null);
        }
        return strings;
    }

    public static List<String> encodes(Map<Item,Integer> items){

        List<String> strings=new ArrayList<>();
        for (Item item:items.keySet()
                ) {
            strings.add(Base64Utils.encodeToString(item.getPhoto()));
            item.setPhoto(null);
        }
        return strings;
    }

    public static String encode(Item item){

        String string=Base64Utils.encodeToString(item.getPhoto());
        item.setPhoto(null);
        return string;
    }
}
