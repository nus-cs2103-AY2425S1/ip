/**
 * Command represents an specific operation that interacts with the to-do list, store and ui
 */
public abstract class Command {

    /** The argument passed in to a command **/
    protected String arguments;
    /** The to-do list to keep track of to-dos **/
    protected TodoList list;
    /** The storage medium to archive the above list **/
    protected FileStore store;

    /**
     * Represents a command object that takes in its argument, list to modify on and store to save to
     *
     * @param arguments The argument to be passed in for a command
     * @param list The to-do list to modify
     * @param store The storage medium to interact
     */
    public Command (String arguments, TodoList list, FileStore store) {
        this.arguments = arguments;
        this.list = list;
        this.store = store;
    }

    /**
     * Executes the operation
     */
    public abstract void execute();

    /**
     * Check if the application should exit after the execution of this command
     *
     * @return A boolean representing if the application should exit
     */
    public boolean isExit() {
        return false;
    }
}
