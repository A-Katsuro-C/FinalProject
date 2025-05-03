import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ResturantOrderSystem{
    private ArrayList<String> addToOrder = new ArrayList<>();
    private String[] checkoutOrder = new String[15];
    int index = 0;
    int itemsOrdered = 0;

    public static int getUserInput() {
        System.out.print("Please select the order that is complete: ");
        Scanner input = new Scanner(System.in);
        String userInput = input.next();
        try{
            //input.nextLine();
            input.nextLine();
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(e + "\nYou did not give me an integer value.");
            System.out.println("Try again!");
            return getUserInput(); // Recursive call
        }
        
    }

    
    public void addToOrder(String order){
        addToOrder.add(order);
        System.out.println("Order added: " + order + "\n");
    }

    public void checkout(){
        String order;
        for (int i = 0; i < addToOrder.size(); i++){
            System.out.print("[" + (i + 1) + "] ");  
        }
        System.out.println ("\n" + addToOrder);
        if (!addToOrder.isEmpty()){
            order = addToOrder.remove(getUserInput() - 1);
            if(getUserInput() > addToOrder.size()){
                System.out.println("Out of Range, Please try again");
            }
            if (index < checkoutOrder.length){
                checkoutOrder[index] = order;
                index++;
            }
            System.out.println("Orders Completed: " + order + "\n");
            itemsOrdered++;
        } else {
            System.out.println("No Orders Added.\n");
        }
    }

    public void transactions(){
        System.out.print("Current Orders: " + addToOrder + " ");
        System.out.print("Completed Orders: ");
        for (int i = 0; i < itemsOrdered; i++){
            System.out.print("[" + checkoutOrder[i] + "] ");
        }
        System.out.println();
    }

    public void loadOrder(){
        try(BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))){
            String order;
            while ((order = reader.readLine()) != null){
                addToOrder.add(order);
            }
            System.out.println("Order for pickup! Added to current orders.");
        } catch (IOException error) {
            System.out.println("Error loading orders: " + error.getMessage());
        }
    }

    public void saveOrder(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt"))){
            for (String order : addToOrder){
                writer.write(order);
                writer.newLine();
            }
            System.out.println("Orders saved in File");
        } catch (IOException error) {
            System.out.println("Error saving orders: " + error.getMessage());
        }
    }



    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ResturantOrderSystem Menu = new ResturantOrderSystem();
        int choice;
        String order;
        boolean state = true;
        while (state){
            try{
                System.out.println("1. Add to Order\n2. Checkout\n3. View All Transactions\n4. Load Data\n5. Save Data\n6. Exit");
                System.out.println("What option do you choose?: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice){
                    case 1:
                        System.out.println("Enter the order you wish to add: ");
                        order = input.nextLine();
                        Menu.addToOrder(order);
                        break;
                    case 2: 
                        Menu.checkout();
                        break;
                    case 3: 
                        Menu.transactions();
                        break;
                    case 4: 
                        Menu.loadOrder();
                        break;
                    case 5:
                        Menu.saveOrder();
                        break;
                    case 6:
                        state = false;
                        break;
                    default:
                        System.out.println("Invalid Number, please try again.\n");
                }
            } catch (Exception error){
                System.out.println("Error: Invalid input. Please Try again\n");
                input.nextLine();
            }
        }
    }
}
