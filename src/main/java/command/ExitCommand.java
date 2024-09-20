package command;

/**
 * ExitCommand class is used to indicate that the user wants to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     */
    @Override
    public void execute() {
    }   

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }


}
