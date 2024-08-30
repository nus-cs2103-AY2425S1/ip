package knight2103.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs an object containing an empty list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs an object containing an existent list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList(ArrayList<Task> storageData) {
        this.taskList = storageData;
    }

    /**
     * Returns the number of tasks in the bot's taskList.
     *
     * @return Number of tasks in the bot's taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns a String which represents a formatted list of tasks in the bot.
     *
     * @return Formatted List of task in the bot's application.
     */
    public String printToList() {
        String stringToPrint = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + this.taskList.get(i) + "\n";
        }
        return stringToPrint;
    }

    /**
     * Returns a String which will be saved in the bot's storage file.
     *
     * @return Formatted List of task to be saved in storage file.
     */
    public String printToFile() {
        String stringToWrite = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            stringToWrite += this.taskList.get(i).saveToFileFormat() + "\n";
        }
        return stringToWrite;
    }

    /**
     * Adds a task into the bot's taskList.
     *
     * @param newTask Task to be added to the taskList.
     */
    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Returns the task that is being marked as done. The task identified by its index
     * will be marked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be marked.
     * @return The newly marked-as-done Task.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task mark(int index) throws IndexOutOfBoundsException {
        taskList.get(index).markDone();
        return taskList.get(index); // must be after markDone to return the newly updated one
    }

    /**
     * Returns the task that is being unmarked as done. The task identified by its index
     * will be unmarked as done in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be unmarked.
     * @return The newly unmarked-as-done Task.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task unmark(int index) throws IndexOutOfBoundsException {
        taskList.get(index).unmarkDone();
        return taskList.get(index); // must be after unmarkDone to return the newly updated one
    }

    /**
     * Returns the task that is being deleted. The task identified by its index
     * will be deleted in the bot's taskList.
     *
     * @param index The index of the taskList that refers to the task to be deleted.
     * @return The Task to be deleted.
     * @throws IndexOutOfBoundsException If index indicating the Task is out of range
     * of the taskList.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    }
}
