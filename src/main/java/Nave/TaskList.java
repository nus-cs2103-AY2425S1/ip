package Nave;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String countTasks() {
        return "You have " + tasks.size() + " tasks now.";
    }

    public String listItems() {
        //Check if there are any tasks
        if (tasks.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        for (Task t : tasks) {
            String currItem = tasks.indexOf(t) + 1 + "." + t + "\n";
            fullList.append(currItem);
        }
        fullList.append("That's all, you can do this!");
        return fullList.toString();
    }

    public String markItem(int place) {
        try {
            Task curr = tasks.get(place - 1);
            return curr.mark()
                ? "Well done! I have checked this item off the list:\n" + curr
                : "This item was already marked as done previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public String unmarkItem(int place) {
        try {
            Task curr = tasks.get(place - 1);
            return curr.unmark()
                ? "Oh no! It's ok, I will uncheck this item for now:\n" + curr
                : "This item was already unchecked previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public String deleteItem(int place) {
        try {
            Task deleted = tasks.remove(place - 1);
            return "I have taken this item off the list:\n" + deleted +
                    "\n" + countTasks();
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }
}
