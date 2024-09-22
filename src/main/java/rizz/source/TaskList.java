package rizz.source;
import java.util.ArrayList;
import rizz.task.Task;


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

    public String deleteTask(int index) {
        String str = this.getTask(index - 1).toString();
        this.tasks.remove(index - 1);
        return str;
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
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

    public TaskList findByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getText().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    @Override
    public String toString() {
        if (this.getLength() == 0) {
            return "Empty";
        } else {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < this.getLength(); i++) {
                str.append(i + 1).append(". ").append(this.getTask(i)).append("\n");
            }
            return str.toString();
        }
    }
    
}
