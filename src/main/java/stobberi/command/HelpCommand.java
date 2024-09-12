package stobberi.command;

/**
 * Represents a command to display a Help message.
 */
public class HelpCommand extends Command {
    /**
     * Help message to be displayed.
     */
    private static final String HELP_MESSAGE = """
            Here are the list of commands:

            Types of Tasks
                   todo {description} : Creates a new task of type todo
                   deadline {description} {due date and time} : Creates a new task of type deadline
                   event {description} {start date and time} {end date and time} : Creates a new task of type event

            Other Possible Commands
                   list : Displays all the tasks in the list
                   bye : Exits the application
                   date {date} : Displays all deadlines and events that occur on the input date
                   find {String} : Displays all the tasks which has the input String in the description
                   mark {task number in the list} : Marks the task as done
                   unmark {task number in the list} : Marks the task as not done
                   ? : Displays the possible commands

            Remember all commands entered has to be in lowercase!""";

    /**
     * Constructs a new {@code HelpCommand}.
     */
    public HelpCommand() {}

    /**
     * Executes the command by displaying a help message.
     */
    @Override
    public String execute() {
        return HELP_MESSAGE;
    }
}
