package prince.command;

import prince.exceptions.PrinceException;
import prince.tasks.Task;
import prince.tasks.TaskList;
import prince.util.Storage;
import prince.util.Ui;

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
    private void find(String input, TaskList taskList, Ui ui) throws PrinceException {
        String[] arr = input.split(" ");
        String find = arr[0].trim();
        String keyword = arr[1].trim();
        TaskList newTaskList = new TaskList();
        if (find.equals("find")) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String desc = task.getDescription();
                if (desc.contains(keyword)) {
                    newTaskList.add(task);
                }
            }
        } else {
            throw new PrinceException("Please ensure that your input begins with 'find'!");
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
