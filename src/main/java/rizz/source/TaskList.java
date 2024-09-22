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
        assert task != null : "Task cannot be null";
        this.tasks.add(task);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        Task task = this.tasks.get(index);
        assert task != null : "Task cannot be null";
        return task;
    }

    public String deleteTask(int... index) {
        TaskList deletedTasks = new TaskList();
        for (int i = 0; i < index.length; i++) {
            assert index[i] > 0 && index[i] <= this.getLength() : "Index out of bounds";
            //cause 2nd item of list is Arr[1]
            Task tempTask = this.getTask(index[i] - 1);

            this.tasks.remove(index[i] - 1);
            deletedTasks.addTask(tempTask);
        }
        return deletedTasks.toString();
    }

    public String markTask(int... index) {
        TaskList markedTasks = new TaskList();
        for (int i = 0; i < index.length; i++) {
            assert index[i] > 0 && index[i] <= this.getLength() : "Index out of bounds";
            //cause 2nd item of list is Arr[1]
            Task tempTask = this.getTask(index[i] - 1);

            tempTask.markAsDone();
            markedTasks.addTask(tempTask);
        }
        return markedTasks.toString();
    }

    public String unmarkTask(int... index) {
        TaskList unmarkTasks = new TaskList();
        for (int i = 0; i < index.length; i++) {
            assert index[i] > 0 && index[i] <= this.getLength() : "Index out of bounds";
            //cause 2nd item of list is Arr[1]
            Task tempTask = this.getTask(index[i] - 1);

            tempTask.unmarkAsDone();
            unmarkTasks.addTask(tempTask);
        }
        return unmarkTasks.toString();
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
        if (this.getLength() == 0) {
            return "Empty";
        } else {
            StringBuilder tempStr = new StringBuilder();
            for (int i = 0; i < this.getLength(); i++) {
                tempStr.append(i + 1).append(". ").append(this.getTask(i)).append("\n");
            }
            return tempStr.toString();
        }
    }
    
}
