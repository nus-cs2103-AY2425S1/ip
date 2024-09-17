package ScoobyDoo;
import exception.InputFormatException;
import storage.Storage;
import task.Event;
import task.Task;
import task.Todo;
import task.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The ScoobyDoo class represents the main application for managing tasks.
 * It handles user input, task management, and interaction with storage and UI components.
 */
public class ScoobyDoo {
    /** The name of the application. */
    private final String name = "Scooby-Doo";
    /** The list of tasks managed by the application. */
    private TaskList taskList;
    /** The storage component for saving and loading tasks. */
    private final Storage storage;
    /** The UI component for handling user interface operations. */
    private final UI ui;

    /**
     * Constructs a new ScoobyDoo application with the specified file path for storage.
     *
     * @param FilePath The file path where tasks will be saved and loaded from.
     */
    public ScoobyDoo(String FilePath) {
        storage = new Storage(FilePath);
        ui = new UI();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printErrorMessage("cannot parse data from file");
            taskList = new TaskList();
        } finally {
            assert taskList != null : "taskList should not be null";
        }
    }

    /**
     * Runs the main application loop.
     * This method handles user input, processes commands, and manages tasks until the user exits.
     * It also loads existing tasks from storage at startup and saves tasks before exiting.
     */
    public String getResponse (String input) {
        // Method implementation
        // 1. Print welcome message
        // 2. Load existing tasks from storage
        // 3. Enter main loop:
        //    - Read user input
        //    - Process commands (bye, list, todo, deadline, event, mark, unmark, delete)
        //    - Handle exceptions and print appropriate messages
        // 4. Save tasks and exit when user inputs "bye"

        //ui.printFormattedResponse(String.format("Hello! I'm %s\nWhat can I do for you?", name));
            if (input.equals("bye")) {

                storage.writeFile(taskList.toFileFormatString());
                return ui.printByeMessage();
            }

            if (input.equals("list")) {
                return ui.printTaskListMessage(taskList.printList());
            }

            if (Todo.matchTodo(input)) {
                try {
                    String description = Parser.getTodoDescription(input);
                    return ui.printFormattedResponse(taskList.addTask(new Todo(description)));
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            if (Deadline.matchDeadline(input)) {
                try {
                    String desription = Parser.getDeadlineDescription(input);
                    LocalDateTime byDate = Parser.getDeadlineDate(input);
                    return ui.printFormattedResponse(taskList.addTask(new Deadline(desription, byDate)));
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            if (Event.matchEvent(input)) {
                try {
                    String description = Parser.getEventDescription(input);
                    LocalDateTime[] fromToDate = Parser.getEventFromAndToDate(input);
                    return ui.printFormattedResponse(taskList.addTask(new Event(description, fromToDate[0], fromToDate[1])));
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            if (input.startsWith("mark")) {
                try {
                    int num = Task.matchesMark(input);
                    return ui.printFormattedResponse(taskList.markTask(num));
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            if (input.startsWith("unmark")) {
                try {
                    int num = Task.matchesUnmark(input);
                    return ui.printFormattedResponse(taskList.unMarkTask(num));
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            if (input.startsWith("delete")) {
                try {
                    int i = TaskList.getDeleteNumber(input);
                    return ui.printFormattedResponse(taskList.deleteTask(i));
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            if (input.startsWith("find")) {
                try {
                    String targetWord = Parser.getFindTargetWord(input);
                    return ui.printFindMessage(taskList.find(targetWord).printList());
                } catch (InputFormatException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
            }

            else {
                return ui.printNoInputMatchedMessage();
            }
    }
}

