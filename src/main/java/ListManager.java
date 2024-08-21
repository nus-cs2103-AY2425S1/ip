public class ListManager {
    private Task[] ItemList = new Task[100];

    // Keeps track of the smallest index that has empty value
    private int ListIndex = 0;

    public void createItem(String item) {
        ItemList[ListIndex] = new Task(item);
        ListIndex += 1;
        return;
    }

    public String listItems() {
        String result = "";
        for (int i = 0; i < ListIndex; i++) {
            Task task = ItemList[i];
            result += (i + 1) + ". " + task.getStatusIcon() + " " + task.getDescription() + "\n";
        }
        return result;
    }

    public void setDone(boolean done, int index) {
        ItemList[index-1].setDone(done);
    }

    public String getItem(int index) {
        return ItemList[index - 1].getStatusIcon() + " " + ItemList[index - 1].getDescription();
    }

}
