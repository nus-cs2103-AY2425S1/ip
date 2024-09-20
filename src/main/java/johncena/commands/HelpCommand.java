package johncena.commands;

/**
 * The {@code HelpCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "help" command, which displays the list of commands
 * that the user can use.
 */
public class HelpCommand implements Command {


    /**
     * Executes the "help" command. Displays the list of commands that the user can use.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the commands you can use:\n");
        sb.append("  bye - Exits the program\n");
        sb.append("  list - Lists all tasks\n");
        sb.append("  mark [task number] - Marks a task as done\n");
        sb.append("  unmark [task number] - Marks a task as not done\n");
        sb.append("  delete [task number] - Deletes a task\n");
        sb.append("  todo [description] - Adds a todo task\n");
        sb.append("  deadline [description] /by [due date] - Adds a deadline task\n");
        sb.append("  event [description] /from [start date] /to [end date] - Adds an event task\n");
        sb.append("  on [date] - Lists all tasks on a specific date\n");
        sb.append("  hello - Displays the welcome message\n");
        sb.append("  help - Displays the list of commands\n");
        return sb.toString();
    }
}
