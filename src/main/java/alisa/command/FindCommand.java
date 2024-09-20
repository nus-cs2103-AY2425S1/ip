package alisa.command;

import alisa.Storage;
import alisa.Ui;
import alisa.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs an instance of FindCommand.
     *
     * @param keyword Word to search for in task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * Finds the keyword in the list of tasks and returns all the tasks that contains the keyword.
     *
     * @param taskList List of tasks.
     * @param ui Ui that displays the result after the command.
     * @param storage Storage that saves the file of the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTasks(keyword);
    }

    /**
     * {@inheritDoc}
     *
     * Indicates that the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
