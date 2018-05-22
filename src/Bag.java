import java.util.ArrayList;

public class Bag {

    ArrayList<Item> bag = new ArrayList<>();


    public void addItem(Item item){
        bag.add(item);
    }

    public void removeItem(Item item){
        bag.remove(item);
    }

}
