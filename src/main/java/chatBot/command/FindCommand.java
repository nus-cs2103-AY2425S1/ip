package chatbot.command;

import java.util.ArrayList;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;
import chatbot.task.Task;

/** FindCommand is a subclass of Command that finds the specified keywords in task list */
public class FindCommand extends Command {
    private final String keywordToFind;

    public FindCommand(String s) {
        this.keywordToFind = s.strip();
    }

    /**
     * Calls findKeyword method in TaskList class, which returns an ArrayList of Task.
     * Afterward, prints the tasks.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        if (this.keywordToFind.isEmpty()) {
            return "You must enter a keyword to find";
        }
        ArrayList<Task> tasksFound = taskList.findKeyword(this.keywordToFind);
        // streamify finding tasks soon
        if (tasksFound.size() > 0) {
            output = "Here are the matching tasks in your list:\n";
            for (Task task : tasksFound) {
                output += task + "\n";
            }
            output = output.stripTrailing();
        } else {
            output = "No matching tasks found";
        }
        return output;
    }
}
