package chatkaki.tasks;

import java.util.ArrayList;
import chatkaki.Ui;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();


    public static void addTask(Task task, boolean fromStorage) {
        tasks.add(task);
        if (!fromStorage) {
            Ui.printMessage("Got it. I've added this task:\n " + task + "\n Now you have " + TaskList.getSize() +
                    " task" + (TaskList.getSize() == 1 ? "" : "s") + " in the list.");
        }
    }

    public static Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }
}
