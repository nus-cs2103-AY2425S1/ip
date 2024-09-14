package yappingbot.commands;

import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Deadline;
import yappingbot.tasks.Event;
import yappingbot.tasks.Task;
import yappingbot.tasks.Todo;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskListFilterView;
import yappingbot.tasks.tasklist.TaskTypes;
import yappingbot.ui.Ui;

/**
 * Class of implemented commands and their functions.
 */
public class CommandDispatcher {

    private final Ui ui;

    /**
     * Constructor for dispatcher to run commands.
     *
     * @param ui UI interface to output text
     */
    public CommandDispatcher(Ui ui) {
        assert ui != null;
        this.ui = ui;
    }

    /**
     * Marks or Unmarks a task in a task list.
     *
     * @param i integer index of task in list.
     * @param isTaskDone boolean of whether to mark or unmark the task.
     * @param userList TaskList to be searched.
     */
    public void changeTaskListStatus(int i, boolean isTaskDone, TaskList userList) {
        assert userList != null;
        assert userList.size() > i;

        Task t = userList.get(i);
        t.setTaskDone(isTaskDone);
        StringBuilder sb = new StringBuilder();
        if (isTaskDone) {
            sb.append(ReplyTextMessages.MARKED_TASK_AS_DONE_TEXT);
        } else {
            sb.append(ReplyTextMessages.UNMARKED_TASK_AS_DONE_TEXT);
        }
        sb.append("\n");
        sb.append(
                String.format(
                        ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t
                )
        );
        ui.print(sb.toString());
    }


}
