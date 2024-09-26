package chappy.util;

import chappy.exception.CreateTaskException;

import chappy.task.Task;

import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds supplied Task to task list, then saves changes to disk using supplied Storage object.
     *
     * @param task Task object to add.
     * @param storage Storage object for saving to disk.
     * @return String response.
     * @throws IOException If Storage object has error saving to disk.
     */
    public String addTask(Task task, Storage storage) throws IOException {
        this.taskList.add(task);
        String storageResponse = storage.saveToDisk(taskList);
        if (storageResponse == null) {
            return "Alright sir! I've added this task:\n" + task.toString();
        }
        return storageResponse;

    }

    /**
     * Removes Task at supplied deleteIndex from task list, then saves changes to disk using
     * supplied Storage object.
     *
     * @param deleteIndex Index of Task to delete from task list.
     * @param storage Storage object for saving to disk.
     * @return String response.
     * @throws IOException If Storage object has error saving to disk.
     * @throws CreateTaskException If supplied index is out of bounds of task list.
     */
    public String removeTask(int deleteIndex, Storage storage)
            throws IOException, CreateTaskException {
        if (deleteIndex < 0 || deleteIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }

        assert deleteIndex > 0 && deleteIndex <= this.taskList.size() - 1;

        Task task = this.taskList.get(deleteIndex);
        this.taskList.remove(task);
        String storageResponse = storage.saveToDisk(taskList);
        if (storageResponse == null) {
            return "Unfortunate.. I'll remove this task from the list..\n" + task.toString();
        }
        return storageResponse;

    }

    /**
     * Marks Task at supplied markIndex in task list as done, then saves changes to disk using
     * supplied Storage object.
     *
     * @param markIndex Index of Task in task list to mark as done.
     * @param storage Storage object for saving to disk.\
     * @return String response.
     * @throws IOException If Storage object has error saving to disk.
     * @throws CreateTaskException If supplied index is out of bounds of task list.
     */
    public String markTaskAsDone(int markIndex, Storage storage)
            throws IOException, CreateTaskException {
        if (markIndex < 0 || markIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }

        assert markIndex > 0 && markIndex <= this.taskList.size() - 1;

        String response = taskList.get(markIndex).markAsDone();
        String storageResponse = storage.saveToDisk(taskList);
        if (storageResponse == null) {
            return response;
        }
        return storageResponse;

    }

    /**
     * Marks Task at supplied unmarkIndex in task list as not done, then saves changes to disk using
     * supplied Storage object.
     *
     * @param unmarkIndex Index of Task in task list to mark as not done.
     * @param storage Storage object for saving to disk.
     * @return String response.
     * @throws IOException If Storage object has error saving to disk.
     * @throws CreateTaskException If supplied index is out of bounds of task list.
     */
    public String markTaskAsNotDone(int unmarkIndex, Storage storage)
            throws IOException, CreateTaskException {
        if (unmarkIndex < 0 || unmarkIndex > this.taskList.size() - 1) {
            throw new CreateTaskException("Oh SIR! That task index does not exist!");
        }

        assert unmarkIndex > 0 && unmarkIndex <= this.taskList.size() - 1;

        String response = taskList.get(unmarkIndex).markAsNotDone();
        String storageResponse = storage.saveToDisk(taskList);
        if (storageResponse == null) {
            return response;
        }
        return storageResponse;

    }

    public String listTasks() {
        String result = "As requested, here are your outstanding tasks sir:\n";
        for (int i = 0; i < this.taskList.size(); i++) {
            result = result + (i + 1) + "." + this.taskList.get(i).toString() + "\n";
        }
        return result;
    }

    public String findTask(String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        String result = "As requested, here are the tasks matching your keyword sir:\n";

        // int index = 1;
        for (int i = 0; i < this.taskList.size(); i++) {
            String taskString = this.taskList.get(i).toString();
            if (pattern.matcher(taskString).find()) {
                result = result + (i + 1) + "." + taskString + "\n";
            }

        }
        return result;
    }

}
