package muffin;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The TaskList class manages a list of tasks. It provides methods to add, delete, mark as done,
 * unmark as done, and retrieve tasks, as well as to list all tasks or matching tasks in the list.
 */
public class TaskList {

    /**
     * An instance of FileProcessor to handle reading and writing tasks to a file.
     */
    private FileProcessor fp = new FileProcessor();

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Stack to store command history for undo function.
     */
    private Stack<History> history;

    public TaskList() {
        this.tasks = fp.readFromFile();
        this.history = new Stack<>();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     */
    public Task mark(int index) {
        Task t = tasks.get(index);
        t.isDone = true;
        history.push(new History("mark", t, index));
        fp.writeToFile(tasks);
        return t;
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * @param index The index of the task to unmark as not done.
     * @return The task that was unmarked as not done.
     */
    public Task unmark(int index) {
        Task t = tasks.get(index);
        t.isDone = false;
        history.push(new History("unmark", t, index));
        fp.writeToFile(tasks);
        return t;
    }

    /**
     * Returns a formatted String representation of the elements in the list.
     *
     * @return A String representing the elements of the list, formatted with their positions.
     */
    public String list() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task delete(int index) {
        Task t = tasks.get(index);
        tasks.remove(index);
        history.push(new History("delete", t, index));
        fp.writeToFile(tasks);
        return t;
    }

    /**
     * Adds a new task to the list.
     *
     * @param t The task to add to the list.
     */
    public void add(int index, Task t) {
        tasks.add(index, t);
        history.push(new History("add", t, tasks.size() - 1));
        fp.writeToFile(tasks);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks in the list whose descriptions contain the specified keyword.
     *
     * @param keyword The keyword to search for within each task's description.
     * @return A formatted String of tasks whose descriptions contain the keyword,
     *         or an empty String if no tasks match the search.
     */
    public String find(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.description.contains(keyword)) {
                newList.add(task);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < newList.size(); i++) {
            result.append(i + 1).append(". ").append(newList.get(i)).append("\n");
        }
        return result.toString();
    }

    public String undo() throws MuffinException {
        if (history.isEmpty()) {
            return "There is no action for Muffin to undo!";
        }

        History lastAction = history.pop();

        switch (lastAction.getCommand()) {
        case "add":
            tasks.remove(lastAction.getIndex());
            break;

        case "delete":
            tasks.add(lastAction.getIndex(), lastAction.getTask());
            break;

        case "mark":
            unmark(lastAction.getIndex());
            break;

        case "unmark":
            mark(lastAction.getIndex());
            break;

        default:
            throw new MuffinException("Command does not exist.");
        }

        return String.format("Muffin has helped you undo %s: \n \t %s.", lastAction.getCommand(), lastAction.getTask());
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int length() {
        return tasks.size();
    }
}
