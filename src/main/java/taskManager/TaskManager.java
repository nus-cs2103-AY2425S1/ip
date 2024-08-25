package taskManager;
import task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import orionExceptions.FileInitializationException;


public class TaskManager {
    private static final String DATA_FILE_PATH = "./data/tasks.csv";


    public TaskManager() throws FileInitializationException {
        File dataFile = new File(DATA_FILE_PATH);
        try {
            if (!dataFile.exists()) {
                // Create the parent directories if they don't exist
                if (dataFile.getParentFile() != null && !dataFile.getParentFile().exists()) {
                    boolean dirsCreated = dataFile.getParentFile().mkdirs();
                    if (!dirsCreated) {
                        throw new FileInitializationException("Failed to create directories: " + dataFile.getParentFile());
                    }
                }

                // Create the file if it doesn't exist
                boolean fileCreated = dataFile.createNewFile();
                if (!fileCreated) {
                    throw new FileInitializationException("Failed to create file: " + dataFile.getPath());
                }
            }
        } catch (IOException e) {
            throw new FileInitializationException("IOException occurred: " + e.getMessage());
        }


    }
    private int getNextTaskId(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(Task::getTaskID)
                .max()
                .orElse(0) + 1;
    }


    public List<Task> loadTasksFromFile() {
        // I took AI help for this function as I didn't know Java file reading syntax
        List<Task> loadedTasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int taskId = Integer.parseInt(parts[0]);
                String type = parts[1];
                String description = parts[2];
                boolean completed = Boolean.parseBoolean(parts[3]);

                Task task = null;
                switch (type) {
                    case "TODO":
                        task = new Todo(taskId, description);
                        break;
                    case "DEADLINE":
                        String by = parts[4];
                        task = new Deadline(taskId, description, by);
                        break;
                    case "EVENT":
                        String[] eventTimes = parts[4].split("\\|");
                        String from = eventTimes[0];
                        String to = eventTimes[1];
                        task = new Event(taskId, description, from, to);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown task type: " + type);
                }

                task.setCompleted(completed);
                loadedTasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing task data: " + e.getMessage());
        }

        return loadedTasks;
    }

    public void printTasks() {
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
                    taskLine.append(",").append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    taskLine.append(",").append(((Event) task).getFrom()).append("|").append(((Event) task).getTo());
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



    public Task addTodo(String description) {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Todo(newTaskId, description);
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    public Task addDeadline(String description, String by) {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Deadline(newTaskId, description, by);
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    public Task addEvent(String description, String from, String to) {
        List<Task> tasks = loadTasksFromFile();
        int newTaskId = getNextTaskId(tasks);
        Task task = new Event(newTaskId, description, from, to);
        tasks.add(task);
        saveTasksToFile(tasks);
        return task;
    }

    public int getSize() {
        List<Task> tasks = loadTasksFromFile();
        return tasks.size();
    }

    public Task markAsDone(int listPosition) {
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

    public Task unmarkAsDone(int listPosition) {
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

    public Task deleteTask(int listPosition) {
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
