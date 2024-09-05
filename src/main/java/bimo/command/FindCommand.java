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

    private String word;

    /**
     * Instantiates a command to list tasks by words
     * @param word
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Lists all tasks that contains word specified.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = new ArrayList<>();

        for (int i = 0; i < tasks.getLength(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDetails().contains(this.word)) {
                results.add(task);
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
