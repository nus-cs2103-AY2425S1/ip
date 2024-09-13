package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;

/**
 * Represents a command that tells the chatbot to exit and shutdown.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a new ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the exit command.
     *
     * Once the exit command is run, the program will save the current state of the tasklist
     * to the memory before quitting.
     *
     * @param tasklist The TaskList object whose state is to be saved to memory.
     * @param storage The Storage object used to save the state of the task list.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        storage.save();
        return "Seeya!";
    }
}
