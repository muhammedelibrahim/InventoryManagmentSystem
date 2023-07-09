package inventorymanagmentsystem;

/**
 *
 * @author Muhammed
 */
public class product {
    private int ID;
    private String name;
    private float price;
    private int catID;
    private String categoray;

    public product(String name, float price, int catID) {
        this.name = name;
        this.price = price;
        this.catID = catID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public String getCategoray() {
        return categoray;
    }

    public void setCategoray(String categoray) {
        this.categoray = categoray;
    }

    public product(int id ,String name, float price, String categoray) {
        this.ID = id;
        this.name = name;
        this.price = price;
        this.categoray = categoray;
    }
    
}
