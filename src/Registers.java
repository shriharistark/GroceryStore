import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by hari on 25/11/17.
 */

/**
 * Register is used to instantiate Bill
 */
public class Registers extends Bill{

    private Integer RegisterId;

    //create register with registerId
    public Registers(Integer registerId) {
        this.RegisterId = registerId;
    }

    public Registers() {
    }

    public Integer getRegister() {
        return RegisterId;
    }

    public void startBilling(){

        System.out.println("\nPress Enter to add items | Press \"x\" to end billing");

        Bill bill = new Bill();                     //Instantiating a Bill
        while (true){

            Scanner in = new Scanner(System.in);
            String move = in.nextLine();
            if(!move.equals("x")){

                System.out.print("Item id: ");
                Scanner initemid = new Scanner(System.in);
                int itemid = initemid.nextInt();

                System.out.print("Item quantity: ");
                Scanner initemquantity = new Scanner(System.in);
                int quantity = initemquantity.nextInt();

                bill.addItem(itemid,quantity);
                System.out.println("#"+itemid+" quantity: "+quantity);
                System.out.println("Press Enter to add more | Press x to end billing ..");
            }

            else {
                break;
            }
        }

        System.out.println("\nPress y to commit bill | Press n to discard bill");

        Scanner in = new Scanner(System.in);
        String move = in.nextLine();
        if(move.equals("y")){
            bill.generateBill();
        }

        else {
            bill.discardBill();
        }
    }


}
