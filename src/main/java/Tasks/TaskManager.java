package Tasks;

import Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Task> tasks;
    public TaskManager() {
        tasks = new ArrayList<>();
    }
    private String getItem(int index) {
        return index + 1 + ". " + tasks.get(index);
    }
    private void print(String str) {
        System.out.println("         " + str);
    }
    public void listTasks() {
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print(getItem(i));
        }
    }
    public void completeTask(int index) {
        Task task = tasks.get(index);
        task.complete();
        print("Nice! I've marked this task as done:");
        print(getItem(index));
    }
    public void unCompleteTask(int index) {
        Task task = tasks.get(index);
        task.unComplete();
        print("OK, I've marked this task as not done yet:");
        print(getItem(index));
    }
    public void addTask(Task task) {
        print("Got it. I've added this task:");
        tasks.add(task);
        String taskStr = tasks.size() > 1 ? "tasks" : "task";
        String message = String.format("Now you have %d %s in the list", tasks.size(), taskStr);
        print(message);
        print(getItem(tasks.size() - 1));
    }
}
