package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.InvalidInputStobberiException;
import stobberi.stobberiexception.StobberiException;

/**
 * Represents a command to display a help message.
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
     *
     * @param taskList      The list of tasks (though not used in this command).
     * @param restOfCommand The rest of the command given (though not used in this command).
     */
    public HelpCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by returning the help message.
     * If additional text is provided in the command, an {@link InvalidInputStobberiException} is thrown.
     *
     * @return The help message containing the list of available commands.
     * @throws StobberiException If additional text is provided in the command.
     */
    @Override
    public String execute() throws StobberiException {
        if (getRestOfCommand().isEmpty()) {
            return HELP_MESSAGE;
        }
        throw new InvalidInputStobberiException("Do you mean 'list'? "
                + "Pwease don't add unnecessary stuff!");
    }
}
