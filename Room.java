public class Room {
    private int number;
    private int price;
    private int limit;
    private String bed;

    public Room(int n, int p, int l, String b){ //Ensures that every room created needs to have a room number, price, person limit and bed type
        this.number=n;
        this.price=p;
        this.limit=l;
        this.bed=b;
    }
    public int getRoomNumber(){ //returns number of the room
        return this.number;
    }
    public int getPrice(){ //returns the price of the room
        return this.price;
    }
    public int getLimit(){ //returns the limit of the room
        return this.limit;
    }
    public String getBed(){ //returns bed type of the room
        return this.bed;
    }

    public void printRoom(){ //Prints the attributes of the room in a readable format
                System.out.println("Room:" + this.number);
                System.out.println("Room Price:" + this.price);
                System.out.println("Room Limit:" + this.limit);
                System.out.println("Bed type:" + this.bed);
                System.out.println("~~~~~~~~~~~~~~~~~~~~");
            }
}