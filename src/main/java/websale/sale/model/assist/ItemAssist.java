package websale.sale.model.assist;

import websale.sale.model.Item;

public class ItemAssist {
    Item item;
    String photo;

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
