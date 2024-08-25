package orion.taskList;

import orion.task.*;
import orion.task.*;

import java.io.*;
import java.util.List;
import orion.orionExceptions.FileInitializationException;
import orion.storage.Storage;

public class TaskList {
    private static final String DATA_FILE_PATH = "./data/tasks.csv";
    private Storage storage;

    public TaskList(Storage storage) throws FileInitializationException {
        this.storage = storage;
        loadTasksFromFile();
    }

    private int getNextTaskId(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(Task::getTaskID)
                .max()
                .orElse(0) + 1;
    }

    public boolean isValidIndex(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        if (tasks.isEmpty()) {
            return false;
        }
        return listPosition > 0 && listPosition <= tasks.size();
    }

    public List<Task> loadTasksFromFile() throws FileInitializationException {
        return storage.read();
    }

    public void printTasks() throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    public void saveTasksToFile(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for (Task task : tasks) {
                StringBuilder taskLine = new StringBuilder();
                taskLine.append(task.getTaskID()).append(",");
                taskLine.append(getTaskType(task)).append(",");
                taskLine.append(task.getDescription()).append(",");
                taskLine.append(task.isCompleted());

                if (task instanceof Deadline) {
                    taskLine.append(",").append(((Deadline) task).getBy()); // ISO-8601 format
                } else if (task instanceof Event) {
                    taskLine.append(",").append(((Event) task).getFrom())
                            .append("|").append(((Event) task).getTo()); // ISO-8601 format
                }

                bw.write(taskLine.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private String getTaskType(Task task) {
        if (task instanceof Todo) {
            return "TODO";
        } else if (task instanceof Deadline) {
            return "DEADLINE";
        } else if (task instanceof Event) {
            return "EVENT";
        } else {
            throw new IllegalArgumentException("Unknown task type: " + task.getClass().getName());
        }
    }

    public Task addTodo(String description) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Todo(newTaskId, description);
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    public Task addDeadline(DeadlineDetails temp) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Deadline(newTaskId, temp.getDescription(), temp.getBy());
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    public Task addEvent(EventDetails temp) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Event(newTaskId, temp.getDescription(), temp.getFrom(), temp.getTo());
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    public int getSize() throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        return tasks.size();
    }

    public Task markAsDone(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
            saveTasksToFile(tasks);
            return task;
        }
        return null;
    }

    public Task unmarkAsDone(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(false);
            saveTasksToFile(tasks);
            return task;
        }
        return null;
    }

    public Task deleteTask(int listPosition) throws FileInitializationException {
        List<Task> tasks = loadTasksFromFile();
        int index = listPosition - 1;
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            saveTasksToFile(tasks);
            return removedTask;
        }
        return null;
    }
}
