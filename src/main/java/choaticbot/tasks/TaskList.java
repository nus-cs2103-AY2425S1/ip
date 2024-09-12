package choaticbot.tasks;

import choaticbot.exceptions.ChoaticBotException;

import java.util.ArrayList;
import static choaticbot.ui.Ui.printLine;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasklist;
    //private int numberOfTask;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks
     * @param task The new task
     */
    public void addTask(Task task) {
        printLine();
        this.tasklist.add(task);
        System.out.println("added: " + task + "\n"
                + "You have " + this.tasklist.size() + " tasks in the list");
        printLine();
    }

    /**
     * Deletes a task from the list of tasks
     * @param int The task to be deleted
     */
    public void deleteTask(int index) throws ChoaticBotException {
        if (index <= 0 || index > tasklist.size()) {
            throw new ChoaticBotException("Index out of bounds, there is only " + tasklist.size() + " tasks");
        } else {
            printLine();
            System.out.println("deleted: " + this.tasklist.get(index - 1) + "\n");
            this.tasklist.remove(index - 1);
            System.out.println("You have " + this.tasklist.size() + " tasks in the list");
            printLine();
        }
    }

    /**
     * Prints the list of tasks
     */
    public void listTask() {
        printLine();
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasklist.get(i));
        }
        printLine();
    }

    /**
     * Marks a task in the list of tasks to be completed
     * @param task The task to be marked completed
     */
    public void markTask(int index) throws ChoaticBotException{
        if (index <= 0 || index > tasklist.size()) {
            throw new ChoaticBotException("Index out of bounds, there is only " + tasklist.size() + " tasks");
        } else {
            printLine();
            this.tasklist.get(index - 1).complete();
            System.out.println("Marked as done:\n" + this.tasklist.get(index - 1));
            printLine();
        }
    }

    /**
     * Marks a task in the list of tasks to be uncompleted
     * @param task The task to be marked as completed
     */
    public void unmarkTask(int index) {
        if (index <= 0 || index > tasklist.size()) {
            throw new ChoaticBotException("Index out of bounds, there is only " + tasklist.size() + " tasks");
        } else {
            printLine();
            this.tasklist.get(index - 1).uncomplete();
            System.out.println("Marked as undone:\n" + this.tasklist.get(index - 1));
            printLine();
        }
    }

    public void filterByWord(String word) {
        printLine();
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).containWord(word)) {
                System.out.println();
                System.out.println((i + 1) + ". " + this.tasklist.get(i));
            }
        }
        printLine();
    }

    /**
     * Returns the list of tasks
     * @return An ArrayList of type Task
     */
    public ArrayList<Task> getTasks() {
        return this.tasklist;
    }

    public void clear() {
        this.tasklist.clear();
    }
}