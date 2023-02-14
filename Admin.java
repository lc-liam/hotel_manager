import java.util.ArrayList;
import java.util.Scanner;
public class Admin {
    private static ArrayList<Room> roomList= new ArrayList<Room>(); //Tracks current rooms
    private static ArrayList<Room> bookedRooms = new ArrayList<>();
    private static int earnings = 0; //Tracks earnings
    private static void addRoom(int n, int p, int l, String b){ //Adds room to the hotel
        //Need to add defensive coding (no same room numbers)
            roomList.add(new Room(n,p,l,b));
            System.out.println("Room has been added successfully!");
    }
    public static ArrayList<Room> getRoomList(){ //Responsible for getting the list of rooms
        return roomList;
    }
    public static ArrayList<Room> getBookedRoomList(){ //Responsible for getting the list of booked rooms
        return bookedRooms;
    }
    private static void print(){ // Responsible for printing the list of rooms in a readable format
        if(roomList.size() == 0 && bookedRooms.size() == 0){
            System.out.println("There has been no rooms created");
        }
        else {
            System.out.println("Available Rooms\n" +
                    "~~~~~~~~~~~~~~~~~");
            for (Room i : roomList) {
                i.printRoom();
            }
            System.out.println("Booked Rooms\n" +
                    "~~~~~~~~~~~~~~~");
            for (Room i : bookedRooms) {
                i.printRoom();
            }
        }
    }
    public static void adminMenu(){ //Responsible for prompting user input for admin
        Scanner inputs = new Scanner(System.in);
        System.out.print(
                "***Admin Menu***\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "1. View Rooms!\n" +
                        "2. Create a Room!\n" +
                        "3. See Current Dishes in Restaurant!\n" +
                        "4. Add Dishes to Restaurant\n" +
                        "5. View Current Earnings\n" +
                        "6. Exit\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "Enter your choice: ");
        int choice = inputs.nextInt();
        while(choice!= 1 && choice !=2 && choice!=3 && choice!=4 && choice !=5 && choice!=6){ //Ensures user has entered a valid option
            System.out.println("You have entered an incorrect input");
            System.out.print(
                    "***Admin Menu***\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "1. View Rooms!\n" +
                            "2. Create a Room!\n" +
                            "3. See Current Dishes in Restaurant!\n" +
                            "4. Add Dishes to Restaurant\n" +
                            "5. View Current Earnings\n" +
                            "6. Exit\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Enter your choice: ");
            choice = inputs.nextInt();
        }
        //Brings user to the corresponding menu given their choice
        if(choice ==1){ //Prints rooms
            print();
            adminMenu();
        }
        else if(choice==2) { //Allows admin to create rooms by asking various questions to create the room
            boolean cond = true;
            System.out.println("Let's create a room!");
            System.out.print("Please set the room number(It must not be a previously existing room number): ");
            int roomNum=inputs.nextInt();
            //Ensures that they do not enter a previous room number
            for (Room i : roomList) {
                if(i.getRoomNumber() == roomNum) {
                    cond = false;
                }
            }
            for (Room i : bookedRooms) {
                if(i.getRoomNumber() == roomNum) {
                    cond = false;
                }
            }
            while (!cond) { //Ensures user will enter a valid room number
                cond = true;
                System.out.println("It appears that the room number entered already exists.");
                System.out.print("Please set another room number: ");
                roomNum = inputs.nextInt();
                for (Room i : roomList) {
                    if (i.getRoomNumber() == roomNum) {
                        cond = false;
                    }
                }
                for (Room i : bookedRooms) {
                    if(i.getRoomNumber() == roomNum) {
                        cond = false;
                    }
                }
            }
            System.out.print("Please enter the price for the room: ");
            int price = inputs.nextInt();
            while(price <0){
                System.out.println("Why are you trying to pay someone to sleep in your hotel?");
                System.out.print("Please enter the price for the room: ");
                price = inputs.nextInt();
            }
            System.out.print("Please enter how many people are supposed to be in the room: ");
            int limit = inputs.nextInt();
            while(limit <1){
                System.out.println("You can't have a room with no one in it..." );
                System.out.print("Please enter how many people are supposed to be in the room: ");
                limit = inputs.nextInt();
            }
            System.out.print("Please enter the bed type of the room: ");
            String bed = inputs.next();
            addRoom(roomNum, price, limit, bed); //Adds room to the list of rooms with the given info from the admin
            adminMenu(); //Brings the admin back to the admin menu
        }
        else if(choice ==3){ //Displays current restaurant menu
            Restaurant.displayMenu();
            adminMenu();
        }
        else if(choice==4){ //Adds dishes to menu
            System.out.println("Let's add some dishes to the restaurant!");
            System.out.print("Please name the dish you want to add: ");
            String dName = inputs.next();
            System.out.print("Please enter the price of the dish: ");
            int dPrice = inputs.nextInt();
            Restaurant.addDish(dName, dPrice);
            adminMenu();
        }
        else if(choice==5){ //Prints total earnings
            System.out.println("Total earnings: $" + earnings);
            adminMenu();
        }
        else{ //Goes back to mainMenu
            Main.mainMenu();
        }
    }
    public static void bookRoom(int i){ //Changes the availability of a room in the list of rooms to booked
        bookedRooms.add(roomList.get(i));
        roomList.remove(i);
    }
    public static void unBookRoom(int i){ //unbooks a room
        roomList.add(bookedRooms.get(i));
        bookedRooms.remove(i);
    }
    public static void addEarnings(int money){ //Adds money to the total earnings
        earnings+= money;
    } //Adds money to earnings
    public static int getEarnings(){ //Returns the amount of money earned
        return earnings;
    } //Returns the amount of earnings
}
