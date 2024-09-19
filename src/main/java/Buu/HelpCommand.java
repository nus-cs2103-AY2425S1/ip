package Buu;

/**
 * Represents a command that provides help information to the user in the Buu Task Manager application.
 * This command displays a list of available commands and their usage when executed, all in the cheerful
 * and playful persona of Buu from Dragon Ball.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command by displaying a list of available commands and their usage.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.addToOutputBuffer("That doesn't look right!\n"
                + "Here are some things you can do\n");
        ui.addToOutputBuffer("todo [task description] - Add a new task for Buu to remember!");
        ui.addToOutputBuffer("deadline [deadline description] /by [date/time] - Set a deadline!"
                + " Don't keep Buu waiting too long!");
        ui.addToOutputBuffer("event [event description] /from [start date/time] /to [end date/time] - "
                + "Create a fun event! Buu loves fun!");
        ui.addToOutputBuffer("list - Show all tasks. Buu loves to see progress!");
        ui.addToOutputBuffer("mark [task number] - Mark a task as done! Yay! Buu is happy!");
        ui.addToOutputBuffer("unmark [task number] - Unmark a task. Hmm, did Goku forget?");
        ui.addToOutputBuffer("delete [task number] - Remove a task! Buu will miss it, but okay!");
        ui.addToOutputBuffer("find [keyword] - Find a task! Buu will sniff it out for you!");
        ui.addToOutputBuffer("priority [task number] [priority level] - Set task priority! "
                + "Buu likes important tasks first! "
                + "(1: Low, 2: Medium, 3: High)");
        ui.addToOutputBuffer("bye - Exit the program! Bye-bye, Goku! Buu will be waiting for you to come back! ");
    }
}
