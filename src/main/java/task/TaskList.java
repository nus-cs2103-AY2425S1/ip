package task;

import java.util.ArrayList;
/**
 * Represents a list of tasks in a task management system.
 */
public class TaskList {

    @SuppressWarnings("checkstyle:VisibilityModifier")
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTasks(Task t) {
        tasks.add(t);
    }
}
