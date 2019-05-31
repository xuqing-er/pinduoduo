package websale.sale.model;

import java.util.Date;

public class Order {
    int id;
    int status;
    String sum;
    Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public String getSum() {
        return sum;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
