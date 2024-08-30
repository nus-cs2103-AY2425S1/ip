package sigmabot.command;

import sigmabot.task.Deadline;
import sigmabot.task.Event;
import sigmabot.task.Task;
import sigmabot.task.Todo;
import sigmabot.util.ListMapWriter;
import sigmabot.util.ListReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListOperation extends Command {
    private ListReader reader = new ListReader();
    private ListMapWriter writer = new ListMapWriter();
    private String filePath = "/Users/wxy/Desktop/ip/src/main/data/tasks.txt";
    private Map<String, Task> taskList = new HashMap<>();

    @Override
    public void execute(Scanner sc) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No tasks file found. A new one will be created upon saving tasks.");
        } else if (file.length() == 0) {
            System.out.println("Tasks file is empty. You can create a new task.");
        } else {
            try {
                taskList.putAll(reader.readTasksFromFile(filePath));
                if (taskList.isEmpty()) {
                    System.out.println("No tasks found. The file might be corrupted or empty.");
                } else {
                    System.out.println("Tasks loaded from file: " + filePath);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while reading the tasks file: " + e.getMessage());
            }
        }

        while (true) {
            System.out.println("Enter operation command (create, query, mark, unmark, delete, exit): ");
            if (!sc.hasNextLine()) {
                break;
            }
            String command = sc.nextLine();
            switch (command) {
            case "create":
                createNewTask(sc);
                break;
            case "query":
                queryTasks();
                break;
            case "mark":
                handleMarkDone(sc);
                break;
            case "unmark":
                handleMarkUndone(sc);
                break;
            case "delete":
                handleDeleteTask(sc);
                break;
            case "exit":
                System.out.println("Exiting List Operations...");
                return;
            default:
                System.out.println("Invalid command. Please enter 'create', 'query', 'mark', 'unmark', 'delete', or 'exit'.");
            }
        }
    }

    public void createNewTask(Scanner sc) {
        while (true) {
            System.out.println("Enter task type (todo, deadline, event) or '/exit' to finish: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("/exit")) {
                break;
            }
            Task task = null;
            switch (input.toLowerCase()) {
            case "todo":
                task = Todo.createTodo(sc);
                break;
            case "deadline":
                task = Deadline.createDeadline(sc);
                break;
            case "event":
                task = Event.createEvent(sc);
                break;
            default:
                System.out.println("Invalid task type. Please enter 'todo', 'deadline', or 'event'.");
                continue;
            }
            if (task != null) {
                taskList.put(task.getName(), task);
            }
        }
        System.out.println("New tasks added.");
        writer.writeMapToFile(taskList, filePath);
    }

    public void queryTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available. You can create a new task.");
        } else {
            System.out.println("Current tasks:");
            for (Task task : taskList.values()) {
                System.out.println(task.toString());
            }
        }
    }

    public void markDone(Scanner sc) {
        System.out.println("Enter the name of the task to mark as done: ");
        String taskName = sc.nextLine().trim();
        Task task = taskList.get(taskName);
        if (task != null) {
            task.markDone();
            System.out.println("Task '" + taskName + "' marked as done.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
        writer.writeMapToFile(taskList, filePath);
    }

    public void markUndone(Scanner sc) {
        System.out.println("Enter the name of the task to mark as undone: ");
        String taskName = sc.nextLine().trim();
        Task task = taskList.get(taskName);
        if (task != null) {
            task.markUndone();
            System.out.println("Task '" + taskName + "' marked as undone.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
        writer.writeMapToFile(taskList, filePath);
    }

    public void deleteTask(Scanner sc) {
        System.out.println("Enter the name of the task to delete: ");
        String taskName = sc.nextLine().trim();
        if (taskList.remove(taskName) != null) {
            System.out.println("Task '" + taskName + "' deleted.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
        writer.writeMapToFile(taskList, filePath);
    }

    private void handleMarkDone(Scanner sc) {
        markDone(sc);
    }

    private void handleMarkUndone(Scanner sc) {
        markUndone(sc);
    }

    private void handleDeleteTask(Scanner sc) {
        deleteTask(sc);
    }
}
