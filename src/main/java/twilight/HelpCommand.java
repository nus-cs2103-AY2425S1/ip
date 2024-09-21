package twilight;

/**
 * Represents a command to give user information on the available commands in Twilight.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = """
            Commands to add tasks:
            'deadline deadlineName /by YYYY-MM-DD'
            'todo taskName'
            'event eventName /from startTime /to endTime'
            
            Other commands:
            'list': returns list of all tasks.
            'find description': returns tasks that match the description.
            'tag taskNumber #tag': tags the specified task.
            'mark taskNumber': marks the task as complete.
            'unmark taskNumber': unmarks the completion of the task.
            'delete taskNumber': deletes the specified task.
            'bye': exits the application.""";

    public HelpCommand() {

    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        return HELP_MESSAGE;
    }
}
