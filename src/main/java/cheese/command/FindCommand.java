package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command{
    private final String keyword;

    /**
     * Constructor for FindCommand, requires keyword to find task
     * @param inputTokens String[]
     * @throws CheeseException Ensure correct input
     */
    public FindCommand(String[] inputTokens) throws CheeseException {
        if (inputTokens.length != 2) throw new CheeseException("FindCommand require 'find ....'");
        this.keyword = inputTokens[1];
    }

    /**
     * Searches through task list to find matching tasks
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getName().contains(keyword)) matchedTasks.add(t);
        }
        ui.say(matchedTasks);
    }
}
