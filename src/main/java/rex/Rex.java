package rex;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import rex.command.Command;
import rex.exception.InvalidCommandException;
import rex.exception.InvalidInputException;
import rex.task.Deadline;
import rex.task.Event;
import rex.task.Task;
import rex.task.TaskList;
import rex.task.ToDo;
import rex.util.Parser;
import rex.util.Storage;
import rex.util.Ui;

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

    public String getResponse(String input) {
        Command command = null;
        try {
            String[] inputTokens = Parser.parseInput(input);

            // Process user command
            command = Command.inputToCommand(inputTokens[0]);
            switch (command) {
            case HELP:
                return ui.getHelp();
            case TODO:
                String argument = inputTokens[1];
                ToDo toDo = taskList.addToDo(argument);
                return ui.addTask(toDo);
            case DEADLINE:
                argument = inputTokens[1];
                Deadline deadline = taskList.addDeadline(argument);
                return ui.addTask(deadline);
            case EVENT:
                argument = inputTokens[1];
                Event event = taskList.addEvent(argument);
                return ui.addTask(event);
            case LIST:
                String output = taskList.getListDisplay();
                return ui.displayList(output);
            case FIND:
                argument = inputTokens[1];
                output = taskList.findTasks(argument);
                return ui.findTask(output);
            case MARK:
                argument = inputTokens[1];
                Task marked = taskList.markTask(argument);
                return ui.markTask(marked);
            case UNMARK:
                argument = inputTokens[1];
                Task unmarked = taskList.unmarkTask(argument);
                return ui.unmarkTask(unmarked);
            case DELETE:
                argument = inputTokens[1];
                Task deleted = taskList.deleteTask(argument);
                return ui.deleteTask(deleted);
            case RAWR:
                return ui.rawr();
            case BYE:
                return ui.getGoodbye();
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidInputException e) {
            if (command != null) {
                String usageMessage = Command.usageMessage(command);
                return ui.errorMessage(e.getMessage() + "\nUsage: " + usageMessage);
            } else {
                return ui.errorMessage(e);
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            throw new RuntimeException(e);
        } catch (DateTimeParseException e) {
            String usageMessage = Command.usageMessage(command);
            return ui.errorMessage("Wrong date/time format!\nUsage: " + usageMessage);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            String usageMessage = Command.usageMessage(command);
            return ui.errorMessage("Invalid task number!\nUsage: " + usageMessage);
        }
    }
}
