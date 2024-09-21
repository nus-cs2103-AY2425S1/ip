package commands;

import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Represents a bye command entered by the user.
 */
public class ByeCommand extends Command {
    /**
     * Stores the command string associated with bye command.
     *
     * @param command Command string.
     */
    public ByeCommand(String command) {
        super(command);
    }

    private String getResponse() {
        return "Bye. Hope to see you again soon!\n"
                + "Program will close now ...";
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot returns bye message and terminates.
     * </p>
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) {
        return this.getResponse();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "bye";
    }
}
