package Bot;

import TaskType.Task;
import TaskType.TaskBuilder;
import java.util.ArrayList;

public class ListManager {
    private ArrayList<Task> ItemList = new ArrayList<>();

    public Task createItem(TaskBuilder builder) {
        Task task = builder.build();
        ItemList.add(builder.build());
        return task;
    }

    /**
     * Method that takes in a string and returns all tasks that includes that string. Can be used
     * to list all items by just passing in a "" argument
     */
    public String listItems(String item) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ItemList.size(); i++) {
            Task task = ItemList.get(i);
            if (task.toString().contains(item)) {  // Check if the task contains the item
                result.append(i + 1).append(". ").append(task.toString()).append("\n");
            }
        }
        return result.toString();
    }

    public void setDone(boolean done, int index) {
        if (index > 0 && index <= ItemList.size()) {
            ItemList.get(index - 1).setDone(done);
        }
    }

    public void delete(int index) {
        if (index > 0 && index <= ItemList.size()) {
            ItemList.remove(index-1);
        }
    }

    public int getItemSize() {
        return ItemList.size();
    }

    public String getItem(int index) {
        if (index > 0 && index <= ItemList.size()) {
            Task task = ItemList.get(index - 1);
            return task.toString();
        }
        return "";
    }


}
