package friday.task;

import friday.util.FridayException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws FridayException {
        tasks.remove(index);
    }

    public void findTasks(String keyword) {
        if (keyword.trim().isEmpty()) {
            System.out.println("\tPlease provide a keyword to search for.");
            return;
        }

        System.out.println("\tHere are the matching tasks in your list:");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.toString().toLowerCase();
            String[] words = description.split("\\s+");

            for (String word : words) {
                if (word.equals(keyword.toLowerCase())) {
                    System.out.println("\t" + (i + 1) + "." + task);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("\tNo matching tasks found.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
