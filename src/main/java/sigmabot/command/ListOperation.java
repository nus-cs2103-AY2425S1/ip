package sigmabot.command;

import sigmabot.task.Deadline;
import sigmabot.task.Event;
import sigmabot.task.Task;
import sigmabot.task.Todo;
import sigmabot.ui.UiComponent;
import sigmabot.util.ListMapWriter;
import sigmabot.util.ListReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ListOperation extends Command {
    ListReader reader = new ListReader();
    ListMapWriter writer = new ListMapWriter();
    String directory = "../../data/";
    Map<String, Map<String, Task>> taskListsCatalog = new HashMap<>();

    @Override
    public void execute(UiComponent ui) {
        // Read files from the directory to populate the taskListsCatalog
        File directoryFile = new File(directory);
        if (!directoryFile.exists() || directoryFile.listFiles() == null || directoryFile.listFiles().length == 0) {
            ui.printDialogue("No lists found. You can create a new list.");
        } else {
            taskListsCatalog.putAll(reader.readDirectory(directory, ui));
            ui.printDialogue("Lists loaded from directory: " + directory);
        }

        // Main command loop
        while (true) {
            ui.printDialogue("Enter operation command (create, query, mark, delete, exit): ");
            String command = ui.readInput().trim().toLowerCase();

            switch (command) {
                case "create":
                    createNewList(taskListsCatalog, ui); // Pass the actual taskListsCatalog
                    break;
                case "query":
                    queryLists(taskListsCatalog, ui); // Pass the actual taskListsCatalog
                    break;
                case "mark":
                    handleMarkDone(ui);
                    break;
                case "delete":
                    handleDeleteTask(ui);
                    break;
                case "exit":
                    ui.printDialogue("Exiting List Operations...");
                    return; // Exit the loop and end the method
                default:
                    ui.printDialogue("Invalid command. Please enter 'create', 'query', 'mark', 'delete', or 'exit'.");
            }
        }
    }

    public void createNewList(Map<String, Map<String, Task>> taskListCatalog, UiComponent ui) {
        ui.printDialogue("Enter tag for the new list: ");
        String tag = ui.readInput().trim();
        Map<String, Task> newTaskList = new HashMap<>();
        String input;
        while (true) {
            ui.printDialogue("Enter task type (todo, deadline, event) or '/exit' to finish: ");
            input = ui.readInput().trim();
            if (input.equalsIgnoreCase("/exit")) {
                break; // Exit the loop if the user types '/exit'
            }
            Task task = null;
            switch (input.toLowerCase()) {
                case "todo" -> task = Todo.createTodo(ui);
                case "deadline" -> task = Deadline.createDeadline(ui);
                case "event" -> task = Event.createEvent(ui);
                default -> {
                    ui.printDialogue("Invalid task type. Please enter 'todo', 'deadline', or 'event'.");
                    continue;
                }
            }
            if (task != null) {
                newTaskList.put(task.getName(), task);
            }
        }
        taskListCatalog.put(tag, newTaskList);
        ui.printDialogue("New task list created with tag: " + tag);
        // Write the new task list to the file
        writer.writeMapToFile(taskListCatalog, directory, ui);
    }

    public void queryLists(Map<String, Map<String, Task>> taskListCatalog, UiComponent ui) {
        if (taskListCatalog.isEmpty()) {
            ui.printDialogue("No lists available. You can create a new list.");
        } else {
            for (String tag : taskListCatalog.keySet()) {
                ui.printDialogue("List: " + tag);
                for (Task task : taskListCatalog.get(tag).values()) {
                    ui.printDialogue(task.toString());
                }
            }
        }
    }

    public void markDone(Map<String, Task> taskList, UiComponent ui) {
        ui.printDialogue("Enter the name of the task to mark as done: ");
        String taskName = ui.readInput().trim();

        Task task = taskList.get(taskName);
        if (task != null) {
            task.markDone();
            ui.printDialogue("Task '" + taskName + "' marked as done.");
        } else {
            ui.printDialogue("Task '" + taskName + "' not found.");
        }

        // Rewrite the modified list to the file
        writer.writeMapToFile(taskListsCatalog, directory, ui);
    }

    public void deleteTask(Map<String, Task> taskList, UiComponent ui) {
        ui.printDialogue("Enter the name of the task to delete: ");
        String taskName = ui.readInput().trim();

        if (taskList.remove(taskName) != null) {
            ui.printDialogue("Task '" + taskName + "' deleted.");
        } else {
            ui.printDialogue("Task '" + taskName + "' not found.");
        }

        // Rewrite the modified list to the file
        writer.writeMapToFile(taskListsCatalog, directory, ui);
    }

    private void handleMarkDone(UiComponent ui) {
        ui.printDialogue("Enter the tag of the list you want to mark a task as done in: ");
        String tag = ui.readInput().trim();

        Map<String, Task> taskList = taskListsCatalog.get(tag);

        if (taskList != null) {
            markDone(taskList, ui);
            // After modifying the task list, write the updated list to the file
            writer.writeMapToFile(taskListsCatalog, directory, ui);
        } else {
            ui.printDialogue("Task list with tag '" + tag + "' not found.");
        }
    }

    private void handleDeleteTask(UiComponent ui) {
        ui.printDialogue("Enter the tag of the list you want to delete a task from: ");
        String tag = ui.readInput().trim();

        Map<String, Task> taskList = taskListsCatalog.get(tag);

        if (taskList != null) {
            deleteTask(taskList, ui);
            // After modifying the task list, write the updated list to the file
            writer.writeMapToFile(taskListsCatalog, directory, ui);
        } else {
            ui.printDialogue("Task list with tag '" + tag + "' not found.");
        }
    }
}
