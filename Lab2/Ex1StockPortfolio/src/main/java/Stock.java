public class Stock {
    private String name;
    private int quantity;

    public Stock(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }
}
