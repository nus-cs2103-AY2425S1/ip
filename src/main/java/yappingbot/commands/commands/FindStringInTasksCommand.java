package yappingbot.commands.commands;

import yappingbot.commands.CommandBase;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskListFilterView;
import yappingbot.ui.Ui;

/**
 * Returns a TaskList that is either the same userList given should there be no results found,
 * or a new TaskListFilterView that will act as a TaskList but updates the parent TaskList
 * that was passed into.
 */
public class FindStringInTasksCommand
        extends CommandBase<FindStringInTasksCommand.Args, FindStringInTasksCommand> {

    private TaskList userList;
    private Ui ui;

    /**
     * Defines Argument Enum for this class.
     *
     * @see ArgEnums for more info.
     */
    protected enum Args implements ArgEnums<Args> {
        SEARCH_STRING("", true);

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
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown
     *                                             argument
     */
    public FindStringInTasksCommand(String[] argSlices) throws YappingBotIncorrectCommandException {
        super(argSlices);
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
        return Args.SEARCH_STRING;
    }

    @Override
    protected FindStringInTasksCommand run() {
        assert userList != null;
        assert arguments != null;
        assert arguments.containsKey(Args.SEARCH_STRING);

        String searchString = String.join(" ", arguments.get(Args.SEARCH_STRING));

        StringBuilder sb = new StringBuilder();
        String searchStringSanitized = searchString.replaceAll("\n", "");
        sb.append(String.format(ReplyTextMessages.FIND_STRING_INIT_1s, searchStringSanitized));
        TaskList newFilteredView = TaskListFilterView.createFilter(userList, searchString);
        sb.append("\n");
        if (newFilteredView.isEmpty()) {
            sb.append(String.format(ReplyTextMessages.FIND_STRING_FAIL_1s, searchString));
            ui.print(sb.toString());
        } else {
            sb.append(String.format(
                    ReplyTextMessages.FIND_STRING_FOUND_1d_1s,
                    newFilteredView.size(),
                    searchString));
            ui.print(sb.toString());
            printUserList(newFilteredView);
            this.userList = newFilteredView;
        }
        return this;
    }

    private void printUserList(TaskList newFilteredView) {
        new PrintUserTaskListCommand().setEnvironment(ui, newFilteredView).run();
    }

    @Override
    public String getHelpText() {
        return ReplyTextMessages.FIND_USAGE;
    }

    /**
     * Set the necessary values needed to execute this command.
     *
     * @param ui Ui object to interface.
     * @param userList TaskList to search in.
     * @return this object, useful for chainning
     */
    public FindStringInTasksCommand setEnvironmen(Ui ui, TaskList userList) {
        this.userList = userList;
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
