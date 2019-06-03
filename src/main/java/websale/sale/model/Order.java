package websale.sale.model;

import java.time.LocalDateTime;

public class Order {
    int id;
    int status;
    String sum;
    LocalDateTime date;

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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getSum() {
        return sum;
    }


    public void setSum(String sum) {
        this.sum = sum;
    }
}
