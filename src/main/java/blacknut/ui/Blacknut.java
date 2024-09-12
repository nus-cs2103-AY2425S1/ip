package blacknut.ui;

import java.util.ArrayList;
import java.util.Scanner;
import blacknut.ui.BlacknutExceptions.InvalidCommandException;
import blacknut.ui.BlacknutExceptions.EmptyDescriptionException;
import blacknut.ui.BlacknutExceptions.InvalidTaskNumberException;
import blacknut.ui.BlacknutExceptions.IncorrectFormatException;
import blacknut.ui.BlacknutExceptions.BlacknutException;

import java.io.File;

/**
 * Represents the main Blacknut application that handles user input, processes commands,
 * and manages tasks.
 */
public class Blacknut {
    private static final String FILE_PATH = "data" + File.separator + "blacknut.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Blacknut application.
     */
    public Blacknut() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadTasksFromFile());
        assert tasks != null : "TaskList should not be null after loading from file";
        parser = new Parser();
    }

    /**
     * Runs the Blacknut application, handling user input and executing commands.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readInput();
            ui.showLine();

            try {
                String command = parser.parseCommand(input);
                switch (command) {
                    case "bye":
                        ui.showGoodbye();
                        return;
                    case "list":
                        ui.showTasks(tasks.getTasks());
                        break;
                    case "mark":
                    case "unmark":
                        int markIndex = parser.parseIndex(input);
                        assert markIndex >= 0 && markIndex < tasks.size() : "Mark index out of bounds";
                        boolean markAsDone = command.equals("mark");
                        tasks.markTask(markIndex, markAsDone);
                        ui.showMarkedTask(tasks.getTask(markIndex), markAsDone);
                        break;
                    case "todo":
                        String todoDescription = parser.parseDescription(input, "todo");
                        assert !todoDescription.isEmpty() : "Todo description should not be empty";
                        Task newTodo = new Todo(todoDescription);
                        tasks.addTask(newTodo);
                        ui.showAddedTask(newTodo, tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineParts = parser.parseDeadline(input);
                        Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.addTask(newDeadline);
                        ui.showAddedTask(newDeadline, tasks.size());
                        break;
                    case "event":
                        String[] eventParts = parser.parseEvent(input);
                        Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                        tasks.addTask(newEvent);
                        ui.showAddedTask(newEvent, tasks.size());
                        break;
                    case "delete":
                        int deleteIndex = parser.parseIndex(input);
                        Task deletedTask = tasks.deleteTask(deleteIndex);
                        ui.showDeletedTask(deletedTask, tasks.size());
                        break;
                    case "find":
                        String keyword = parser.parseKeyword(input);
                        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                        ui.showMatchingTasks(matchingTasks);
                        break;
                    default:
                        throw new InvalidCommandException("I don't know what that means. Please enter a valid command.");
                }
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            } catch (EmptyDescriptionException e) {
                ui.showError(e.getMessage());
            } catch (InvalidTaskNumberException e) {
                ui.showError(e.getMessage());
            } catch (IncorrectFormatException e) {
                ui.showError(e.getMessage());
            }

            ui.showLine();
            storage.saveTasksToFile(tasks.getTasks());
        }
    }

    /**
     * The main method to run the Blacknut application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        assert new File(FILE_PATH).exists() || new File(FILE_PATH).getParentFile().exists() : "File path must be valid";
        new Blacknut().run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Blacknut heard: " + input;
    }
}

