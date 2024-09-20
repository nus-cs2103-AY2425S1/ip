package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in a task management system.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void addTasks(Task t) {
        tasks.add(t);
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void setTasks(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }
}
