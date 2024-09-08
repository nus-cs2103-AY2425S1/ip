package knight2103.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an object containing an empty list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs an object containing an existent list of tasks stored as an ArrayList.
     * This list is called the taskList.
     */
    public TaskList(ArrayList<Task> storageData) {
        this.tasks = storageData;
    }

    /**
     * Returns the number of tasks in the bot's taskList.
     *
     * @return Number of tasks in the bot's taskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a String which represents a formatted list of tasks in the bot.
     *
     * @return Formatted List of task in the bot's application.
     */
    public String printToList() {
        String stringToPrint = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + this.tasks.get(i) + "\n";
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
        for (int i = 0; i < this.tasks.size(); i++) {
            stringToWrite += this.tasks.get(i).saveToFileFormat() + "\n";
        }
        return stringToWrite;
    }

    /**
     * Returns a String which represents a formatted list of tasks
     * that contain the word to be searched.
     *
     * @return Formatted list of tasks that contain the search word.
     */
    public String searchPrintToList(String wordSearch) {
        String stringToPrint = "";
        int bulletPoint = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            Task searchedTask = this.tasks.get(i);
            if (searchedTask.getDescription().contains(wordSearch)) {
                bulletPoint++;
                stringToPrint += bulletPoint + ". " + searchedTask + "\n";
            }
        }
        if (bulletPoint == 0) {
            stringToPrint = "NIL: There is no matching tasks.\n";
        }
        return stringToPrint;
    }

    /**
     * Adds a task into the bot's taskList.
     *
     * @param newTask Task to be added to the taskList.
     */
    public void add(Task newTask) {
        this.tasks.add(newTask);
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
    public Task mark(int index) throws IndexOutOfBoundsException { // modify Command
        tasks.get(index).markDone();
        return tasks.get(index); // must be after markDone to return the newly updated one
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
        tasks.get(index).unmarkDone();
        return tasks.get(index); // must be after unmarkDone to return the newly updated one
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
        Task taskToDelete = tasks.get(index);
        tasks.remove(index);
        return taskToDelete;
    }
}
