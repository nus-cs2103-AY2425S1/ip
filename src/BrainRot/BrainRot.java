package BrainRot;

import BrainRot.exceptions.*;
import java.io.IOException;

/**
 * The BrainRot.BrainRot class is the main controller for the task management system.
 * It coordinates between the UI, task storage, and task operations.
 * This class handles user commands, manages the task list, and ensures data is saved to storage.
 */
public class BrainRot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a BrainRot.BrainRot object that initializes the user interface, storage, and task list.
     *
     * @param filePath The file path where tasks are stored.
     */
    public BrainRot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (UnknownLoadingError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the main loop to process user commands.
     * This method continually reads user input and performs the appropriate actions.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String userInput = ui.getUserCommand();
            String[] parsedInput = Parser.parse(userInput);
            String action = parsedInput[0];
            String details = parsedInput[1];

            try {
                switch (action) {
                    case "list":
                        ui.showTaskList(tasks);
                        break;
                    case "bye":
                        ui.showExit();
                        return;
                    case "mark":
                        markTask(details);
                        break;
                    case "unmark":
                        unmarkTask(details);
                        break;
                    case "delete":
                        deleteTask(details);
                        break;
                    case "add":
                        addTask(details);
                        break;
                    default:
                        throw new UnknownCommandException("Unknown command");
                }

            } catch (UnknownCommandException | UnknownActivityException | IOException e) {
                ui.showCommandError();
            }
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param details The index of the task to be marked.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private void markTask(String details) throws IOException {
        int markIndex = Integer.parseInt(details) - 1;
        tasks.getTask(markIndex).mark();
        storage.save(tasks.getTasks());
        ui.showMarkMsg(tasks.getTask(markIndex).toString());
    }

    /**
     * Unmarks a task, setting it as not completed.
     *
     * @param details The index of the task to be unmarked.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private void unmarkTask(String details) throws IOException {
        int unmarkIndex = Integer.parseInt(details) - 1;
        tasks.getTask(unmarkIndex).unmark();
        storage.save(tasks.getTasks());
        ui.showUnMarkMsg(tasks.getTask(unmarkIndex).toString());
    }

    /**
     * Deletes a task from the task list.
     *
     * @param details The index of the task to be deleted.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private void deleteTask(String details) throws IOException {
        int deleteIndex = Integer.parseInt(details) - 1;
        String taskDetails = tasks.getTask(deleteIndex).toString();
        tasks.removeTask(deleteIndex);
        storage.save(tasks.getTasks());
        ui.showDeleteMsg(taskDetails);
    }

    /**
     * Adds a new task to the task list based on the details provided.
     *
     * @param details The details of the task to be added.
     * @throws UnknownCommandException If the command to add the task is unknown.
     * @throws UnknownActivityException If the task description is invalid.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private void addTask(String details) throws UnknownCommandException, UnknownActivityException, IOException {
        Task newTask;
        if (details.startsWith("todo")) {
            newTask = new ToDo(details.substring(5).trim());
        } else if (details.startsWith("deadline")) {
            String[] parts = details.split("/by");
            newTask = new Deadline(parts[0].substring(9).trim(), parts[1].trim());
        } else if (details.startsWith("event")) {
            String[] parts = details.split("/to");
            newTask = new Event(parts[0].substring(6).trim(), parts[1].trim(), parts[2].trim());
        } else {
            throw new UnknownCommandException("Unknown command");
        }
        tasks.addTask(newTask);
        storage.save(tasks.getTasks());
        ui.showAddTaskMsg(newTask.toString());
    }
}
