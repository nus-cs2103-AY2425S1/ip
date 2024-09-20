package blitz;

import java.util.ArrayList;
import java.util.stream.Collectors;

import exception.BlitzException;
import task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList object with specified initial ArrayList.
     *
     * @param list ArrayList that may or may not contain Task objects for TaskList initialization.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns a list of tasks that matched the specified pattern.
     *
     * @param pattern The String pattern to match against the task.
     * @throws BlitzException If error occurs while processing Task String.
     */
    public TaskList getMatchedTasks(String pattern) throws BlitzException {
        TaskList matchedTaskList = convertStringListToTaskList(list.stream()
                .map(Task::convertTaskToString)
                .filter(str -> str.contains(pattern))
                .collect(Collectors.toCollection(ArrayList::new)));

        return matchedTaskList;
    }

    /**
     * Checks if TaskList is empty.
     *
     * @return True if ArrayList in TaskList is not empty, false otherwise.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of Task objects in the TaskList.
     *
     * @return The number of elements in the ArrayList of TaskList.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Adds specified Task object to TaskList.
     *
     * @param task Task object that is to be added to the TaskList.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a Task object (specified by index) in the TaskList.
     *
     * @param i Index of the Task object to be deleted.
     * @return Task object that has been deleted.
     */
    public Task deleteTask(int i) {
        return list.remove(i);
    }

    /**
     * Returns a Task object (specified by index) from the TaskList.
     *
     * @param i Index of the Task object to be returned.
     * @return Task object that is specified by the index.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Returns all Task objects in the TaskList as an ArrayList of Task objects.
     *
     * @return ArrayList of all the Task objects in the TaskList.
     */
    public ArrayList<Task> getAllTask() {
        return list;
    }

    /**
     * Checks whether the specified Task exists in the list of tasks (without comparing status).
     *
     * @param task Task to be checked for existence in the list.
     * @return True if at least one task in the list is considered equal, false otherwise.
     */
    public boolean isTaskExist(Task task) {
        return list.stream().anyMatch(t -> t.isEqualWithoutStatus(task));
    }

    /**
     * Converts a list of String into a TaskList by parsing each String into a Task.
     *
     * @param list The list of String containing the strings to be converted to Task object.
     * @return TaskList containing the Task objects converted from the String.
     */
    private TaskList convertStringListToTaskList(ArrayList<String> list) throws BlitzException {
        ArrayList<Task> taskList = new ArrayList<>();

        for (String str : list) {
            taskList.add(Task.convertStringToTask(str));
        }

        return new TaskList(taskList);
    }

    /**
     * Compares two TaskList objects and determines if they are equal.
     *
     * @param o Object to be compared.
     * @return True if both objects are of same reference or all Tasks in both TaskList are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskList t = (TaskList) o;

        if (this.list.size() != t.list.size()) {
            return false;
        }

        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).equals(t.list.get(i))) {
                return false;
            }
        }

        return true;
    }
}
