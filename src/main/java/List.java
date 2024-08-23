import java.util.ArrayList;
public class List {

    private ArrayList<String> items = new ArrayList<String>();

    public List() {
    
    }

    public void appendItem(String item) {
        items.add(item);
    }

    public ArrayList<String> getItems() {
        return items;
        
    }


}
