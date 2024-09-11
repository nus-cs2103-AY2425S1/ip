package thebotfather.util;

import java.util.ArrayList;

import thebotfather.task.Task;

/**
 * The TaskList class manages a list of tasks in the TheBotFather application.
 * It provides methods to add, remove, and modify tasks, as well as to retrieve descriptions
 * of the tasks and save the task list to a file.
 */
public class TaskList {

    /**
     * The count of tasks left to be completed
     */
    protected static int numberOfRemainingTasks = 0;

    /**
     * The initial list of tasks.
     */
    private final ArrayList<Task> taskArrayList;

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
    public void addTask(Task task) throws TheBotFatherException {
        if (taskArrayList.contains(task)) {
            throw new TheBotFatherException("Son, check again.. this task might already be in the list smh");
        }
        taskArrayList.add(task);
        numberOfRemainingTasks++;
    }

    /**
     * Retrieves a description of all tasks in the list.
     *
     * @return A formatted string containing descriptions of all tasks.
     * @throws TheBotFatherException If the task list is empty.
     */
    public String getListDesc() throws TheBotFatherException {
        int size = taskArrayList.size();
        if (size < 1) {
            throw new TheBotFatherException("How do I print what is not there?");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
        }
        stringBuilder.append("Task Count: ").append(size);
        return stringBuilder.toString();
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
                numberOfRemainingTasks--;
            }
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, "
                    + "you don't even have those many tasks son.\n"
                    + "To mark a task as done enter \"mark <index>\"");
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
                numberOfRemainingTasks++;
            }
            task.unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, "
                    + "you don't even have those many tasks son.\n"
                    + "To unmark a task enter \"unmark <index>\"");
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
                numberOfRemainingTasks--;
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, "
                    + "you don't even have those many tasks son.\n"
                    + "To delete a task enter \"delete <index>\"");
        }
    }

    /**
     * Searches for tasks in the list that contain the specified word in their description.
     * <p>
     * This method iterates through the list of tasks and checks if each task's description contains
     * the given word. If a task's description contains the word, that task is added to a new {@link TaskList}.
     * The method then returns this new {@link TaskList} containing all tasks that matched the search criteria.
     *
     * @param word The word to search for in the descriptions of the tasks.
     * @return A {@link TaskList} containing all tasks whose descriptions include the specified word.
     */
    public TaskList findWord(String word) throws TheBotFatherException {
        TaskList taskList = new TaskList(new ArrayList<>());
        for (Task task : taskArrayList) {
            if (task.isWordInDescription(word)) {
                taskList.addTask(task);
            }
        }

        return taskList;
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
