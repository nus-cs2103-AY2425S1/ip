package panorama;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import panorama.task.Task;

/**
 * Manages a list of {@code Task} objects.
 * Provides methods to add, list, mark, unmark, and delete tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    private boolean isTestEnv;

    /**
     * Initializes an empty task list.
     *
     * @param isTestEnv true if in test env
     */
    public TaskList(boolean isTestEnv) {
        tasks = new ArrayList<>();
        storage = new Storage();
        this.isTestEnv = isTestEnv;

        load();
    }

    /**
     * Initializes the task list with a pre-existing list of tasks.
     *
     * @param list The initial list of {@code Task} objects.
     * @param isTestEnv true if in test env
     */
    public TaskList(List<Task> list, boolean isTestEnv) {
        tasks = list;
        storage = new Storage();
        this.isTestEnv = isTestEnv;
    }

    /**
     * Saves the list of tasks to the data file.
     */
    private void save() {
        if (this.isTestEnv) {
            return;
        }

        assert storage != null;
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the data file.
     */
    private void load() {
        if (this.isTestEnv) {
            return;
        }

        assert storage != null;
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a {@code Task} to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        save();
    }

    /**
     * Marks a task as done.
     * The task is identified by its index in the list.
     *
     * @param id The index of the task to be marked as done.
     */
    public void mark(int id) {
        tasks.get(id).setDone(true);
        save();
    }

    /**
     * Marks a task as not done.
     * The task is identified by its index in the list.
     *
     * @param id The index of the task to be marked as not done.
     */
    public void unmark(int id) {
        tasks.get(id).setDone(false);
        save();
    }

    /**
     * Deletes a task from the list.
     * The task is identified by its index in the list.
     *
     * @param id The index of the task to be deleted.
     */
    public Task delete(int id) {
        Task t = tasks.remove(id);
        save();
        return t;
    }

    /**
     * Filters the list for tasks which contain {@code keyword} in the name.
     *
     * @param keyword The search keyword.
     */
    public TaskList find(String keyword) {
        List<Task> result = new ArrayList<>();

        for (Task t: tasks) {
            String name = t.getName();
            String[] words = name.split(" ");

            boolean isFound = false;
            for (String word: words) {
                if (word.equals(keyword)) {
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                result.add(t);
            }
        }


        return new TaskList(result, this.isTestEnv);
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves all tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int numTasks() {
        return tasks.size();
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            // This could either be the result of a `list` command, or
            // a `find x` command where x has no search results.
            return "No tasks found.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            int num = i + 1;
            sb.append(num);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
        }
        return sb.toString();
    }
}
