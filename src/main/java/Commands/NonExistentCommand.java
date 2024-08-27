package commands;

import applemazer.*;

public class NonExistentCommand extends Command {
    /**
     * Does not do anything since it is a non-existent command.
     * @param tasks   The task list to use if necessary.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {}

    /**
     * @return Returns true as the chatbot should continue running after executing a non-existent command.
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
