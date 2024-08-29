package tissue;

import java.util.ArrayList;

public class TaskList {
    private final String INDENT = "       ";
    private ArrayList<Task> taskArray = new ArrayList<>();

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public Task retrieveTask(int index) {
        return taskArray.get(index);
    }

    public int size() {
        return taskArray.size();
    }

    public void add(Task task) {
        taskArray.add(task);
    }

    @Override
    public String toString() {
        String parsedText = "";
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            parsedText += INDENT + (i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }

    public Task deleteTask(int number) {
        return taskArray.remove(number - 1);
    }

    /**
     * Searches for tasks that matches the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> searchKeyword(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : taskArray) {
            if (task.getTask().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
