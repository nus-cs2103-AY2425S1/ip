package lewis;

import java.util.ArrayList;

/**
 * This command tells Lewis to find all tasks that containing a keyword
 * in its description or date and time fields. Then, it will print
 * these tasks to the user.
 */
public class FindCommand extends Command {

    /** The keyword that this command should search for */
    private String keyword;
    private FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Factory method to instantiate a find command
     * Parses the user input from standard input stream
     * to instantiate the command
     * @return a FindCommand with the relevant keyword
     */
    public static FindCommand of(String input) throws LewisException {
        try {
            String[] tokens = input.split("find", 2);
            String keyword = tokens[1].trim();
            if (keyword.isEmpty()) {
                throw new LewisException("Oi, I can't find anything in there!");
            }
            return new FindCommand(keyword);
        } catch(IndexOutOfBoundsException e) {
            throw new LewisException("Oi, I can't find anything in there!");
        }
    }

    public static String getHelpDescription() {
        return "Displays all tasks matching a keyword in the current tasklist.\n" +
                "Usage: find <string>";
    }
    /**
     * Executes the command. Subclasses of this class must implement this logic.
     */
    @Override
    public void execute() {
        TaskList.allMatchingTasks(this.keyword);
        ArrayList<String> tasksToPrint = TaskList.allTasksToString();
        Ui.printList(tasksToPrint);
    }
}
