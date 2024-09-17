package main.command;

import main.exceptions.PrinceException;
import main.tasks.Task;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command that finds tasks containing the keyword
 * as inputted by the user.
 */
public class FindCommand extends Command {
    private String input;

    /**
     * Constructor for FindCommand class.
     * @param input Input by the user.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks that contains the keyword as inputted by the user.
     * @param input Input by the user.
     * @param taskList List of tasks.
     * @param ui Ui as initialised in main.
     * @throws PrinceException
     */
    private void find(String input, TaskList taskList, Ui ui) {
        String[] arr = input.split(" ");
        String keyword = arr[1].trim();

        TaskList newTaskList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String desc = task.getDescription();
            if (desc.contains(keyword)) {
                newTaskList.add(task);
            }
        }

        if (newTaskList.size() > 0) {
            ui.showMatchingTaskList(newTaskList);
        } else {
            ui.showNoMatchingTasks();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        find(input, tasks, ui);
    }
}
