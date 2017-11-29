import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hari on 25/11/17.
 */


/**
 * Adds items to list "bill" also keep track of availablity of items while adding
 * Hashmap needed for calculation of real-time inventory/availability of items
 * Changes are commited in the generatebill()
 * Bill can be discarded in discardBill()
 */
public class Bill {

    private ArrayList<Item> bill = new ArrayList<>();
    private HashMap<Item,Integer> itemquantity = new HashMap<>();
    private Double total;
    boolean billgenerated = false;

    public Bill() {

        this.bill = new ArrayList<>();
        this.itemquantity = new HashMap<>();
        this.total = 0.0;
    }

    void addItem(int itemId,int quantity){

        Item i = new Item();
        i = i.getItem(itemId);

        if(i != null) {

            if(itemquantity.containsKey(i)) {

                int available = i.getNumberOfAvailable() - itemquantity.get(i);
                if(available > 0) {

                    itemquantity.put(i, itemquantity.get(i) + quantity);
                    System.out.println("\t debug avail: " + (i.getNumberOfAvailable() - itemquantity.get(i)));

                }

                else {
                    System.out.println("\n"+i.getName() + " ITEM NOT AVAILABLE ON STORE! UPDATE INVENTORY!");
                }

            }

            else {
                if(i.getNumberOfAvailable() >= quantity) {
                    itemquantity.put(i, quantity);
                    bill.add(i);
                    System.out.println("\t debug avail: "+(i.getNumberOfAvailable() - itemquantity.get(i)));
                }

                else {
                    System.out.println("\n"+i.getName() + " ITEM NOT AVAILABLE ON STORE! UPDATE INVENTORY!");
                    System.out.println("\t debug avail: "+(i.getNumberOfAvailable()));

                }
            }
        }

        else {
            System.out.println("\nNot a valid item\n");
        }

        for(Item k : bill){
            System.out.print(k.getId()+" ");
        }
        System.out.println();
    }

    void generateBill(){

        if(!billgenerated) {
            Double discount = 0.0;
            System.out.println("\nGenerating bill .. at register - "+"\n");
            System.out.println("\n#ID\tNAME\tPRICE\tQUANTITY\tDISCOUNT");
            for (Item i : bill) {
                i.setNumberOfAvailable(i.getNumberOfAvailable() - itemquantity.get(i));
                i.setSold(i.getSold()+itemquantity.get(i));

                System.out.println(i.getId() + "\t" + i.getName().substring(0, 4) + "\t" + i.getPrice() + "\t" + itemquantity.get(i) + "\t"+i.getDiscount());

                if (i.getDiscount() > 0) {
                    discount += i.getDiscount()*itemquantity.get(i);
                }
                this.total += (i.getPrice() * itemquantity.get(i));
            }

            System.out.println("\nGross total: " + this.total);
            this.total -= discount;
            System.out.println("Net total: "+(this.total)+" (Discount: "+discount+")");
            this.billgenerated = true;
        }

    }

    void discardBill(){

        System.out.println("\nDiscarding bill ..\n");
        if(billgenerated) {
            for (Item i : bill) {
                i.setNumberOfAvailable(i.getNumberOfAvailable() + itemquantity.get(i));
            }
            bill.removeAll(bill);
        }

        else {
            System.out.println("Bill cannot be discarded without generating ...");
            bill.removeAll(bill);
        }
    }

}
