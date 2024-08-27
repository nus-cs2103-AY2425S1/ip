package GPT;
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
        ui.showLine();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("You can try these commands:");
        System.out.println("  todo [task description] - To add a ToDo task");
        System.out.println("  deadline [task description] /by [date/time] - To add a Deadline task");
        System.out.println("  event [task description] /from [start date/time] /to [end date/time] - To add an Event task");
        System.out.println("  list - To display all tasks");
        System.out.println("  mark [task number] - To mark a task as done");
        System.out.println("  unmark [task number] - To unmark a task");
        System.out.println("  delete [task number] - To delete a task");
        System.out.println("  bye - To exit the chatbot");
        ui.showLine();
    }
}
