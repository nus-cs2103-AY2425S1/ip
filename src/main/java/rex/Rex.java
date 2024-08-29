package rex;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Deadline;
import rex.task.Event;
import rex.task.Task;
import rex.task.TaskList;
import rex.task.ToDo;
import rex.util.Parser;
import rex.util.Storage;
import rex.util.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * The {@code Rex} class serves as the main entry point for the Rex application,
 * a task management system that allows users to manage their tasks via various commands.
 * It integrates storage, task management, and user interaction functionalities.
 */
public class Rex {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The {@code Rex} class serves as the main entry point for the Rex application,
     * a task management system that allows users to manage their tasks via various commands.
     * It integrates storage, task management, and user interaction functionalities.
     */
    public Rex() {
        try {
            this.storage = new Storage();
            this.taskList = new TaskList(storage);
            this.ui = new Ui();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
    }

    /**
     * Starts the Rex application, providing an interactive loop that continuously reads user input
     * and processes it into various commands until the "BYE" command is issued.
     *
     * <p>The following commands are supported:</p>
     * <ul>
     *     <li>{@code HELP} - Displays help information.</li>
     *     <li>{@code TODO} - Adds a new to-do task.</li>
     *     <li>{@code DEADLINE} - Adds a new deadline task.</li>
     *     <li>{@code EVENT} - Adds a new event task.</li>
     *     <li>{@code LIST} - Displays all tasks in the task list.</li>
     *     <li>{@code MARK} - Marks a task as done.</li>
     *     <li>{@code UNMARK} - Unmarks a task (marks it as not done).</li>
     *     <li>{@code DELETE} - Deletes a task from the list.</li>
     *     <li>{@code RAWR} - Displays a special message.</li>
     *     <li>{@code BYE} - Exits the application.</li>
     * </ul>
     */
    public void run() {
        ui.greeting();

        while (true) {
            Command command = null;
            try {
                String input = ui.readInput();
                String[] inputTokens = Parser.parseInput(input);

                // Process user command
                command = Command.inputToCommand(inputTokens[0]);
                switch (command) {
                case HELP:
                    ui.help();
                    break;
                case TODO:
                    String argument = inputTokens[1];
                    ToDo toDo = taskList.addToDo(argument);
                    ui.addTask(toDo);
                    break;
                case DEADLINE:
                    argument = inputTokens[1];
                    Deadline deadline = taskList.addDeadline(argument);
                    ui.addTask(deadline);
                    break;
                case EVENT:
                    argument = inputTokens[1];
                    Event event = taskList.addEvent(argument);
                    ui.addTask(event);
                    break;
                case LIST:
                    String output = taskList.getListDisplay();
                    ui.displayList(output);
                    break;
                case FIND:
                    argument = inputTokens[1];
                    output = taskList.findTasks(argument);
                    ui.findTask(output);
                    break;
                case MARK:
                    argument = inputTokens[1];
                    Task marked = taskList.markTask(argument);
                    ui.markTask(marked);
                    break;
                case UNMARK:
                    argument = inputTokens[1];
                    Task unmarked = taskList.unmarkTask(argument);
                    ui.unmarkTask(unmarked);
                    break;
                case DELETE:
                    argument = inputTokens[1];
                    Task deleted = taskList.deleteTask(argument);
                    ui.deleteTask(deleted);
                    break;
                case RAWR:
                    ui.rawr();
                    break;
                case BYE:
                    ui.goodbye();
                    return;
                }
            } catch (InvalidInputException e) {
                if (command != null) {
                    String usageMessage = Command.usageMessage(command);
                    ui.errorMessage(e.getMessage() + "\nUsage: " + usageMessage);
                } else {
                    ui.errorMessage(e);
                }
            } catch (IOException e) {
                System.out.println("An error has occurred.");
                throw new RuntimeException(e);
            } catch (DateTimeParseException e) {
                String usageMessage = Command.usageMessage(command);
                ui.errorMessage("Wrong date/time format!\nUsage: " + usageMessage);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                String usageMessage = Command.usageMessage(command);
                ui.errorMessage("Invalid task number!\nUsage: " + usageMessage);
            }
        }
    }

    /**
     * The main method serves as the entry point for the application.
     * It creates a new {@code Rex} instance and starts the application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Rex rex = new Rex();
        rex.run();
    }
}
