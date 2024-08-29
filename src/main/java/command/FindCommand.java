package command;

import java.io.IOException;
import java.util.ArrayList;

import exception.ScheduloException;
import task.Task;
import task.TaskList;
import util.Storage;
import util.Ui;

/**
 * Represents a command to find tasks that contain a specific word.
 */
public class FindCommand extends Command {

    private String word;

    /**
     * Constructs a FindCommand with the specified search word.
     *
     * @param word The word to search for in task names.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the command to find and display tasks containing the specified word.
     *
     * @param tasks   The TaskList to search in.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance for saving and loading tasks.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task task = tasks.getTasks().get(i);
            if (task.getName().contains(word)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showError("No matching tasks found.");
        } else {
            ui.showLine();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
            }
            ui.showLine();
        }
    }
}
