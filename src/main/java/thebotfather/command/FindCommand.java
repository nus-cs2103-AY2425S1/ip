package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

import java.util.StringTokenizer;

public class FindCommand extends Command {

    /**
     * The word to search for in task descriptions.
     */
    private final String word;

    /**
     * Constructs a {@code FindCommand} with the specified command input.
     * <p>
     * The constructor parses the input tokens to extract the word to be searched
     * in the task descriptions. If no word is provided, it throws a
     * {@link TheBotFatherException}.
     *
     * @param tokens The tokens from the command input. The word to search for is expected
     *               to be contained in these tokens.
     * @throws TheBotFatherException If the word to search for is not specified in the input.
     */
    public FindCommand(StringTokenizer tokens) throws TheBotFatherException {
        StringBuilder builder = new StringBuilder();
        while (tokens.hasMoreTokens()) {
            builder.append(tokens.nextToken()).append(" ");
        }
        word = builder.toString().trim();
        if (word.isEmpty()) {
            throw new TheBotFatherException("What word you you want me to find bruh\n" +
                    "\tIf you want me to find a specific word type \"find <word>\"");
        }
    }

    /**
     * Executes the find command, searching for tasks containing the specified word.
     * <p>
     * This method filters tasks in the {@code taskList} by checking if their descriptions
     * contain the specified word. It then uses the {@code PrintTaskListCommand} to display
     * the filtered list of tasks.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The user interface to print the result.
     * @param storage The storage to persist changes (not used in this command).
     * @throws TheBotFatherException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        new PrintTaskListCommand().execute(taskList.findWord(word), ui, storage);
    }
}
