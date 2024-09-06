package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sigmabot.task.Deadline;
import sigmabot.task.Event;
import sigmabot.task.Task;
import sigmabot.task.Todo;
import sigmabot.util.ListMapWriter;
import sigmabot.util.ListReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ListOperation} class provides functionality for managing a task list.
 * It includes methods for creating, querying, finding, marking, unmarking, and deleting tasks.
 * The tasks are read from and written to a file specified by the {@code filePath}.
 */
public class ListOperation extends ChatCommand {
    private ListReader reader = new ListReader();
    private ListMapWriter writer = new ListMapWriter();
    private String filePath = System.getProperty("user.home") + "/tasks.txt";
    private Map<String, Task> taskList = new HashMap<>();

    /**
     * Executes the main command loop, providing options to create, query, mark, unmark, or delete tasks.
     * Also loads existing tasks from the file at startup.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    @Override
    public void execute(TextArea displayArea, TextField inputField) {
        File file = new File(filePath);

        // Check if the file exists. If not, attempt to copy it from the JAR.
        if (!file.exists()) {
            displayArea.appendText("No tasks file found. Creating a new one...\n");
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("tasks.txt")) {
                if (inputStream != null) {
                    // Copy the file from the JAR to the user's home directory
                    try (FileOutputStream outputStream = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    displayArea.appendText("Default tasks file created at: " + filePath + "\n");
                } else {
                    displayArea.appendText("No default tasks file found in JAR. A new one will be created upon saving tasks.\n");
                }
            } catch (Exception e) {
                displayArea.appendText("An error occurred while creating the tasks file: " + e.getMessage() + "\n");
            }
        } else if (file.length() == 0) {
            displayArea.appendText("Tasks file is empty. You can create a new task.\n");
        } else {
            try {
                taskList.putAll(reader.readTasksFromFile(filePath)); // Using ListReader to load tasks
                if (taskList.isEmpty()) {
                    displayArea.appendText("No tasks found. The file might be corrupted or empty.\n");
                } else {
                    displayArea.appendText("Tasks loaded from file: " + filePath + "\n");
                }
            } catch (Exception e) {
                displayArea.appendText("An error occurred while reading the tasks file: " + e.getMessage() + "\n");
            }
        }

        // Setup input field action handler
        inputField.setOnAction(event -> {
            String command = inputField.getText().trim();
            inputField.clear();
            switch (command.toLowerCase()) {
            case "create":
                createNewTask(displayArea, inputField);
                break;
            case "query":
                queryTasks(displayArea);
                break;
            case "find":
                findTasks(displayArea, inputField);
                break;
            case "add":
                addTask(displayArea, inputField);
                break;
            case "mark":
                handleMarkDone(displayArea, inputField);
                break;
            case "unmark":
                handleMarkUndone(displayArea, inputField);
                break;
            case "delete":
                handleDeleteTask(displayArea, inputField);
                break;
            case "exit":
                displayArea.appendText("Exiting List Operations...\n");
                break;
            default:
                displayArea.appendText("Invalid command. Please enter 'create', 'query', 'find', 'add', 'mark', 'unmark', 'delete', or 'exit'.\n");
            }
        });
    }

    /**
     * Creates a new task based on user input and adds it to the task list.
     * The task list is then saved to the file.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    public void createNewTask(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Enter task type (todo, deadline, event) or '/exit' to finish:\n");
        inputField.setOnAction(event -> {
            String input = inputField.getText().trim();
            inputField.clear();

            if (input.equalsIgnoreCase("/exit")) {
                displayArea.appendText("Finished creating tasks.\n");
                return;
            }

            switch (input.toLowerCase()) {
            case "todo":
                Todo.createTodoGUI(displayArea, inputField, taskList);
                break;
            case "deadline":
                Deadline.createDeadlineGUI(displayArea, inputField, taskList);
                break;
            case "event":
                Event.createEventGUI(displayArea, inputField, taskList);
                break;
            default:
                displayArea.appendText("Invalid task type. Please enter 'todo', 'deadline', or 'event'.\n");
            }

            writer.writeMapToFile(taskList, filePath); // Save tasks to file after creation
            return;
        });
    }

    /**
     * Prints the entries of the task list for debugging purposes.
     */
    private void printTaskListDebug(TextArea displayArea) {
        displayArea.appendText("Debug: Current Task List Contents:\n");
        for (Map.Entry<String, Task> entry : taskList.entrySet()) {
            displayArea.appendText("Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n");
        }
    }

