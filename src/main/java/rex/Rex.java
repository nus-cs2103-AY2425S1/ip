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
            if (inputTokens.length == 1) {
                return commandToOutput(command);
            } else {
                return commandToOutput(command, inputTokens[1]);
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
            assert command != null : "command should not be null";
            String usageMessage = Command.usageMessage(command);
            return ui.errorMessage("Wrong date/time format!\nUsage: " + usageMessage);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            assert command != null : "command should not be null";
            String usageMessage = Command.usageMessage(command);
            return ui.errorMessage("Invalid task number!\nUsage: " + usageMessage);
        }
    }

    private String commandToOutput(Command command, String ... argument)
            throws InvalidInputException, IOException {
        if (argument.length > 1) {
            throw new InvalidInputException("Too many arguments.");
        }

        switch (command) {
        case HELP:
            return ui.getHelp();
        case TODO:
            ToDo toDo = taskList.addToDo(argument[0]);
            return ui.addTask(toDo);
        case DEADLINE:
            Deadline deadline = taskList.addDeadline(argument[0]);
            return ui.addTask(deadline);
        case EVENT:
            Event event = taskList.addEvent(argument[0]);
            return ui.addTask(event);
        case LIST:
            return ui.displayList(taskList);
        case SCHEDULE:
            return ui.displaySchedule(taskList, argument[0]);
        case FIND:
            return ui.findTask(taskList, argument[0]);
        case MARK:
            Task marked = taskList.markTask(argument[0]);
            return ui.markTask(marked);
        case UNMARK:
            Task unmarked = taskList.unmarkTask(argument[0]);
            return ui.unmarkTask(unmarked);
        case DELETE:
            Task deleted = taskList.deleteTask(argument[0]);
            return ui.deleteTask(deleted);
        case RAWR:
            return ui.rawr();
        case BYE:
            return ui.getGoodbye();
        default:
            throw new InvalidCommandException();
        }
    }
}
