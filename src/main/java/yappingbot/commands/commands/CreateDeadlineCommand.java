package yappingbot.commands.commands;

import yappingbot.commands.CreateTaskCommandBase;
import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Deadline;
import yappingbot.tasks.Task;
import yappingbot.tasks.Todo;

/**
 * Creates deadline task.
 */
public class CreateDeadlineCommand extends CreateTaskCommandBase<CreateDeadlineCommand.Args> {

    protected enum Args implements ArgEnums<Args> {
        TASK_NAME("", true),
        DEADLINE("/by", true);

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
     * Subclasses must override this, linking it to an Enum of possible Argument Types. Usually
     * <i>return A.class</i>.
     *
     * @return The Class of th Enums defined in the subclass holding the possible Argument Types.
     */
    @Override
    protected Class<Args> getArgumentClass() {
        return Args.class;
    }

    /**
     * Subclasses must override this, linking it to the value in the Enum referring to the first
     * Argument (unmarked argument).
     *
     * @return The Enum defined in the subclass holding the possible Argument Types.
     */
    @Override
    protected Args getFirstArgumentType() {
        return Args.TASK_NAME;
    }

    /**
     * Constructs Command object with arguments to prepare for execution.
     *
     * @param argSlices ordered array of strings with argument flags followed by argument values.
     * @throws YappingBotIncorrectCommandException Exception thrown when there is an unknown argument
     *                                             flag given.
     */
    public CreateDeadlineCommand(String[] argSlices) throws YappingBotIncorrectCommandException {
        super(argSlices);
    }

    /**
     * Returns the usage help text, helpful for guiding user on correct usage.
     *
     * @return String of help text.
     */
    @Override
    public String getHelpText() {
        return ReplyTextMessages.DEADLINE_USAGE;
    }

    /**
     * Creates the task based on each subclass's implementation of its Task.
     *
     * @return Task that is newly created
     */
    @Override
    protected Task createNewTask() {
        assert arguments.containsKey(Args.TASK_NAME) && arguments.containsKey(Args.DEADLINE);
        return new Deadline(getArgValueJoined(Args.TASK_NAME),
                            false,
                            getArgValueJoined(Args.DEADLINE));
    }
}
