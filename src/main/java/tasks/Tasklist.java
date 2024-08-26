package tasks;
import java.util.ArrayList;
import storage.Storage;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    public TaskList() {
        this.storage = new Storage("./data/TaskList.txt", "./data/TaskList.dat");
        this.taskList = storage.loadTasksFromFile();
    }

    public void addTask(Task task) {
        taskList.add(task);
        storage.saveTasksToFile(taskList);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
        storage.saveTasksToFile(taskList);
    }

    public void markAsUndone(int index) {
        taskList.get(index).unmarkAsDone();
        storage.saveTasksToFile(taskList);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
        storage.saveTasksToFile(taskList);
    }

    public int listLength() {
        return taskList.size();
    }

    @Override
    public String toString() {
        // suggested by Copilot
        if (taskList.isEmpty()) {
            return "You have no tasks in the list.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    
}