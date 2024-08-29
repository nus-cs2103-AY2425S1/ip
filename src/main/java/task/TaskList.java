package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void add(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void delete(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void mark(int index) {
        Task task = taskList.get(index);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmark(int index) {
        Task task = taskList.get(index);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i) + "\n");
        }
        return sb.toString();
    }
}
