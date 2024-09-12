package choaticbot.actions;

import choaticbot.tasks.TaskList;

/**
 * The {@code Find} class represents an action that searches for tasks in the task list
 * based on a keyword. It extends the {@link Action} class and provides an implementation
 * to filter tasks that contain a specified word.
 */
public class Find extends Action {

    /**
     * The keyword to search for in the task list.
     */
    private String word;

    /**
     * Constructs a {@code Find} action with the specified task list and keyword.
     *
     * @param taskList The task list in which the search will be performed.
     * @param word The keyword used to filter the tasks.
     */
    public Find(TaskList taskList, String word) {
        super(taskList);
        this.word = word;
    }

    /**
     * Executes the find action. It filters the task list for tasks containing
     * the specified keyword.
     */
    @Override
    public void execute() {
        this.taskList.filterByWord(word);
    }
}
