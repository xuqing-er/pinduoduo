package websale.sale.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

public class Order {
    int id;
    @Max(1) @Min(0)
    int status;
    String sum;
    @Past
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
