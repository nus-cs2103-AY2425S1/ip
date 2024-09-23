package yappingbot.commands.commands;

import yappingbot.commands.CommandBase;
import yappingbot.commands.Parser;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.ui.Ui;

/**
 * Marks or Unmarks a task in a task list.
 */
public class ChangeTaskListStatusCommand extends CommandBase<ChangeTaskListStatusCommand.Args,
        ChangeTaskListStatusCommand> {
    private TaskList userList;
    private Ui ui;
    private boolean isTaskDone = false;

    /**
     * Defines Argument Enum for this class.
     * Argument Enums - this class excepts no Arguments.
     *
     * @see ArgEnums for more info.
     */
    protected enum Args implements ArgEnums<Args> {
        INDEX("", true),
        ;

        private final String keyword;
        private final boolean isRequired;

        Args(String keyword, boolean isRequired) {
            this.keyword = keyword;
            this.isRequired = isRequired;
        }

        @Override
        public String getKeyword() {
            return keyword;
        }

        @Override
        public boolean isRequired() {
            return isRequired;
        }
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public ChangeTaskListStatusCommand(String[] userInputSlices)
    throws YappingBotIncorrectCommandException {
        super(userInputSlices);
    }

    @Override
    protected String getArgumentSeperator() {
        return "/";
    }

    @Override
    protected Class<Args> getArgumentClass() {
        return Args.class;
    }

    @Override
    protected Args getFirstArgumentType() {
        return Args.INDEX;
    }

    /**
     * Resets the currentUserList to default view if it is currently a filter view.
     *
     * @return this object, useful for chaining.
     */
    @Override
    protected ChangeTaskListStatusCommand run() {
        assert arguments != null;
        assert userList != null;
        assert arguments.containsKey(Args.INDEX);
        int i = Parser.parseTaskNumberSelected(arguments.get(Args.INDEX)[0]);
        assert userList.size() > i;

        Task t = userList.get(i);
        t.setTaskDone(isTaskDone);

        ui.println(isTaskDone
                   ? ReplyTextMessages.MARKED_TASK_AS_DONE_TEXT
                   : ReplyTextMessages.UNMARKED_TASK_AS_DONE_TEXT);
        ui.printf(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                  t.getTaskTypeSymbol(),
                  t.getTaskDoneCheckmark(),
                  t);
        return this;
    }

    @Override
    public String getHelpText() {
        return isTaskDone
               ? ReplyTextMessages.MARK_INSTRUCTION_USAGE
               : ReplyTextMessages.UNMARK_INSTRUCTION_USAGE;
    }

    /**
     * Set the necessary values needed to execute this command.
     *
     * @param ui Ui that will allow Input/Output.
     * @param currentUserList TaskList that needs to be resetted.
     * @param isTaskDone new {@code isTaskDone} of the task.
     * @return this object, useful for chainning.
     */
    public ChangeTaskListStatusCommand setEnvironment(Ui ui, TaskList currentUserList,
                                                      boolean isTaskDone) {
        this.userList = currentUserList;
        this.ui = ui;
        this.isTaskDone = isTaskDone;
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
