package mahesh.util;

import java.util.ArrayList;

import mahesh.task.Task;

/**
 * Manages a list of tasks, providing methods to add, delete, mark, unmark, and print tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the ArrayList of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list and increments the task count.
     *
     * @param task The task to be added to the list.
     */
    public void addToList(Task task) {
        this.list.add(task);
        Ui.printTaskAdded(task, this.list.size());
    }

    /**
     * Deletes a task from the list at the specified index and decrements the task count.
     *
     * @param index The index of the task to be removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public void deleteFromList(int index) {
        try {
            Task task = this.list.get(index);
            this.list.remove(index);
            Ui.printTaskDeleted(task, this.list.size());
        } catch (IndexOutOfBoundsException err) {
            Ui.printNoSuchTaskErr(this.list.size());
        }
    }

    /**
     * Marks the task at the specified index as done and prints a confirmation message.
     * If the index is out of bounds, prints an error message.
     *
     * @param index the index of the task to be marked as done
     */
    public void markTaskAsDone(int index) {
        try {
            Task task = this.list.get(index);
            task.markAsDone();
            Ui.printMarkedAsDone(task);
        } catch (IndexOutOfBoundsException err) {
            Ui.printNoSuchTaskErr(index);
        }
    }

    /**
     * Unmarks the task at the specified index as done and prints a confirmation message.
     * If the index is out of bounds, prints an error message.
     *
     * @param index the index of the task to be unmarked as done
     */
    public void unmarkTaskAsDone(int index) {
        try {
            Task task = this.list.get(index);
            task.unmarkAsDone();
            Ui.printUnmarkedAsDone(task);
        } catch (IndexOutOfBoundsException err) {
            Ui.printNoSuchTaskErr(index);
        }
    }

    /**
     * Prints all tasks in the list. Prints error message if the list is empty.
     */
    public void printList() {
        if (this.list.size() == 0) {
            Ui.printEmptyListErr();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : this.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
        Ui.printDivider();
    }

    /**
     * Converts the TaskList to a string format suitable for saving to a file.
     *
     * @return a string representation of the TaskList for file storage
     */
    public String toFileData() {
        StringBuilder fileData = new StringBuilder();
        for (Task task : list) {
            fileData.append(task.toFileEntry() + System.lineSeparator());
        }
        return fileData.toString();
    }
}
