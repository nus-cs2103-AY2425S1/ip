package choaticbot.tasks;

import static choaticbot.ui.Ui.printLine;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Provides methods to manage and manipulate tasks, including adding, deleting,
 * listing, marking, unmarking, and filtering tasks.
 */
public class TaskList {
    private ArrayList<Task> tasklist;

    /**
     * Constructs a new {@code TaskList} object with an empty list of tasks.
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        printLine();
        this.tasklist.add(task);
        System.out.println("added: " + task + "\n"
                + "You have " + this.tasklist.size() + " tasks in the list");
        printLine();
    }

    /**
     * Deletes a task from the list of tasks based on the specified index.
     *
     * @param index The index of the task to be deleted (1-based index).
     */
    public void deleteTask(int index) {
        printLine();
        System.out.println("Deleted: " + this.tasklist.get(index - 1) + "\n");
        this.tasklist.remove(index - 1);
        System.out.println("You have " + this.tasklist.size() + " tasks in the list");
        printLine();
    }

    /**
     * Prints the list of tasks with their indices.
     */
    public void listTask() {
        printLine();
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasklist.get(i));
        }
        printLine();
    }

    /**
     * Marks a task as completed based on the specified index.
     *
     * @param index The index of the task to be marked as completed (1-based index).
     */
    public void markTask(int index) {
        printLine();
        this.tasklist.get(index - 1).complete();
        System.out.println("Marked as done:\n" + this.tasklist.get(index - 1));
        printLine();
    }

    /**
     * Marks a task as incomplete based on the specified index.
     *
     * @param index The index of the task to be marked as incomplete (1-based index).
     */
    public void unmarkTask(int index) {
        printLine();
        this.tasklist.get(index - 1).uncomplete();
        System.out.println("Marked as undone:\n" + this.tasklist.get(index - 1));
        printLine();
    }

    /**
     * Filters and prints tasks that contain the specified word in their name.
     *
     * @param word The word to search for in the task names.
     */
    public void filterByWord(String word) {
        printLine();
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).containWord(word)) {
                System.out.println((i + 1) + ". " + this.tasklist.get(i));
            }
        }
        printLine();
    }

    /**
     * Returns the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return this.tasklist;
    }
}
