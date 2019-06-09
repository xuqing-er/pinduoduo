package websale.sale.model;

import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Alias("Store")
public class Store {
    int id;
    int managerId;              //&&&&&&&&&&&&&&&&&&
    @NotNull
    String name;
    @NotNull
    String category;
    @Pattern(regexp = "\\d{11}+")
    String phoneNumber;
    @Max(6) @Min(0)
    int level;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setManagerId(int id) {
        this.managerId = id;
    }        //&&&&&&&&&&&&&&&&&&

    public int getManagerId() {
        return managerId;
    }                //&&&&&&&&&&&&&&&&&&

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
