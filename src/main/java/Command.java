/**
 * Command represents a specific operation that interacts with the to-do list, store and ui
 */
public abstract class Command {

    /** The argument passed in to a command **/
    protected String arguments;

    /**
     * Represents a command object that takes in its argument
     *
     * @param arguments The argument to be passed in for a command
     */
    public Command (String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the operation
     *
     * @param list The to-do list to modify
     * @param ui The ui for interaction
     * @param store The storage medium to interact
     */
    public abstract void execute(TodoList list, Ui ui, FileStore store);

    /**
     * Check if the application should exit after the execution of this command
     *
     * @return A boolean representing if the application should exit
     */
    public boolean isExit() {
        return false;
    }

}
