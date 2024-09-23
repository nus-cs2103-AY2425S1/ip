package yappingbot.commands.commands;

import yappingbot.commands.CommandBase;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskListFilterView;
import yappingbot.ui.Ui;

/**
 * Prints the contents of the task list in a human-friendly format.
 * Example: [T][X] Marked To-do task
 */
public class PrintUserTaskListCommand
        extends CommandBase<PrintUserTaskListCommand.Args, PrintUserTaskListCommand> {

    private TaskList userList;
    private Ui ui;

    /**
     * Defines Argument Enum for this class.
     * Argument Enums - this class excepts no Arguments.
     *
     * @see ArgEnums for more info.
     */
    protected enum Args implements ArgEnums<PrintUserTaskListCommand.Args> {
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
    public PrintUserTaskListCommand() throws YappingBotIncorrectCommandException {
        super(new String[]{});
    }

    @Override
    protected String getArgumentSeperator() {
        return "/";
    }

    @Override
    protected Class<PrintUserTaskListCommand.Args> getArgumentClass() {
        return Args.class;
    }

    @Override
    protected PrintUserTaskListCommand.Args getFirstArgumentType() {
        return null;
    }

    /**
     * Resets the userList to default view if it is currently a filter view.
     *
     * @return this object, useful for chaining.
     */
    @Override
    protected PrintUserTaskListCommand run() {
        assert userList != null;
        if (userList.isEmpty()) {
            ui.println("List is empty!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ReplyTextMessages.LIST_TEXT);
        if (userList instanceof TaskListFilterView) {
            sb.append(String.format("\n(Filter: '%s')",
                                    ((TaskListFilterView) userList).getFilterString()));
        }
        sb.append("\n");
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            sb.append(
                    String.format(
                            "\n%2d.%s",
                            i + 1,
                            String.format(
                                    ReplyTextMessages.TASK_PRINT_TEXT_3s,
                                    t.getTaskTypeSymbol(),
                                    t.getTaskDoneCheckmark(),
                                    t
                            )
                    )
            );
        }
        ui.print(sb.toString());
        return this;
    }

    @Override
    public String getHelpText() {
        return ReplyTextMessages.RESET_USAGE;
    }

    /**
     * Set the necessary values needed to execute this command.
     *
     * @param ui Ui interface used to print output to user
     * @param userList TaskList that needs to be resetted
     * @return this object, useful for chainning
     */
    public PrintUserTaskListCommand setEnvironment(Ui ui, TaskList userList) {
        this.userList = userList;
        this.ui = ui;
        return this;
    }
}
