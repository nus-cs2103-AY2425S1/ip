package lawrence.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import lawrence.database.TaskFileManager;
import lawrence.parser.InputSource;
import lawrence.parser.TaskParser;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;
import lawrence.utils.DateParser;

/**
 * Represents the user command to create new tasks.
 */
public class AddTaskCommand extends Command {
    private final String input;

    /**
     * Default constructor.
     *
     * @param input the user input associated with this command
     */
    public AddTaskCommand(CommandType type, String input) {
        super(type);
        this.input = input;
    }

    /**
     * Creates the relevant task based on user input, then adds
     * the new task into the {@link TaskList} and saves the
     * tasks into a text file.
     * <p>
     * Displays the result of the execution to the user afterwards.
     * </p>
     * <p>
     * If information about the task to add is invalid,
     * no new task will be created and the text file will not be updated.
     * </p>
     *
     * @param tasks a list of tasks the command may operate
     *              on
     * @param manager a {@link TaskFileManager} instance that
     *                the command may use when saving changes
     *                made
     * @param ui a {@link UserInterface} instance to display
     *           possible messages to the user
     */
    @Override
    public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        try {
            Task t = TaskParser.createTask(input, InputSource.USER);
            tasks.addTask(t);
            saveTasks(tasks, manager);

            // Format components of message to display
            int numberOfTasks = tasks.getSize();
            String verb = numberOfTasks == 1 ? "is" : "are";
            String plural = numberOfTasks == 1 ? "" : "s";
            this.response = String.format("Alright, added task:%n%s%nto the list.%n"
                    + "There %s currently %d task%s in the list.", t, verb, numberOfTasks, plural);
        } catch (DateTimeParseException e) {
            this.response = String.format("Invalid date and/or time provided. DateTime should be in the format: %s",
                    DateParser.FORMAT_STRING_FOR_USER_INPUT);
        } catch (IllegalArgumentException e) {
            this.response = e.getMessage();
        } catch (IOException e) {
            this.response = "Failed to save tasks to file. Please try again.";
        }
    }
}
