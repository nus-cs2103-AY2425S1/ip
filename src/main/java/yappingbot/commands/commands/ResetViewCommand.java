package yappingbot.commands.commands;

import yappingbot.commands.CommandBase;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskListFilterView;
import yappingbot.ui.Ui;

/**
 * Resets the currentUserList to default view if it is currently a filter view.
 */
public class ResetViewCommand extends CommandBase<ResetViewCommand.Args, ResetViewCommand> {
    private TaskList currentUserList;
    private boolean silent;
    private Ui ui;

    /**
     * Defines Argument Enum for this class.
     * This class accepts no argument.
     *
     * @see ArgEnums for more info.
     */
    protected enum Args implements ArgEnums<Args> {
        ;

        @Override
        public String getKeyword() {
            return "";
        }

        @Override
        public boolean isRequired() {
            return false;
        }
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument flag given.
     */
    public ResetViewCommand() throws YappingBotIncorrectCommandException {
        super(new String[]{});
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
        return null;
    }

    /**
     * Resets the currentUserList to default view if it is currently a filter view.
     *
     * @return this object, useful for chaining.
     */
    @Override
    protected ResetViewCommand run() {
        assert currentUserList != null;
        // reset the view to main parent
        TaskList userList = currentUserList;
        while (userList instanceof TaskListFilterView) {
            userList = ((TaskListFilterView) userList).getParent();
        }
        if (!silent) {
            ui.println(ReplyTextMessages.RESET_TEXT);
        }
        currentUserList = userList;
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
     * @param silent boolean on whether to run this silently
     * @return this object, useful for chainning
     */
    public ResetViewCommand setEnvironment(Ui ui, TaskList currentUserList, boolean silent) {
        this.currentUserList = currentUserList;
        this.silent = silent;
        this.ui = ui;
        return this;
    }

    /**
     * Returns the processed Userlist.
     *
     * @return TaskList reflecting the original userlist.
     */
    public TaskList getNewUserList() {
        return this.currentUserList;
    }
}
