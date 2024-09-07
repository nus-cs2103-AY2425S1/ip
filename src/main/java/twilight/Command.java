package twilight;

/**
 * Represents an action to be carried out by the chatbot.
 */
abstract class Command {
    /**
     * Executes the particular command assigned.
     * Prints out a message to inform user of the execution of change.
     *
     * @param tasks Tasklist which may be modified during execution.
     * @param storage Storage to be updated via tasklist post changes.
     * @throws InvalidInputException when the input is invalid and command cannot be executed.
     */
    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        if (isExit()) {
            return "See you";
        }
        return "There is an error";
    }


    /**
     * Confirms if the command is an exit command.
     * @return whether it is an exit command.
     */
    public boolean isExit() { return false; }
}
