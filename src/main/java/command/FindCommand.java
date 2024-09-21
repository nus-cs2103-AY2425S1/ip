package command;

import exception.ParserException;
import tasklist.TaskList;

/**
 * Handles related issues to the find command
 */
public class FindCommand extends Command {
    private String pattern;
    /**
     * Constructor for the find command from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found in the command line
     */
    public FindCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument pattern for command find");
        }
        this.pattern = args[1];
    }

    @Override
    public String getResponse(TaskList tasks) {
        TaskList filteredTasks = tasks.find(this.pattern);
        if (filteredTasks.isEmpty()) {
            return "You have no task that contains pattern " + pattern + "\n";
        } else {
            String response = "You have " + tasks.size() + " tasks that contains pattern " + pattern + " as follow:\n";
            for (int i = 0; i < filteredTasks.size(); ++i) {
                response += (i + 1) + ". " + filteredTasks.get(i) + "\n";
            }
            return response;
        }
    }
}
