package evan.main;

import evan.exception.NoSuchTaskException;
import evan.task.Task;

import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void markTaskAsDone(int taskNumber) throws NoSuchTaskException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new NoSuchTaskException(taskNumber);
        }
        tasks.get(index).markAsDone();
    }

    public void markTaskAsUndone(int taskNumber) throws NoSuchTaskException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new NoSuchTaskException(taskNumber);
        }
        tasks.get(index).markAsUndone();
    }

    public void deleteTask(int taskNumber) throws NoSuchTaskException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new NoSuchTaskException(taskNumber);
        }
        tasks.remove(index);
    }

    public void display() {
        if (tasks.isEmpty()) {
            System.out.println("Hooray! Your task list is empty!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    public String encodeAsString() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.encodeAsString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Hooray! Your task list is empty!";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return result.toString();
    }
}
