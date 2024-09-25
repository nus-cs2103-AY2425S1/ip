package echo;

import java.io.FileNotFoundException;
import java.io.IOException;

import echo.task.Deadline;
import echo.task.Events;
import echo.task.Task;
import echo.task.Todo;


/**
 * The Echo class is the main entry point for the Echo application.
 * It handles the loading, saving, and processing of user commands related to task management.
 */
public class Echo {

    public static final String DOCS_TASKS_TXT = "docs/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean awaitingEditInput = false; // To track if we are waiting for a follow-up response
    private Task taskToEdit; // To store the task that the user wants to edit

    /**
     * Constructs an Echo object and initializes the necessary components.
     *
     * @param filePath The file path where tasks are stored and loaded.
     */
    public Echo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (EchoException e) { //empty file or file does not exist

            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Runs the main loop of the Echo application, processing user commands and interacting with the user interface.
     */
    public String run(String input) {

        if (awaitingEditInput) {
            // If we are awaiting an edit response, handle the follow-up here
            awaitingEditInput = false; // Reset this after handling
            return taskToEdit.editTask(input);
        }

        String[] parts = Parser.parseCommand(input);
        String command = parts[0];


        try {
            switch (command) {
            case "bye":
                storage.save(tasks.getTasks());
                return ui.showGoodbyeMessage();
            case "list":
                return ui.showTaskList(tasks.getTasks());
            case "mark":
                tasks.markTask(Integer.parseInt(parts[1]));
                return ui.showMarkedTask(tasks.getTask(Integer.parseInt(parts[1])));
            case "unmark":
                tasks.unmarkTask(Integer.parseInt(parts[1]));
                return ui.showUnmarkedTask(tasks.getTask(Integer.parseInt(parts[1])));
            case "todo":
                Todo todo = tasks.addTodo(parts[1]);
                return ui.showTaskAdded(todo, tasks.getTasks().size());
            case "deadline":
                Deadline deadline = tasks.addDeadline(parts[1]);
                return ui.showTaskAdded(deadline, tasks.getTasks().size());
            case "event":
                Events event = tasks.addEvent(parts[1]);
                return ui.showTaskAdded(event, tasks.getTasks().size());
            case "delete":
                Task removedTask = tasks.deleteTask(Integer.parseInt(parts[1]));
                return ui.showTaskRemoved(removedTask, tasks.getTasks().size());
            case "find":
                String toFind = parts[1];
                return tasks.find(toFind);
            case "edit":
                // Ask the user for further input (follow-up question)
                awaitingEditInput = true; // Set to true to track that we're waiting for input
                int taskToEditNo = Integer.parseInt(parts[1]); // Store the task to edit
                taskToEdit = tasks.getTask(taskToEditNo);
                return "What would you like to edit in task " + taskToEdit + "?";
            default:
                return ui.showErrorMessage("I'm sorry, but I don't know what that means :-(");
            }
        } catch (EchoException | IOException e) {
            return ui.showErrorMessage(e.getMessage());
        }


    }


}
