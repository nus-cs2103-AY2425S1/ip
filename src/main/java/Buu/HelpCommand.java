package Buu;
/**
 * Represents a command that provides help information to the user in the GPT application.
 * This command displays a list of available commands and their usage when executed.
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
        ui.addToOutputBuffer("You can try these commands:");
        ui.addToOutputBuffer("todo [task description] - To add a ToDo task");
        ui.addToOutputBuffer("deadline [task description] /by [date/time] - To add a Deadline task");
        ui.addToOutputBuffer("event [task description] /from [start date/time]"
                + " /to [end date/time] - To add an Event task");
        ui.addToOutputBuffer("list - To display all tasks");
        ui.addToOutputBuffer("mark [task number] - To mark a task as done");
        ui.addToOutputBuffer("unmark [task number] - To unmark a task");
        ui.addToOutputBuffer("delete [task number] - To delete a task");
        ui.addToOutputBuffer("find [keyword] - To find tasks by keyword");
        ui.addToOutputBuffer("priority [task number] [priority level] - To set the priority of a task "
                + "(1: Low, 2: Medium, 3: High)");
        ui.addToOutputBuffer("bye - To exit the chatbot");
    }
}
