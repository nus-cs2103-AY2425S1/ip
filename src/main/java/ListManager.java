public class ListManager {
    private String[] ItemList = new String[100];

    // Keeps track of the smallest index that has empty value
    private int ListIndex = 0;

    public void createItem(String item){
        ItemList[ListIndex] = item;
        ListIndex += 1;
        return;
    }

    public String listItems() {
        String result = "";
        for (int i = 0; i < ListIndex; i++) {
            result += (i + 1) + ". " + ItemList[i] + "\n";
        }
        return result;
    }
}
