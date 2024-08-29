package Bot;

import TaskType.Task;
import TaskType.TaskBuilder;
import java.util.ArrayList;

/**
 * The ListManager class manages a collection of tasks, allowing for creation, listing,
 * updating, and deletion of tasks. It uses an ArrayList to store the tasks and provides
 * methods for interacting with this list.
 */
public class ListManager {
    private ArrayList<Task> ItemList = new ArrayList<>();

    public Task createItem(TaskBuilder builder) {
        Task task = builder.build();
        ItemList.add(builder.build());
        return task;
    }

    public String listItems() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ItemList.size(); i++) {
            Task task = ItemList.get(i);
            result.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Sets the completion status of a task at the specified index.
     *
     * @param done  The completion status to set (true for done, false for not done).
     * @param index The index of the task in the itemList (1-based index).
     */
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

    /**
     * Retrieves the string representation of the task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based index. We will handle it to be 0-based in this method with a -1).
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public String getItem(int index) {
        if (index > 0 && index <= ItemList.size()) {
            Task task = ItemList.get(index - 1);
            return task.toString();
        }
        return "";
    }


}
