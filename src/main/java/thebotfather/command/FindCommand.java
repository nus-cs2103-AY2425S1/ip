package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

import java.util.StringTokenizer;

public class FindCommand extends Command {
    private final String word;

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        new PrintTaskListCommand().execute(taskList.findWord(word), ui, storage);
    }
}
