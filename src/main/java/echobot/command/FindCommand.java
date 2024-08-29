package echobot.command;

import echobot.exception.EchoBotException;
import echobot.exception.MultipleKeywordsException;
import echobot.exception.NullKeywordException;
import echobot.task.Task;

import java.util.List;

public class FindCommand extends Command {
    public final static String COMMAND = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse execute() throws EchoBotException {
        if (this.keyword.isBlank()) {
            throw new NullKeywordException();
        }
        if (this.keyword.contains(" ")) {
            throw new MultipleKeywordsException();
        }
        List<Task> tasks = this.taskList.findTasksByKeyword(keyword);
        if (tasks.isEmpty()) {
            return new CommandResponse("No tasks matching the keyword '" + this.keyword + "'!");
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n\t\t\t");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output.append(i + 1);
            output.append(".");
            output.append(task);
            output.append("\n\t\t\t");
        }

        if (!output.isEmpty()) {
            output.delete(output.length() - 4, output.length());
        }
        return new CommandResponse(output.toString());
    }
}
