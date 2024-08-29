package joe.commands;

public class HelpCommand extends Command {

    /**
     * Prints a list of available commands.
     */
    @Override
    public void execute() {
        System.out.println("bye: ends our interaction :-(");
        System.out.println("deadline <description> /by <due date/time>: Creates a Deadline task");
        System.out.println("delete <idx>: Deletes the task at your chosen index");
        System.out.println("event <description> /from <start date/time> /to <end date/time>: Creates an Event task");
        System.out.println("find <queryString>: Finds task with descriptions matching your query regex");
        System.out.println("list: Displays your current tasks");
        System.out.println("mark <idx>: Marks the task at your chosen index");
        System.out.println("save : Saves all tasks in your current list to the database that will be automatically " +
                "loaded during your next session");
        System.out.println("todo <description>: Creates a ToDo task");
        System.out.println("unmark <idx>: Unmarks the task at your chosen index");
    }
}
