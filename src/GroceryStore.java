import java.util.*;

/**
 * Created by hari on 25/11/17.
 */

/**

GroceryStore - can add/create/update items to the storeinventory | Print total sales so far
Registers - Bill can be generated using a Register
Bill - can add items to bill | Later generate and commit changes | Or Discard the bill

 */

/*
Test/Run using console
 */

public class GroceryStore extends Registers {


    static ArrayList<Item> StoreInventory = new ArrayList<>();
    static int autoid;
    static ArrayList<Registers> Registers = new ArrayList<>();

    //Specify number of registers in the store
    void initialiseRegisters(int numberOfRegisters){

        for(int i = 1 ; i <= numberOfRegisters ; i++){
            Registers.add(new Registers(i));
        }
    }


    Registers useRegister(int registerId){

        Registers k  = new Registers();

        for(Registers i : Registers){
            if(i.getRegister() == registerId){      //selects register with the specified id
                return i;
            }
        }
        return null;
    }

    Item updateInventory(int itemId,Item updateThis){

        for(Item i : StoreInventory){
            if(i.getId() == itemId){

                System.out.println("\nEnter the item specs to update seperated by comma(,) | Format : (Name,Price,Availablecount,Discount): \n");
                Scanner in = new Scanner(System.in);
                String param = in.next();
                String params[] = param.split(",");


                if(params[0]!= null) {
                    i.setName(params[0]);
                }
                if(params[1].matches("[0-9]+")) {
                    i.setPrice(Double.parseDouble(params[1]));
                }
                if(params[2]!= null) {
                    i.setNumberOfAvailable(Integer.parseInt(params[2]));
                }
                if(params[3]!= null) {
                    i.setDiscount(Integer.parseInt(params[3]));
                }

                return i;
            }
        }

        printInventory();
        return null;
    }

    Item createItem(int numberOfAvailable, String name, Double price){

        Item i = new Item(numberOfAvailable,name,price);
        StoreInventory.add(i);
        return i;
    }


    void printInventory(){

        System.out.println("\nPrinting inventory..\n");
        for(Item i : StoreInventory){
            System.out.println(i.toString());
        }
    }

    void displaySales(){

        System.out.println("\nTotal sales .. ");
        Double totalSales = 0.0;

        for(Item i : StoreInventory){
            System.out.println("Item: #"+i.getId()+"("+i.getName()+")"+" | Sold: "+i.getSold()+"(value: "+(i.getSold()*i.getPrice())+")");
            totalSales += (i.getSold()*i.getPrice());
        }

        System.out.println("Total sales: "+ totalSales+" (excluding discounts)");
    }


    public static void main(String[] args) {

        GroceryStore store = new GroceryStore();

        //Sample Items
        store.createItem(10,"apple",90.00);
        store.createItem(20,"Orange",60.00);
        store.createItem(70,"Banana",6.00);
        store.createItem(4,"Watermelon",120.00);
        store.createItem(12,"Papaya",55.00);
        store.createItem(60,"Cherry",16.00);

        store.printInventory();
        store.initialiseRegisters(4);

        boolean execution = true;

        while(execution){

            System.out.println("\n\n1. Access Store Inventory\n2. Display Sales\n3. Access Register\n4. Quit\n");
            System.out.print("Enter your option here: ");
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();

            switch (option){

                case 1:

                    System.out.println("\nINVENTORY\n\n1. Print Inventory\n2. Update Inventory");
                    in = new Scanner(System.in);
                    System.out.print("\nEnter your option: ");
                    int inventoryoption = in.nextInt();

                    //Print Inventory
                    if(inventoryoption == 1) {
                        store.printInventory();
                    }

                    //Update Inv .. Add discounts ..Change available amount ..  etc..
                    else if(inventoryoption == 2){

                        Item i = new Item();
                        System.out.print("\nEnter the item id to update: #");
                        in = new Scanner(System.in);
                        int itemId = in.nextInt();

                        i = store.updateInventory(itemId,i);

                        System.out.println("\nUpdated: "+ i.toString()+"\n");

                    }
                    break;

                case 2:
                    store.displaySales();
                    break;

                case 3:

                    //Generate/discard Bill
                    while (true){

                        System.out.println("\nPress Enter to proceed to using registers | Press x to go back ->");
                        in = new Scanner(System.in);
                        String opt = in.nextLine();

                        if(!opt.equals("x")) {
                            System.out.println("\nEnter registerId: ");
                            in = new Scanner(System.in);
                            int reg = in.nextInt();
                            Registers registers = store.useRegister(reg);
                            System.out.println("using register " + registers.getRegister());

                            System.out.println("\nPress 1 to add new bill | Press 9 to go back ->");
                            in = new Scanner(System.in);
                            int optionbill = in.nextInt();
                            if (optionbill == 1) {
                                registers.startBilling();
                            } else {
                                continue;
                            }
                        }

                        else {
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n\nquitting program .. ");
                    execution = false;
                    break;

                default:
                    System.out.println("\nInvalid option");
            }
        }
    }
}
