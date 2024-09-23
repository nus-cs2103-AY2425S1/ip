package yappingbot.commands;

import yappingbot.commands.commands.ArgEnums;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.ui.Ui;

/**
 * Creates a new task and inserts it into the task list. This is the base class for different
 * Task types to extend.
 */
public abstract class CreateTaskCommandBase<A extends Enum<A> & ArgEnums<A>>
        extends CommandBase<A, CreateTaskCommandBase<A>> {
    private TaskList userList;
    private Ui ui;

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public CreateTaskCommandBase(String[] argSlices) throws YappingBotIncorrectCommandException {
        super(argSlices);
    }

    @Override
    protected String getArgumentSeperator() {
        return "/";
    }

    /**
     * Creates a new task and inserts it into the task list.
     *
     * @return this object, useful for chaining.
     */
    @Override
    protected CreateTaskCommandBase<A> run() {
        assert userList != null;
        Task newTask = createNewTask();
        ui.println(ReplyTextMessages.ADDED_TEXT);
        ui.printf(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                  newTask.getTaskTypeSymbol(),
                  newTask.getTaskDoneCheckmark(),
                  newTask);
        userList.addTask(newTask);
        ui.println(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()));
        return this;
    }

    /**
     * Creates the task based on each subclass's implementation of its Task.
     *
     * @return Task that is newly created
     */
    protected abstract Task createNewTask();

    /**
     * Set the necessary values needed to execute this command.
     *
     * @param currentUserList TaskList that needs to be resetted
     * @return this object, useful for chainning
     */
    public CreateTaskCommandBase<A> setEnvironment(Ui ui, TaskList currentUserList) {
        this.userList = currentUserList;
        this.ui = ui;
        return this;
    }

    /**
     * Returns the processed Userlist.
     *
     * @return TaskList reflecting the original userlist.
     */
    public TaskList getNewUserList() {
        return this.userList;
    }
}
