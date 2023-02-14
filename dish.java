public class dish {
    private String name;
    private int price;
    public dish(String n, int p){
        this.name=n;
        this.price=p;
    }
    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }
    public void printDish(){
        System.out.println("Dish:" + this.name);
        System.out.println("Price:" + this.price);
    }
}