package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.util.Storage;
import yapbot.util.TaskList;

public class FindCommand extends Command {

    private String query;
    private boolean multiwordQuery;

    public FindCommand(String query) throws YapBotException {
        if (query.isEmpty()) {
            throw new YapBotException("Error, Automated Search Completion module offline."
                    + "\nKeyword must be specified.");
        }

        int spacePos = query.indexOf(" ");
        if (spacePos != -1) {
            this.query = query.substring(0, query.indexOf(" "));
            this.multiwordQuery = true;
        } else {
            this.query = query;
            this.multiwordQuery = false;
        }

    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        String matchingTasks = tasks.getMatchingTasks(query);
        String successMessage = "Querying Database for \"" + query + "\"...Success\nMatching Tasks:\n";

        if (multiwordQuery) {
            successMessage = "Multiple words detected, only first word will be queried.\n\n" + successMessage;
        }

        if (matchingTasks == null) {
            return successMessage + "  -No Matching Tasks found-";
        } else {
            return successMessage + matchingTasks;
        }

    }

}
