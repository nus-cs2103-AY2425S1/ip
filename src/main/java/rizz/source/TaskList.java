package rizz.source;
import java.util.ArrayList;
import rizz.task.Task;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Represents a list of tasks.
 * This class provides methods to manage tasks such as adding, deleting, and exporting tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;


    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public String deleteTask(int... indexes) {
        /*Must use List =/ ArrayList as List is stateless Arraylist.remove removes the element at index -> Stream API
        cannot handle side effects! */
        List<Task> deletedTasks = Arrays.stream(indexes)
                .mapToObj(i -> this.tasks.remove(i - 1))
                .toList();
        return deletedTasks.toString();
    }

    public String markTask(int... indexes) {
        List<Task> markedTasks = Arrays.stream(indexes)
                .mapToObj(i -> this.getTask(i - 1))
                .peek(Task::markAsDone)
                .toList();
        return markedTasks.toString();
    }

    public String unmarkTask(int... indexes) {
        List<Task> unmarkedTasks = Arrays.stream(indexes)
                .mapToObj(i -> this.getTask(i - 1))
                .peek(Task::unmarkAsDone)
                .toList();
        return unmarkedTasks.toString();
    }

    public TaskList findByKeyword(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getText().contains(keyword))
                .toList();
        //Need Casting, TaskList <: List
        return new TaskList((ArrayList<Task>) matchingTasks);
    }

    /**
     * Exports all tasks in the task list to an array of strings.
     * Each task is formatted for file storage by calling its export() method.
     *
     * @return An array of strings where each string represents a task in the export format.
     */
    public String[] export() {
        String[] exportTasks = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            exportTasks[i] = tasks.get(i).export();
        }
        return exportTasks;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Empty";
        } else {
            return tasks.stream()
                    .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                    .collect(Collectors.joining("\n"));
        }
    }
    
}
