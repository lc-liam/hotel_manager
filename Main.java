import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Client> clientList= new ArrayList<Client>(); //Tracks list of clients
    public static void mainMenu(){ //Intro menu to direct user action
        Scanner inputs = new Scanner(System.in); //Creating scanner object that allows user to input information
        System.out.print(
                "Welcome to the Main Menu\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "1. Create account\n" +
                        "2. Sign-in\n" +
                        "3. Admin\n" +
                        "4. Exit\n" +
                        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "Enter your choice: "
        );
        int num = inputs.nextInt(); //Asks user for input
        while(num <1 || num >4){
            System.out.println("You have entered an invalid option");
            System.out.print(
                    "Welcome to the Main Menu\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "1. Create account\n" +
                            "2. Sign-in\n" +
                            "3. Admin\n" +
                            "4. Exit\n" +
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                            "Enter your choice: "
            );
            num = inputs.nextInt(); //Asks user for input
        }
        //Based on what the user inputs, directs to the appropriate menu
        if(num==1){
            createAccount();
        }
        else if(num==2){
                signIn();
        }
        else if(num ==3){
            Admin.adminMenu();
        }
        else{
            System.out.println("The hotel earned: $" + Admin.getEarnings());
            System.out.println("Thank you for using the hotel manager!");
        }
        }
        private static boolean checkName(String name){ //Ensures given name is not already tied to an account
            for(Client i: clientList){
                if(i.getName().equals(name)){
                    return true;
                }
            }
            return false;
        }
        private static void createAccount(){ //Prompts user for input to create their account
            Scanner inputs = new Scanner(System.in);
            boolean cond;
            System.out.println("Please enter a name (must be unique): ");
            String name = inputs.next();
            cond=checkName(name);
            while(cond){ //Ensures given name has not been entered before
                System.out.println("Name must be unique");
                System.out.println("Please enter a name (must be unique): ");
                name = inputs.next();
                cond=checkName(name);
            }
            System.out.println("Please enter a password: ");
            String password = inputs.next();

            clientList.add(new Client(name, password)); //Adds user to the list of clients
            mainMenu(); //Brings user back to main menu
        }
        private static void signIn(){ //Responsible for signing user in
            boolean cond = false;
            //Prompts user to enter their username and password
            Scanner inputs = new Scanner(System.in);
            System.out.println("Enter the account name: ");
            String name = inputs.next();
            System.out.println("Please enter a password: ");
            String password = inputs.next();
            for (Client client : clientList) { //Checks if the user with a matching name and password exist
                if (client.getName().equals(name) && client.checkPassword(password)){
                    client.signInMenu(); //Brings user to their menu
                    cond = true;
                }
            }
            if(!cond){ //If there is no account with the matching password and username
                System.out.println("The account name and/or password is incorrect");
                mainMenu(); //Brings back to main menu
            }

        }
        public static void main(String [] args){
            mainMenu();
        }
    }
