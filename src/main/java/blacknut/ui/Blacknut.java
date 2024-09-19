package blacknut.ui;

import java.util.ArrayList;
import blacknut.ui.BlacknutExceptions.InvalidCommandException;
import blacknut.ui.BlacknutExceptions.EmptyDescriptionException;
import blacknut.ui.BlacknutExceptions.InvalidTaskNumberException;
import blacknut.ui.BlacknutExceptions.IncorrectFormatException;

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

    public Ui getUi() {
        return ui;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();

        try {
            String command = parser.parseCommand(input);
            switch (command) {
            case "bye":
                return ui.getGoodbyeMessage();
            case "list":
                return ui.getTasks(tasks.getTasks());
            case "remind":
                return ui.getReminderMessage(tasks.getTasks());
            case "mark":
            case "unmark":
                int markIndex = parser.parseIndex(input);
                assert markIndex >= 0 && markIndex < tasks.size() : "Mark index out of bounds";
                boolean markAsDone = command.equals("mark");
                tasks.markTask(markIndex, markAsDone);
                return ui.getMarkedTask(tasks.getTask(markIndex), markAsDone);
            case "todo":
                String todoDescription = parser.parseDescription(input, "todo");
                assert !todoDescription.isEmpty() : "Todo description should not be empty";
                Task newTodo = new Todo(todoDescription);
                tasks.addTask(newTodo);
                return ui.getAddedTask(newTodo, tasks.size());
            case "deadline":
                String[] deadlineParts = parser.parseDeadline(input);
                Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(newDeadline);
                return ui.getAddedTask(newDeadline, tasks.size());
            case "event":
                String[] eventParts = parser.parseEvent(input);
                Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                tasks.addTask(newEvent);
                return ui.getAddedTask(newEvent, tasks.size());
            case "delete":
                int deleteIndex = parser.parseIndex(input);
                Task deletedTask = tasks.deleteTask(deleteIndex);
                return ui.getDeletedTask(deletedTask, tasks.size());
            case "find":
                String keyword = parser.parseKeyword(input);
                ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                return ui.getMatchingTasks(matchingTasks);
            default:
                throw new InvalidCommandException("I don't know what that means. Please enter a valid command.");
            }
        } catch (InvalidCommandException e) {
            return ui.getError(e.getMessage());
        } catch (EmptyDescriptionException e) {
            return ui.getError(e.getMessage());
        } catch (InvalidTaskNumberException e) {
            return ui.getError(e.getMessage());
        } catch (IncorrectFormatException e) {
            return ui.getError(e.getMessage());
        } finally {
            storage.saveTasksToFile(tasks.getTasks());
        }
    }
}

