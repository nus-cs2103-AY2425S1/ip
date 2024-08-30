package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;
import bimo.tasks.Task;

import java.util.ArrayList;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = new ArrayList<>();

        for (int i = 0; i < tasks.getLength(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDetails().contains(this.word)) {
                results.add(task);
            }
        }
        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            String message = String.format("    %d. %s", i + 1, results.get(i).toString());
            System.out.println(message);
        }
    }
}
