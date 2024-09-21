package echochat;

import exceptions.IndexOutOfBoundsError;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();
    private Storage storage = new Storage();

    /**
     * Saves the current list of tasks to a file using the {@link Storage} class.
     */
    public void saveTasksToFile() {
        storage.save(taskList);
    }

    /**
     * Loads the list of tasks from a previously saved file using the {@link Storage} class.
     * Updates the task list with the loaded tasks.
     */
    public void loadTasksFromFile() {
        taskList = storage.load();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return An {@link ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task to the list and saves the updated list to the file.
     *
     * @param task The task to be added to the task list.
     */
    public void addToList(Task task) {
        try {
            taskList.add(task);
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list by index and saves the updated list to the file.
     *
     * @param idx The index of the task to be removed (1-based index).
     * @return The deleted {@link Task}.
     * @throws IndexOutOfBoundsError If the provided index is out of bounds.
     */
    public Task delete(int idx) throws IndexOutOfBoundsError {
        try {
            Task task = taskList.remove(idx - 1); // Convert 1-based index to 0-based index
            saveTasksToFile();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsError();
        }
    }

    /**
     * Returns length of taskList.
     *
     * @return length of taskList.
     */
    public int getLength() {
        return taskList.size();
    }
}
