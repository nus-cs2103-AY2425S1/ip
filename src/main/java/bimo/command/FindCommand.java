package bimo.command;

import java.util.ArrayList;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;
import bimo.tasks.Task;


/**
 * Creates a command to find tasks by word.
 */
public class FindCommand extends Command {

    private String[] words;

    /**
     * Instantiates a command to list tasks by words
     * @param words Array of words specified by user.
     */
    public FindCommand(String... words) {
        this.words = words;
    }

    /**
     * Lists all tasks that contains the words specified.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = new ArrayList<>();
        assert tasks != null : "Task list must not be null";
        for (int i = 1; i < this.words.length; i++) {
            for (int j = 0; j < tasks.getLength(); j++) {
                Task task = tasks.getTask(j);
                assert task != null : "Task must not be null";
                if (task.getDetails().contains(this.words[i])) {
                    results.add(task);
                }
            }
        }
        String response = "Here are the matching tasks in your list:";
        for (int i = 0; i < results.size(); i++) {
            String message = String.format("\n    %d. %s", i + 1, results.get(i).toString());
            response += message;
        }
        return response;
    }
}
