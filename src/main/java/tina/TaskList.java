package tina;

import tina.task.Task;

import java.util.ArrayList;

/**
 * The <code>TaskList</code> class manages a list of tasks and handles operations such as adding, marking, unmarking, listing, and deleting tasks.
 * It interacts with the <code>Storage</code> class to persist the task list to a file.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private final Storage storage;

    /**
     * Constructs a new <code>TaskList</code> object with a specified <code>Storage</code> object.
     * The task list is initialized by reading from storage file.
     *
     * @param storage The <code>Storage</code> object that handles file operations for the task list.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.list = storage.read();
    }

    /**
     * Adds a task to the list and saves the updated list to storage.
     * Displays a confirmation message including the new task and the total number of tasks.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDes());
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
        storage.write(list);
    }

    /**
     * Marks a task as completed and saves the updated list to storage.
     * Displays a confirmation message including the marked task.
     *
     * @param x The index of the task to be marked as completed.
     */
    public void markTask(int x) {
        Task currTask = list.get(x - 1);
        currTask.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currTask.getDes());
        storage.write(list);
    }

    /**
     * Unmarks a task and saves the updated list to storage.
     * Displays a confirmation message including the unmarked task.
     *
     * @param x The index of the task to be unmarked.
     */
    public void unmarkTask(int x) {
        Task currTask = list.get(x - 1);
        currTask.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currTask.getDes());
        storage.write(list);
    }

    /**
     * Lists all tasks in the task list with their current status.
     * Displays the tasks with their respective indices.
     */
    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            System.out.printf("%d.%s%n", i + 1, currTask.getDes());
        }
    }

    /**
     * Deletes a task from the list and saves the updated list to storage.
     * Displays a confirmation message including the deleted task and the total number of remaining tasks.
     *
     * @param x The index of the task to be deleted.
     */
    public void deleteTask(int x) {
        Task currTask = list.remove(x - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + currTask.getDes());
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
        storage.write(list);
    }

    public void findTask(String input) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            if(currTask.description().contains(input)) {
                System.out.printf("%d.%s%n", count, currTask.getDes());
                count++;
            }
        }
    }
}
