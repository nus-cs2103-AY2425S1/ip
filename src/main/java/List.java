public class List {
    private String[] listItems;
    private int size;

    public List() {
        listItems = new String[100];
        size = 0;
    }

    public void displayList() {
        for (int i = 0; i < size; i++) {
            System.out.println(String.valueOf(i+1) + ". " + listItems[i]);
        }
    }

    public void addItem(String item) {
        listItems[size] = item;
        size++;
    }
}
