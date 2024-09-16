package tasks;

import java.util.ArrayList;

public class TaskList {
    // Contains the task list e.g., it has operations to add/delete tasks in the list
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {

    };

    public TaskList(Task[] tasks) {
        for (Task t : tasks) {
            this.tasks.add(t);
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
