package mortalreminder;

import mortalreminder.backend.CommandAlternativesStorage;
import mortalreminder.backend.Processor;
import mortalreminder.backend.TaskListStorage;
import mortalreminder.backend.tasklistmanager.TaskList;
import mortalreminder.commands.Command;
import mortalreminder.commands.CommandAlternatives;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedOutput;

/**
 * Main class of the MortalReminder App.
 */
public class MortalReminder {

    private final Processor processor = new Processor();

    private TaskList taskList = new TaskList();

    private CommandAlternatives commandAlternatives;

    /**
     * This method loads all tasks from storage and greets the user.
     *
     * @return string of welcome message with initial greeting to the user.
     */
    public String welcome() {
        try {
            this.taskList = TaskListStorage.loadTaskListFromFile();
            this.commandAlternatives = CommandAlternativesStorage.loadCommandsFromFile();
        } catch (MortalReminderException e) {
            return e.getMessage();
        }
        return FormattedOutput.welcome();
    }

    /**
     * Initialises the execution of a command and returns the feedback message from the processor.
     *
     * @param command command created using the parser.
     * @return the string response from the chatbot after command has been executed.
     */
    public String executeCommand(Command command) {
        try {
            return processor.handleCommand(command, taskList);
        } catch (MortalReminderException e) {
            return e.getMessage();
        }
    }
}
