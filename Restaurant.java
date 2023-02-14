import java.util.ArrayList;
public class Restaurant {

    private static ArrayList<dish> menu= new ArrayList<>();

    public static void displayMenu(){ //Displays the current restaurant menu
        if(menu.size() ==0){
            System.out.println("Currently, there are no dishes on the menu");
        }
        else {
            System.out.println("***Menu***");
            for (int i = 0; i < menu.size(); i++) { //Prints out each dish on the menu
                System.out.println(i + 1 + ".");
                menu.get(i).printDish();
                System.out.println("~~~~~~~~~~");
            }
        }
    }
    public static void addDish(String name, int price){ //Adds a dish to the restaurant menu
        menu.add(new dish(name, price));
        System.out.println("Dish has been added successfully!");
    }
    public static ArrayList<dish> getMenu(){ //Returns menu
        return menu;
    }
}
