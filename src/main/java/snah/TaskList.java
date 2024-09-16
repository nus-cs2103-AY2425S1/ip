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
     * Search for relevant keywords found in the tasks
     * @param keyword Keyword to search for
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

    /**
     * Return stringify of the all the tasks in the list
     * @return Tasks stringified
     */
    public String list() {
        String response = "";
        for (int i = 0; i < tasksList.size(); i++) {
            response += String.format("%d. %s\n", i + 1, tasksList.get(i));
        }
        return response;
    }

    public void save(Storage storage) {
        storage.saveTaskList(tasksList);
    }

}
