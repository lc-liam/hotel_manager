import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String name;
    private String password;
    private int bill;
    private ArrayList<Room> bookings = new ArrayList<>();
    public Client(String name, String password){ //Ensures that every client must have a corresponding username and password
        this.name = name;
        this.password = password;
    }
    public boolean checkPassword(String pass){ //Given a password, checks if it matches with the password set
        return this.password.equals(pass);
    }
    public String getName(){ //Returns the name of the client
        return this.name;
    }
    public void signInMenu() { //Prompts client for various choices
        Scanner inputs = new Scanner(System.in);
        System.out.print(
                "***" + name + "'s " + "Menu***\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "1. View Bookings!\n" +
                        "2. Book a Room!\n" +
                        "3. Go to Restaurant!\n" +
                        "4. View Current Bill!\n" +
                        "5. Exit\n" +
                        "6. Checkout\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "Enter your choice: ");
        int input = inputs.nextInt(); //Prompts user to input choice
        while(input<1 || input >6){ //Ensures user enters correct input
            System.out.println("It appears that you have entered an incorrect input");
            System.out.print(
                    "***" + name + "'s " + "Menu***\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "1. View Bookings!\n" +
                            "2. Book a Room!\n" +
                            "3. Go to Restaurant!\n" +
                            "4. View Current Bill!\n" +
                            "5. Exit\n" +
                            "6. Checkout\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Enter your choice: ");
            input = inputs.nextInt();
        }
        if(input == 1){ //Prints the client's booked rooms
            if(this.bookings.size()==0){
                System.out.println("You currently have no rooms booked");
                this.signInMenu();

            }
            else {
                System.out.println("Booked Rooms\n" +
                        "~~~~~~~~~~~~~~~~~~~");
                for (Room booking : this.bookings) {
                    booking.printRoom();
                }
                this.signInMenu();
            }
            }
        else if(input ==2){ //Allows client to book a room
            if(Admin.getRoomList().size() ==0){
                System.out.println("There is no rooms available, please come back later");
                this.signInMenu();
            }
            else {
                System.out.println("Current Rooms\n" +
                        "~~~~~~~~~~~~~~~~~~~");
                for (int i = 0; i < Admin.getRoomList().size(); i++) { //Prints current rooms
                    System.out.println("Option:" + (i + 1));
                    Admin.getRoomList().get(i).printRoom();
                }

                System.out.print("Which room would you like to book(Please enter option # )? ");
                int roomNumber = (inputs.nextInt());
                while (roomNumber < 1 || roomNumber > Admin.getRoomList().size()) { //Ensures user enters a valid roomNumber
                    System.out.println("You have entered an incorrect room choice, please choose again");
                    System.out.print("Which room would you like to book(Please enter option # )? ");
                    roomNumber = inputs.nextInt();
                }
                this.bookings.add(Admin.getRoomList().get(roomNumber - 1)); //Adds the room to the list of booked rooms for the specific client
                this.bill += Admin.getRoomList().get(roomNumber - 1).getPrice(); //Adds the price of the room to the bill of the client
                Admin.bookRoom(roomNumber - 1); //Changes the availability of the booked room
                System.out.println("Room Booked Successfully!");
                this.signInMenu();
            }

        }
        else if(input==3){ //Runs the restaurant menu
            this.restaurantOptions();
        }
        else if(input ==4){
            System.out.println("The current bill is: $" + bill); //Gives current bill
            this.signInMenu();
        }
        else if(input ==5){ //Brings back to main menu
            Main.mainMenu();
        }
        else{ //Resets everything back to 0 and adds the client's bill to the admin's earnings
            System.out.println("The total bill is $" + bill);
            Admin.addEarnings(bill);
            bill=0;
            System.out.println("Thank you for staying with us!");
            for(Room booking: bookings){
                for(int i=0; i< Admin.getBookedRoomList().size(); i++){
                    if(booking.getRoomNumber()==Admin.getBookedRoomList().get(i).getRoomNumber()){ //Changes room availability for booked rooms
                        Admin.unBookRoom(i);
                    }
                }
            }
            for(int i=bookings.size()-1; i>=0; i--){ //Removes bookings from client
                bookings.remove(i);
            }
            Main.mainMenu(); //Brings back to main menu
        }
    }
    private void restaurantOptions(){ //Asks the client what they want to do inside the restaurant
        Scanner inputs = new Scanner(System.in);

        System.out.println("Welcome to the restaurant!");
        System.out.print(
                "***Restaurant Options***\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "1. Order!\n" +
                        "2. Exit!\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "Enter your choice: ");
        int choice = inputs.nextInt();
        while(choice!= 1 && choice !=2){
            System.out.println("It appears you have entered an incorrect input");
            System.out.print(
                    "***Restaurant Options***\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "1. Order!\n" +
                            "2. Exit!\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Enter your choice: ");
            choice = inputs.nextInt();
        }
        if(choice == 1) { //Displays the menu and prompts the user to order
            Restaurant.displayMenu();
            if (Restaurant.getMenu().size() == 0) { //If there is nothing on the restaurant menu, then it should bring the client back to the main restaurant menu
                this.restaurantOptions();
            } else {
                System.out.print("What would you like from the menu(Please enter the #)? ");
                int menuOption = inputs.nextInt();
                while (menuOption < 1 || menuOption > Restaurant.getMenu().size()) { //Makes sure they enter a valid input
                    System.out.println("You have entered an incorrect option");
                    Restaurant.displayMenu();
                    System.out.print("What would you like from the menu(Please enter the #)? ");
                    menuOption = inputs.nextInt();
                }
                this.bill += Restaurant.getMenu().get(menuOption - 1).getPrice(); //Adds the price of the food to the bill of the client
                System.out.println("The food was delivered and enjoyed!");
                this.restaurantOptions();
            }
        }
        else{
            this.signInMenu();
        }
    }
}
