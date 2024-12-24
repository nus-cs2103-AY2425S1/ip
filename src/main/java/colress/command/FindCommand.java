package colress.command;

import static colress.Ui.MESSAGE_LIST_EMPTY;

import java.util.Arrays;
import java.util.Objects;

import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.EmptyInputException;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;

/**
 * Represents the find command which prints a list of tasks that contain a specified keyword
 */
public final class FindCommand extends ListCommand {
    public static final String COMMAND_FORMAT = "find KEYWORD";
    public static final int EXPECTED_ARG_NUMBER = 1;
    private String keyword;

    public FindCommand() {
        super();
    }

    public FindCommand(String[] arguments) {
        super(arguments);
    }

    /**
     * Constructs a FindCommand with the given fields.
     */
    public FindCommand(String[] arguments, String keyword) {
        super(arguments);
        this.keyword = keyword;
    }

    @Override
    public void initialise(String input) {
        this.keyword = input.toLowerCase();
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        return ui.promptKeyword(taskList);
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object that contain a specified keyword,
     * using the provided Ui object to receive date input from the user and to print the output for the user.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        return ui.printTasks(taskList, keyword);
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }

        String[] args = getArguments();
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, COMMAND_FORMAT);
        try {
            ui.parseKeyword(args[0]);
            return ui.printTasks(taskList, keyword);
        } catch (EmptyInputException e) {
            ui.setCommandType("error");
            return e.getMessage();
        }
    }

    @Override
    public String toString() {
        return Parser.COMMAND_FIND;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return Arrays.equals(getArguments(), otherFindCommand.getArguments())
                && Objects.equals(keyword, otherFindCommand.keyword);
    }
}
