package thebotfather.command;

import java.util.StringTokenizer;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents a command to find tasks containing a specific word in their descriptions.
 */
public class FindCommand extends Command {

    /**
     * The word to search for in task descriptions.
     */
    private final String word;

    /**
     * Constructs a {@code FindCommand} by parsing the input tokens to extract the word to be searched.
     * If no word is provided, an exception is thrown.
     *
     * @param tokens The input tokens containing the word to search for.
     * @throws TheBotFatherException If the word to search is not specified.
     */
    public FindCommand(StringTokenizer tokens) throws TheBotFatherException {
        StringBuilder builder = new StringBuilder();
        while (tokens.hasMoreTokens()) {
            builder.append(tokens.nextToken()).append(" ");
        }
        word = builder.toString().trim();
        if (word.isEmpty()) {
            throw new TheBotFatherException("What word you you want me to find bruh\n"
                    + "If you want me to find a specific word type \"find <word>\"");
        }
    }

    /**
     * Executes the find command by searching for tasks that contain the specified word
     * in their descriptions. The filtered list of tasks is then displayed.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The user interface to display the results.
     * @param storage  The storage system (not used in this command).
     * @return A string representing the filtered list of tasks that match the search criteria.
     * @throws TheBotFatherException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        return new PrintTaskListCommand().execute(taskList.findWord(word), ui, storage);
    }
}
