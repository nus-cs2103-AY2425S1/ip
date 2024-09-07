package mortalreminder.backend;

import java.util.Arrays;

import mortalreminder.backend.tasklistmanager.TaskCreator;
import mortalreminder.backend.tasklistmanager.TaskEditor;
import mortalreminder.backend.tasklistmanager.TaskList;
import mortalreminder.backend.tasklistmanager.TaskRetriever;
import mortalreminder.commands.Command;
import mortalreminder.commands.CommandAlternatives;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedPrinting;

// javadocs were generated using ChatGPT with minor edits.

/**
 * Handles the processing of commands related to task management.
 * <p>
 * The {@code Processor} class is responsible for interpreting and executing commands
 * related to tasks, such as creating tasks, marking them as done, deleting them, and
 * listing upcoming tasks.
 */
public class Processor {

    /**
     * Processes the given command and modifies the {@link TaskList} accordingly.
     * <p>
     * Depending on the command type, this method can print the task list, create a new task,
     * mark or unmark a task as done, delete a task, clear all tasks, or list upcoming tasks.
     *
     * @param command  the {@link Command} to process.
     * @param taskList the {@link TaskList} to modify based on the command.
     * @return response string which is the output message of the entire command.
     * @throws MortalReminderException based on different input or command errors found in the given command.
     */
    public String handleCommand(Command command, TaskList taskList) throws MortalReminderException {
        CommandType commandType = command.commandType();

        String commandDetails = Arrays.stream(command.commandDetails())
                .reduce((accumulator, element) -> accumulator + " " + element)
                .orElse("");

        switch (commandType) {
        case LIST:
            return FormattedPrinting.printList(taskList);
        case FIND:
            return TaskRetriever.findTasks(taskList, commandDetails);
        case MARK:
            // Fallthrough
        case UNMARK:
            return TaskEditor.executeMarkOrUnmark(commandDetails, taskList, commandType);
        case DELETE:
            return TaskEditor.executeDeletion(commandDetails, taskList);
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return TaskCreator.createTask(commandDetails, taskList, commandType);
        case CLEAR_TASKS:
            return taskList.clearList();
        case UPCOMING_TASKS:
            return TaskRetriever.getUpcomingTasks(taskList);
        case ADD_COMMAND_ALTERNATIVE:
            CommandAlternatives commandAlternatives = CommandAlternativesStorage.loadCommandsFromFile();
            return commandAlternatives.addCommandAlternative(commandDetails);
        case CLEAR_ALTERNATIVES:
            return CommandAlternativesStorage.clearAlternativesFile();
        default:
            return feedbackUnrecognisedCommand();
        }
    }

    /**
     * Returns feedback message if the command passed by the user is unrecognisable.
     * A list of all recognisable commands are also provided.
     */
    public String feedbackUnrecognisedCommand() {
        StringBuilder message = new StringBuilder("""
                I do not recognise this command, please check again!
                Available commands are:
                """);
        for (CommandType type : CommandType.values()) {
            if (type == CommandType.UNKNOWN) {
                continue;
            }
            message.append(type.name().toLowerCase()).append("\n");
        }
        return message.toString();
    }
}