    /**
     * Displays all tasks in the current task list.
     * If the task list is empty, prompts the user to create a new task.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     */
    public void queryTasks(TextArea displayArea) {
        if (taskList.isEmpty()) {
            displayArea.appendText("No tasks available. You can create a new task.\n");
        } else {
            displayArea.appendText("Current tasks:\n");
            for (Task task : taskList.values()) {
                displayArea.appendText(task.toString() + "\n");
            }
        }
    }

    /**
     * Finds and displays tasks that contain a specific substring in their names.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    public void findTasks(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Enter the substring to search for in task names:\n");
        inputField.setOnAction(event -> {
            String substring = inputField.getText().trim().toLowerCase();
            inputField.clear();
            boolean found = false;
            for (Task task : taskList.values()) {
                if (task.getName().toLowerCase().contains(substring)) {
                    displayArea.appendText(task.toString() + "\n");
                    found = true;
                }
            }
            if (!found) {
                displayArea.appendText("No tasks found containing the substring: " + substring + "\n");
            }
        });
    }

    /**
     * Adds a new task to the existing task list based on user input.
     * The task list is then saved to the file.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    public void addTask(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Enter task type to add (todo, deadline, event) or '/exit' to finish:\n");
        inputField.setOnAction(event -> {
            String input = inputField.getText().trim();
            inputField.clear();
            if (input.equalsIgnoreCase("/exit")) {
                displayArea.appendText("Finished adding tasks.\n");
                return;
            }
            switch (input.toLowerCase()) {
            case "todo":
                Todo.createTodoGUI(displayArea, inputField, taskList);
                break;
            case "deadline":
                Deadline.createDeadlineGUI(displayArea, inputField, taskList);
                break;
            case "event":
                Event.createEventGUI(displayArea, inputField, taskList);
                break;
            case "/exit":
                return;
            default:
                displayArea.appendText("Invalid task type. Please enter 'todo', 'deadline', or 'event'.\n");
            }
            writer.writeMapToFile(taskList, filePath); // Save tasks to file after addition
            displayArea.appendText("Enter task type to add (todo, deadline, event) or '/exit' to finish:\n");
        });
    }

    /**
     * Marks a specified task as done based on user input.
     * The task list is then saved to the file.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    public void markDone(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Enter the name of the task to mark as done:\n");
        inputField.setOnAction(event -> {
            String taskName = inputField.getText().trim();
            inputField.clear();
            Task task = taskList.get(taskName);
            if (task != null) {
                task.markDone();
                displayArea.appendText("Task '" + taskName + "' marked as done.\n");
                writer.writeMapToFile(taskList, filePath); // Save tasks to file after modification
            } else {
                displayArea.appendText("Task '" + taskName + "' not found.\n");
            }
        });
    }

    /**
     * Marks a specified task as undone based on user input.
     * The task list is then saved to the file.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    public void markUndone(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Enter the name of the task to mark as undone:\n");
        inputField.setOnAction(event -> {
            String taskName = inputField.getText().trim();
            inputField.clear();
            Task task = taskList.get(taskName);
            if (task != null) {
                task.markUndone();
                displayArea.appendText("Task '" + taskName + "' marked as undone.\n");
                writer.writeMapToFile(taskList, filePath); // Save tasks to file after modification
            } else {
                displayArea.appendText("Task '" + taskName + "' not found.\n");
            }
        });
    }

    /**
     * Deletes a specified task based on user input.
     * The task list is then saved to the file.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    public void deleteTask(TextArea displayArea, TextField inputField) {
        displayArea.appendText("Enter the name of the task to delete:\n");
        inputField.setOnAction(event -> {
            String taskName = inputField.getText().trim();
            inputField.clear();
            if (taskList.remove(taskName) != null) {
                displayArea.appendText("Task '" + taskName + "' deleted.\n");
                writer.writeMapToFile(taskList, filePath); // Save tasks to file after deletion
            } else {
                displayArea.appendText("Task '" + taskName + "' not found.\n");
            }
        });
    }

    /**
     * Handles the process of marking a task as done.
     * Prompts the user to select the task to be marked as done.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    private void handleMarkDone(TextArea displayArea, TextField inputField) {
        markDone(displayArea, inputField);
    }

    /**
     * Handles the process of marking a task as undone.
     * Prompts the user to select the task to be marked as undone.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    private void handleMarkUndone(TextArea displayArea, TextField inputField) {
        markUndone(displayArea, inputField);
    }

    /**
     * Handles the process of deleting a task.
     * Prompts the user to select the task to be deleted.
     *
     * @param displayArea The {@code TextArea} object for displaying output.
     * @param inputField  The {@code TextField} object for reading user input.
     */
    private void handleDeleteTask(TextArea displayArea, TextField inputField) {
        deleteTask(displayArea, inputField);
    }
}
