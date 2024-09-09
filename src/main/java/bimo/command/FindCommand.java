package bimo.command;

import java.util.ArrayList;

import bimo.tasks.Task;
import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command to find tasks by word.
 */
public class FindCommand extends Command {

    private String[] specifiedWords;

    /**
     * Instantiates a command to list tasks by words
     * @param specifiedWords Array of words specified by user.
     */
    public FindCommand(String... specifiedWords) {
        this.specifiedWords = specifiedWords;
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
        this.filterTasks(results, tasks);
        String response = ui.printResultsList(results);
        return response;
    }

    /**
     * Filters and add specified task into empty arraylist.
     *
     * @param results ArrayList containing filtered tasks.
     * @param tasks list containing all tasks.
     */
    public void filterTasks(ArrayList<Task> results, TaskList tasks) {
        for (int i = 1; i < this.specifiedWords.length; i++) {
            for (int j = 0; j < tasks.getLength(); j++) {
                Task task = tasks.getTask(j);
                assert task != null : "Task must not be null";
                boolean isInsideResults = results.contains(task);
                boolean hasWord = task.getDetails().contains(this.specifiedWords[i]);
                if (hasWord && !isInsideResults) {
                    results.add(task);
                }
            }
        }
    }
}
