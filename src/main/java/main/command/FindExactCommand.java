package main.command;

import main.exceptions.PrinceException;
import main.tasks.Task;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command that finds exact match for tasks containing 
 * the keyword/phrase as inputted by the user.
 */
public class FindExactCommand extends Command {
    private String input;

    /**
     * Constructor for FindCommand class.
     * @param input Input by the user.
     */
    public FindExactCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks that matches the exact keyword/phrase as inputted by the user.
     * @param input Input by the user.
     * @param taskList List of tasks.
     * @param ui Ui as initialised in main.
     * @throws PrinceException
     */
    private void findExact(String input, TaskList taskList, Ui ui) {
        String[] arr = input.split(" ");
        StringBuilder builder = new StringBuilder();
        String taskDescription;

        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1) {
                builder.append(arr[i]); // ensure no spacing at the last word of the string
            } else {
                builder.append(arr[i] + " ");
            }
            System.out.println("DEBUG: " + builder);
        }
        taskDescription = builder.toString();

        TaskList newTaskList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String desc = task.getDescription();
            if (desc.equals(taskDescription)) {
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
        findExact(input, tasks, ui);
    }
}
