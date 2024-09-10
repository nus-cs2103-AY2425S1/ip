package snah;

import java.util.ArrayList;

import snah.task.Task;
import snah.util.Storage;

/**
 * Class to handle the list of tasks
 */
public class TaskList {

    private ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(Storage storage) {
        this.tasksList = storage.getTaskLists();
    }

    public void add(Task task) {
        tasksList.add(task);
    }

    public Task get(int index) {
        return tasksList.get(index);
    }

    public Task remove(int index) {
        return tasksList.remove(index);
    }

    public void clear() {
        tasksList.clear();
    }

    public int size() {
        return tasksList.size();
    }

    /**
     * Search for the keyword in the tasks list
     * @param keyword
     * @return List of tasks that contain the keyword
     */
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.contains(keyword)) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }

    public void save(Storage storage) {
        storage.saveTaskList(tasksList);
    }

}
