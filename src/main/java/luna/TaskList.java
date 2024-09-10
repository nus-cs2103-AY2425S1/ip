package luna;

import java.util.ArrayList;

import luna.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to the current list of tasks.
     *
     * @param task Task to be added.
     * @return List of tasks after adding task.
     */
    public String addTask(Task task, Storage storage) {
        tasks.add(task);
        storage.saveTasks(tasks);

        String taskSize = String.format("Now you have %d tasks in the list.", tasks.size());
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + taskSize;
    }

    /**
     * Deletes task from the current list of tasks.
     *
     * @param task Task to be deleted.
     * @return Deleted task.
     * @throws LunaException If task number is invalid.
     */
    public Task deleteTask(int task, Storage storage) throws LunaException {
        if (task >= tasks.size() || task < 0) {
            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
        }

        Task removed = tasks.remove(task);
        assert removed != null;
        storage.saveTasks(tasks);
        return removed;
    }

    /**
     * Prints the current list of tasks.
     *
     * @return List of tasks
     */
    public String list() {
        if (tasks.isEmpty()) {
            return "You do not have any task at the moment.";
        }

        String list = "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format("%d. %s\n", i + 1, tasks.get(i).toString());
            list += task;
        }

        return list;
    }

    /**
     * Marks a task as completed in the current list of tasks.
     *
     * @param task Task to be marked.
     * @return List of tasks after marking task.
     * @throws LunaException If task number is invalid.
     */
    public String markTaskAsDone(int task, Storage storage) throws LunaException {
        if (task >= tasks.size() || task < 0) {
            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
        }

        Task taskToMark = tasks.get(task);
        assert taskToMark != null;
        taskToMark.markAsDone();
        storage.saveTasks(tasks);

        return "Nice! I've marked this task as done:\n"
                + "  " + taskToMark;
    }

    /**
     * Unmark task from the current list of tasks.
     *
     * @param task Task to unmark.
     * @return List of tasks after unmarking task.
     * @throws LunaException If task number is invalid.
     */
    public String unmarkTask(int task, Storage storage) throws LunaException {
        if (task >= tasks.size() || task < 0) {
            throw new LunaException("Invalid task number. Type \"list\" to view tasks.");
        }

        Task taskToUnmark = tasks.get(task);
        assert taskToUnmark != null;
        taskToUnmark.unmark();
        storage.saveTasks(tasks);

        return "OK, I've marked this task as not done yet:\n"
                + "  " + taskToUnmark;
    }

    /**
     * Search for task from the current list of tasks.
     *
     * @param query Description of task to find within tasks.
     * @return List of tasks matching query.
     */
    public String find(String query) {
        assert !query.isEmpty();
        String matched = "";
        String queryUpperCase = query.toUpperCase();

        for (Task task : tasks) {
            String taskUppercase = task.toString().substring(7).toUpperCase();
            if (taskUppercase.contains(queryUpperCase)) {
                matched += String.format("%s\n", task);
            }
        }
        return matched;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
