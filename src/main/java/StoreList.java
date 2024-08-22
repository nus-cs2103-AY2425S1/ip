import java.util.ArrayList;

public class StoreList {
    private ArrayList<String> items;

    public StoreList() {
        items = new ArrayList<>();
    }

    /**
     * Adds item to list
     *
     * @param item task to be added.
     */
    public void addItem(String item) {
        items.add(item);
        System.out.println("    added: " + item);
    }

    //getter
    public ArrayList<String> getItems() {
        return items;
    }

    /**
     * Returns items in list
     *
     */
    public void displayItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + items.get(i));
        }
    }

}
