package derek.command;

import derek.command.Command;

/**
 * The {@code TaskCommand} class is an abstract class that represents a command related to tasks.
 * It extends the {@code Command} class and provides a method to extract the task details from the command.
 */
public class TaskCommand extends Command {

    /**
     * Constructs a {@code TaskCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public TaskCommand(String command) {
        super(command);
    }

    /**
     * Extracts the task details from the command.
     *
     * @return the task details as a string
     */
    public String getTask(){
        String command = super.getCommand();
        int firstWord = command.indexOf(" ");
        return command.substring(firstWord + 1);

    }
}
