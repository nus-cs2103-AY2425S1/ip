package Bot;

import TaskType.Task;
import TaskType.TaskBuilder;
import java.util.ArrayList;

public class ListManager {
    private ArrayList<Task> ItemList = new ArrayList<>();

    public void createItem(TaskBuilder builder) {
        ItemList.add(builder.build());
    }

    public String listItems() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ItemList.size(); i++) {
            Task task = ItemList.get(i);
            result.append(i + 1).append(". ").append(task.toString()).append("\n");
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
