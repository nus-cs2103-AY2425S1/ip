package chatBot.command;

import chatBot.bot.TaskList;
import chatBot.bot.Ui;
import chatBot.bot.Storage;
import chatBot.task.Task;

import java.util.ArrayList;

/** FindCommand is a subclass of Command that finds the specified keywords in task list */
public class FindCommand extends Command {
    private final String keywordToFind;

    public FindCommand(String s) {
        this.keywordToFind = s;
    }

    /**
     * Calls findKeyword method in TaskList class, which returns an ArrayList of Task.
     * Afterward, prints the tasks.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasksFound = taskList.findKeyword(this.keywordToFind);
        if (tasksFound.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : tasksFound) {
                System.out.println(task);
            }
        } else {
            System.out.println("No matching tasks found");
        }
    }
}
