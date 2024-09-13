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
    private ArrayList<Task> itemList = new ArrayList<>();

    public Task createItem(TaskBuilder builder) {
        Task task = builder.build();
        System.out.println("Printing out task infomation");
        for (int i = 0; i < itemList.size(); i++) {
            Task task2 = itemList.get(i);
            System.out.println(task2 + "task2 info");
            System.out.println(task + "current task info");
            if (task2.isEqual(task)) {
                System.out.println("This item already exists!");
                return task;
            }
        }
        itemList.add(builder.build());
        return task;
    }

    /**
     * Method that takes in a string and returns all tasks that includes that string. Can be used
     * to list all items by just passing in a "" argument
     */
    public String listItems(String item) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < itemList.size(); i++) {
            Task task = itemList.get(i);
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
        boolean isValidIndex = index > 0 && index <= itemList.size();

        assert isValidIndex : "Index out of range";

        if (isValidIndex) {
            itemList.get(index - 1).setDone(done);
        }
    }

    public void delete(int index) {
        boolean isValidIndex = index > 0 && index <= itemList.size();

        assert isValidIndex : "Index out of range";

        if (isValidIndex) {
            itemList.remove(index-1);
        }
    }

    public int getItemSize() {
        return itemList.size();
    }

    /**
     * Retrieves the string representation of the task at the specified index.
     *
     * @param index The index of the task to retrieve (1-based index. We will handle it to be 0-based in this method with a -1).
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public String getItem(int index) {
        boolean isValidIndex = index > 0 && index <= itemList.size();

        assert index >= 0:"Index for getItem is negative!";
        if (isValidIndex) {
            Task task = itemList.get(index - 1);
            return task.toString();
        }
        return "";
    }


}
