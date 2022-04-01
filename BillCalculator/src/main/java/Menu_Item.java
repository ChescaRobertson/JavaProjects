import java.util.ArrayList;

public class Menu_Item {

    private String name;
    private double price;

    public Menu_Item(String itemname, double itemprice) {
        this.name = itemname;
        this.price = itemprice;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}

