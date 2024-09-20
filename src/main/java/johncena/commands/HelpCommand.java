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
    public void execute() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the commands you can use:");
        System.out.println("  bye - Exits the program");
        System.out.println("  list - Lists all tasks");
        System.out.println("  mark [task number] - Marks a task as done");
        System.out.println("  unmark [task number] - Marks a task as not done");
        System.out.println("  delete [task number] - Deletes a task");
        System.out.println("  todo [description] - Adds a todo task");
        System.out.println("  deadline [description] /by [due date] - Adds a deadline task");
        System.out.println("  event [description] /from [start date] /to [end date] - Adds an event task");
        System.out.println("  on [date] - Lists all tasks on a specific date");
        System.out.println("  hello - Displays the welcome message");
        System.out.println("  help - Displays the list of commands");
        System.out.println("____________________________________________________________");
    }
}
