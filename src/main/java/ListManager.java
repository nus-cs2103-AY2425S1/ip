import TaskType.Task;
import TaskType.TaskBuilder;

public class ListManager {
    private Task[] ItemList = new Task[100];

    // Keeps track of the smallest index that has empty value
    private int ListIndex = 0;

    public void createItem(TaskBuilder builder) {
        ItemList[ListIndex] = builder.build();
        ListIndex++;
    }

    public String listItems() {
        String result = "";
        for (int i = 0; i < ListIndex; i++) {
            Task task = ItemList[i];
            result += (i + 1) + ". " + task.toString() + "\n";
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
