package tasks;

import exceptions.InvalidTaskException;

import java.util.ArrayList;

public class List {

    private ArrayList<Task> tasks;

    public List(){
        this.tasks = new ArrayList<>(100);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list currently
     *
     * @return Number of tasks
     */
    public int numOfTasks() {
        return tasks.size();
    }

    /**
     * Adds a task to the current list
     *
     * @param task A task to be added
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a string to be displayed when user commands "list"
     *
     * @return A string of list of tasks
     */
    public String displayList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(".").append(tasks.get(i).displayTask());
        }
        return list.toString();
    }

    public Task getTask(int index) throws InvalidTaskException {
        if (tasks.isEmpty() || index + 1 > tasks.size()) {
            throw new InvalidTaskException(index);
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task of a specified index
     *
     * @param index Index of task specified by user
     * @throws InvalidTaskException If index of task does not exist
     */
    public Task deleteTask(int index) throws InvalidTaskException {
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        } catch (Exception e) {
            throw new InvalidTaskException(index);
        }
    }

    /**
     * Returns a list of tasks containing a specific keyword
     *
     * @param keyword Keyword specified by user
     * @return List of tasks containing the keyword
     */
    public List filterByKeyword(String keyword) {
        List filteredList = new List();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                filteredList.addTaskToList(task);
            }
        }
        return filteredList;
    }
}
