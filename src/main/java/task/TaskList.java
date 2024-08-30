package task;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTasks(Task t) {
        tasks.add(t);
    }

    public static ArrayList<Task> getTasks(){
        return tasks;
    }
}
