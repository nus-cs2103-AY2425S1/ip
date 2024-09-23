package yappingbot.commands.commands;

import yappingbot.commands.CommandBase;
import yappingbot.commands.Parser;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.ui.Ui;

/**
 * Deletes a task in the user list, and prints that task out.
 */
public class DeleteTaskCommand extends CommandBase<DeleteTaskCommand.Args, DeleteTaskCommand> {
    private TaskList userList;
    private Ui ui;

    /**
     * Defines Argument Enum for this class.
     * This class accepts no arguments.
     *
     * @see ArgEnums for more info.
     */
    protected enum Args implements ArgEnums<Args> {
        INDEX("", true);

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
    public DeleteTaskCommand(String[] userInputSlices) throws YappingBotIncorrectCommandException {
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
    protected DeleteTaskCommand run() {
        assert arguments != null;
        assert userList != null;
        assert arguments.containsKey(Args.INDEX);
        int i = Parser.parseTaskNumberSelected(arguments.get(Args.INDEX)[0]);
        assert userList.size() > i;

        Task t = userList.deleteTask(i);
        ui.println(ReplyTextMessages.DELETED_TEXT);
        ui.printf(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                  t.getTaskTypeSymbol(),
                  t.getTaskDoneCheckmark(),
                  t);
        ui.printf(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size());
        return this;
    }

    @Override
    public String getHelpText() {
        return ReplyTextMessages.RESET_USAGE;
    }

    /**
     * Set the necessary values needed to execute this command.
     *
     * @param currentUserList TaskList that needs to be resetted
     * @return this object, useful for chainning
     */
    public DeleteTaskCommand setEnvironment(Ui ui, TaskList currentUserList) {
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
