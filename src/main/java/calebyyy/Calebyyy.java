package calebyyy;

import calebyyy.Tasks.Task;
import java.util.ArrayList;

public class Calebyyy {
    private ArrayList<Task> tasks;
    private CommandManager commandManager;

    public Calebyyy() {
        tasks = new ArrayList<>();
        commandManager = new CommandManager(this);
    }

    public void start() {
        greet();
        commandManager.startCommandLoop();
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void stop() {
        System.exit(0);
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        new Calebyyy().start();
    }
}