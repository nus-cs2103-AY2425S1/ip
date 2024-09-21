package alexer.command;

import alexer.ui.Response;

/**
 * Displays the help information on the capabilities of Alexer
 * and the commands that are available
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public Response run(String... arguments) {
        return new Response("Alexer, a chat bot to improve your life by managing your tasks.\n" +
                "Here are commands for Alexer:\n\n" +
                "\ttodo <description> - Creates a new To-Do task with a description\n" +
                "\tevent <description> /from <when> /to <when> - Adds an event with starting and ending time\n" +
                "\tdeadline <description> /by <date-time> - Add a new deadline task with deadline given\n" +
                "\tmark <index> - Marks a task as completed/done\n" +
                "\tlist - Shows all your tasks and numbered by their index\n" +
                "\tfind <keyword> - Searches for a task with description that contains the given keyword\n" +
                "\tdelete <index> - Removes the task at the given index from your task list\n" +
                "\nExample usage (do try them out!):\n\n" +
                "\ttodo do maths homework\nlist\nfind homework\nmark 1\ndelete 1\n");
    }
}
