package thebotfather.util;

import thebotfather.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks in the TheBotFather application.
 * It provides methods to add, remove, and modify tasks, as well as to retrieve descriptions
 * of the tasks and save the task list to a file.
 */
public class TaskList {

    /**
     * The initial list of tasks.
     */
    private final ArrayList<Task> taskArrayList;

    /**
     * The count of tasks left to be completed
     */
    protected static int NUMBER_OF_REMAINING_TASKS = 0;

    /**
     * Constructs a TaskList instance with an initial list of tasks.
     *
     * @param taskArrayList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Adds a new task to the task list.
     * Increments the count of tasks left to be completed.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
        NUMBER_OF_REMAINING_TASKS++;
    }

    /**
     * Retrieves a description of all tasks in the list.
     *
     * @return A formatted string containing descriptions of all tasks.
     * @throws TheBotFatherException If the task list is empty.
     */
    public String getListDesc() throws TheBotFatherException {
        int size = taskArrayList.size();
        if (size < 1) throw new TheBotFatherException("How do I print what is not there?");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
        }
        return string.toString();
    }

    /**
     * Marks a task as done based on its index in the list.
     * Decrements the count of tasks left to be completed.
     *
     * @param index The index of the task to be marked as done.
     * @throws TheBotFatherException If the index is out of bounds.
     */
    public void markAsDone(int index) throws TheBotFatherException {
        try {
            Task task = taskArrayList.get(index);
            if (!task.isDone()) {
                NUMBER_OF_REMAINING_TASKS--;
            }
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son.\n" +
                    "\tTo mark a task as done enter \"mark <index>\"");
        }
    }

    /**
     * Unmarks a task as not done based on its index in the list.
     * Increments the count of tasks left to be completed.
     *
     * @param index The index of the task to be unmarked.
     * @throws TheBotFatherException If the index is out of bounds.
     */
    public void unmark(int index) throws TheBotFatherException {
        try {
            Task task = taskArrayList.get(index);
            if (task.isDone()) {
                NUMBER_OF_REMAINING_TASKS++;
            }
            task.unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son.\n" +
                    "\tTo unmark a task enter \"unmark <index>\"");
        }
    }

    /**
     * Deletes a task from the list based on its index.
     * Updates the count of tasks left to be completed if the deleted task was not done.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws TheBotFatherException If the index is out of bounds.
     */
    public Task delete(int index) throws TheBotFatherException {
        try {
            Task task = taskArrayList.get(index);
            taskArrayList.remove(index);
            if (!task.isDone()) {
                NUMBER_OF_REMAINING_TASKS--;
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son.\n" +
                    "\tTo delete a task enter \"delete <index>\"");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int numberOfElements() {
        return taskArrayList.size();
    }

    /**
     * Converts the list of tasks to a string format suitable for saving to a file.
     *
     * @return A string representation of the task list for file storage.
     */
    public String toFile() {
        StringBuilder dataInFile = new StringBuilder();
        for (Task task : taskArrayList) {
            dataInFile.append(task.toFile()).append(System.lineSeparator());
        }

        return dataInFile.toString();
    }

    /**
     * Retrieves the description of a task at a specific index in the list.
     *
     * @param index The index of the task.
     * @return The description of the task at the specified index.
     */
    public String getTaskDescAtIndex(int index) {
        return taskArrayList.get(index).toString();
    }
}
