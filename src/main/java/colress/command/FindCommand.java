package colress.command;

import colress.Parser;
import colress.TaskList;
import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.EmptyInputException;

/**
 * Represents the find command which prints a list of tasks that contain a specified keyword
 */
public final class FindCommand extends ListCommand {
    public static final String MESSAGE_INVALID_FORMAT = "What is this?! I do not recognise that command format!"
            + "Here's the correct format: find KEYWORD";
    public static final int EXPECTED_ARG_NUMBER = 1;
    private String keyword;

    public FindCommand() {
        super();
    }

    public FindCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        return ui.promptKeyword(taskList);
    }

    @Override
    public void initialise(String input) {
        this.keyword = input.toLowerCase();
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
    public String execute(UiAdvanced ui, TaskList taskList) {
        String[] args = getArguments();
        checkNumberOfArgs(args, EXPECTED_ARG_NUMBER, MESSAGE_INVALID_FORMAT);
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
}
