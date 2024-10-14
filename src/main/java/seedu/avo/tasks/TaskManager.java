package seedu.avo.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import seedu.avo.storage.Storage;
import seedu.avo.ui.ResponseFormatter;

/**
 * Represents a controller that perform
 * actions on a list of tasks
 */
public class TaskManager {
    private final List<Task> tasks;
    private final Storage<Task, String> storage;
    private final ResponseFormatter formatter;

    /**
     * @param storage A storage object
     */
    public TaskManager(Storage<Task, String> storage, ResponseFormatter formatter) {
        tasks = storage.fetchAll();
        this.storage = storage;
        this.formatter = formatter;
    }

    /**
     * Prints the list of tasks
     */
    public String listTasks() {
        List<Integer> indexes = IntStream.range(0, tasks.size())
                .boxed().toList();
        return formatter.showTaskCount(tasks.size()) + "\n"
                + formatter.showTasksFromList(tasks, indexes);
    }

    /**
     * Marks a task as completed
     * @param index The index of the completed task
     */
    public String completeTask(int index) {
        Task task = tasks.get(index);
        task.complete();
        saveList();
        return formatter.showTaskMarked(tasks, index);
    }
    /**
     * Marks a task as uncompleted
     * @param index The index of the uncompleted task
     */
    public String unCompleteTask(int index) {
        Task task = tasks.get(index);
        task.unComplete();
        saveList();
        return formatter.showTaskUnmarked(tasks, index);
    }

    /**
     * Adds a task to the list of tasks
     * @param task The new task
     */
    public String addTask(Task task) {
        tasks.add(task);
        saveList();
        return formatter.showTaskAdded(tasks);
    }

    /**
     * Deletes a tasks from the list of tasks
     * @param index The index of the deleted task
     */
    public String deleteTask(int index) {
        Task task = tasks.remove(index);
        saveList();
        return formatter.showTaskRemoved(task);
    }
    private void saveList() {
        storage.write(this.formatData());
    }
    public String getTasksByDate(LocalDate date) {
        List<Integer> indexes = filterByDate(date);
        return formatter.showTaskCount(indexes.size()) + "\n"
                + formatter.showTasksFromList(tasks, indexes);
    }

    /**
     * Formats the tasks data for storage
     * @return A string format of the list of tasks
     */
    public String formatData() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.formatData()).append("\n");
        }
        return str.toString();
    }
    public String getTasksByName(String name) {
        List<Integer> indexes = filterByName(name);
        return formatter.showTaskCount(indexes.size()) + "\n"
                + formatter.showTasksFromList(tasks, indexes);
    }
    private List<Integer> filterByName(String name) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchName(name)) {
                result.add(i);
            }
        }
        return result;
    }
    private List<Integer> filterByDate(LocalDate date) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isOccurringOnDate(date)) {
                result.add(i);
            }
        }
        return result;
    }
}
