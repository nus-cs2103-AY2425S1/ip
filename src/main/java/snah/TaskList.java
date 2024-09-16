package snah;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
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
    public TaskList search(String keyword) {
        ArrayList<Task> searchResults = tasksList.stream().filter(task -> task.contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(searchResults);
    }

    /**
     * Return stringify of the all the tasks in the list
     * @return Tasks stringified
     */
    public String list() {
        return IntStream.range(0, tasksList.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, tasksList.get(i).toString()))
                .collect(Collectors.joining("\n"));
    }

    public void save(Storage storage) {
        storage.saveTaskList(tasksList);
    }

}
