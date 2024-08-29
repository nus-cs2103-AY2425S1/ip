package tasks;

import java.util.ArrayList;
import static ui.Ui.printLine;

public class TaskList {
    private ArrayList<Task> tasklist;
    //private int numberOfTask;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public void addTask(Task task) {
        printLine();
        this.tasklist.add(task);
        System.out.println("added: " + task + "\n"
                + "You have " + this.tasklist.size() + " tasks in the list");
        printLine();
    }

    public void deleteTask(int index) {
        printLine();
        System.out.println("deleted: " + this.tasklist.get(index - 1) + "\n");
        this.tasklist.remove(index - 1);
        System.out.println("You have " + this.tasklist.size() + " tasks in the list");
        printLine();
    }

    public void listTask() {
        printLine();
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasklist.get(i));
        }
        printLine();
    }

    public void markTask(int index) {
        printLine();
        this.tasklist.get(index - 1).complete();
        System.out.println("Marked as done:\n" + this.tasklist.get(index - 1));
        printLine();
    }

    public void unmarkTask(int index) {
        printLine();
        this.tasklist.get(index - 1).uncomplete();
        System.out.println("Marked as undone:\n" + this.tasklist.get(index - 1));
        printLine();
    }

    public ArrayList<Task> getTasks() {
        return this.tasklist;
    }

    public void clear() {
        this.tasklist.clear();
    }
}