/**
 * Created by hari on 25/11/17.
 */


public class Item extends GroceryStore{

    //POJO Model
    private int id;
    private int numberOfAvailable;
    private String name;
    private Double price;
    private Double discountAmount;
    private int sold;

    public Item(int numberOfAvailable, String name, Double price) {
        this.numberOfAvailable = numberOfAvailable;
        this.name = name;
        this.price = price;
        this.id = ++autoid;
        this.discountAmount = 0.0;
        this.sold = 0;
    }

    public Item(){

    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberOfAvailable() {
        return numberOfAvailable;
    }

    public void setNumberOfAvailable(int numberOfAvailable) {
        this.numberOfAvailable = numberOfAvailable;
    }

    public Item getItem(int id){

        for(Item i : StoreInventory){
            if(i.getId() == id){
                return i;
            }
        }

        return null;
    }

    public Item getItem(String itemName){

        for(Item i : StoreInventory){
            if(i.getName().equals(itemName)){
                return i;
            }
        }

        return null;
    }


    public Double getDiscount() { return discountAmount; }

    public void setDiscount(int discountpercent) {
        this.discountAmount = (this.price) * (discountpercent)/100; //adds discountamount per item
    }

    public int getSold() { return sold; }

    public void setSold(int sold) { this.sold = sold; }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountAmount=" + discountAmount +
                '}'+", available="+ numberOfAvailable;
    }

}
