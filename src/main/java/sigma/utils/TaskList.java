package sigma.utils;

import java.util.ArrayList;
import java.util.Comparator;

import sigma.exception.SigmaException;
import sigma.task.Task;
/**
 * Represents a list of tasks.
 * Contains operations to add, remove, and get tasks.
 * Also contains operations to check if the list is empty and to get the size of the list.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param i The index of the task to get.
     *
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param i The index of the task to remove.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     *
     * @return An array list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        tasks.stream()
                .filter((task) -> (task.getDescription().contains(keyword)))
                .forEachOrdered((task) -> {
                    matchingTasks.add(task);
                });
        return matchingTasks;
    }

    /**
     * Builds a list of tasks.
     *
     * @return A string builder containing the list of tasks.
     */
    public StringBuilder buildList() {
        StringBuilder s = new StringBuilder();
        tasks.stream()
                .map((task) -> tasks.indexOf(task) + 1)
                .map((i) -> i + ". " + tasks.get(i - 1).toString() + "\n")
                .forEachOrdered((taskString) -> {
                    s.append(taskString);
                });
        return s;
    }

    /**
     * Sorts the list of tasks based on the specified parameter.
     *
     * @param parameter The parameter to sort the tasks by.
     *
     * @return A new TaskList object containing the sorted tasks.
     */
    public ArrayList<Task> filterTasksByParameter(String parameter) throws SigmaException {
        ArrayList<Task> copyOfTasks = new ArrayList<>(tasks);
        ArrayList<Task> filteredTasks = new ArrayList<>();
        if (parameter.equalsIgnoreCase("description")
                || parameter.equalsIgnoreCase("desc")
                || parameter.equalsIgnoreCase("date")) {
            return sortTasksByParameter(parameter);
        }
        switch (parameter) {
        case "todo":
        case "t":
            copyOfTasks.stream()
                    .filter((task) -> task.getTaskType().equals("T"))
                    .forEachOrdered((task) -> filteredTasks.add(task));
            break;
        case "deadline":
        case "d":
            copyOfTasks.stream()
                    .filter((task) -> task.getTaskType().equals("D"))
                    .forEachOrdered((task) -> filteredTasks.add(task));
            break;
        case "event":
        case "e":
            copyOfTasks.stream()
                    .filter((task) -> task.getTaskType().equals("E"))
                    .forEachOrdered((task) -> filteredTasks.add(task));
            break;
        default:
            throw new SigmaException("What the sigma? I can't sort by that parameter!");
        }
        return filteredTasks;
    }

    private ArrayList<Task> sortTasksByParameter(String parameter) {
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        switch (parameter) {
        case "description":
        case "desc":
            sortedTasks.sort(Comparator.comparing(Task::getDescription));
            break;
        case "date":
            sortedTasks.sort(Comparator.comparing(Task::getStartDate, Comparator.nullsLast(Comparator.naturalOrder())));
            break;
        default:
            break;
        }
        return sortedTasks;
    }

}
