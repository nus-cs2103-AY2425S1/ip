package joe.parser;

import joe.controller.Controller;
import joe.ui.Ui;
import static joe.Constants.EXIT_COMMAND;
import static joe.Constants.LIST_COMMAND;
import static joe.Constants.POSTPONE_COMMAND;
import static joe.Constants.TASK_MARK_COMPLETE_COMMAND;
import static joe.Constants.TASK_UNMARK_COMPLETE_COMMAND;
import static joe.Constants.TASK_DELETE_COMMAND;
import static joe.Constants.TASK_TODO_COMMAND;
import static joe.Constants.TASK_DEADLINE_COMMAND;
import static joe.Constants.TASK_EVENT_COMMAND;
import static joe.Constants.HELP_COMMAND;
import static joe.Constants.BY_COMMAND;
import static joe.Constants.FROM_COMMAND;
import static joe.Constants.TO_COMMAND;
import static joe.Constants.FIND_COMMAND;
import static joe.Constants.TASK_TODO_COMMAND_LENGTH;
import static joe.Constants.TASK_DEADLINE_COMMAND_LENGTH;
import static joe.Constants.TASK_EVENT_COMMAND_LENGTH;
import static joe.Constants.BY_COMMAND_LENGTH;
import static joe.Constants.FROM_COMMAND_LENGTH;
import static joe.Constants.TO_COMMAND_LENGTH;
import static joe.Constants.FIND_COMMAND_LENGTH;

/**
 * Parses the input from the user and calls the appropriate controller method.
 */
public class Parser<C extends Controller> {

    private final C controller;
    private final Ui ui;

    public Parser(C controller, Ui ui) {
        this.controller = controller;
        this.ui = ui;
    }

    /**
     * Parses the input and calls the appropriate controller method.
     * 
     * @param input The input from the user
     *            .
     */
    public boolean parse(String input) {
        assert input != null : "Input is null";
        if (input.equals(EXIT_COMMAND)) {
            controller.endProgram();
            return false;
        } else if (input.contains("|")) {
            ui.printReservedCharacterErrorMessage();
        } else if (input.equals(LIST_COMMAND)) {
            controller.handleList();
        } else if (input.startsWith(TASK_MARK_COMPLETE_COMMAND)) {
            controller.handleDone(getCommandIndex(input));
        } else if (input.startsWith(TASK_UNMARK_COMPLETE_COMMAND)) {
            controller.handleUndone(getCommandIndex(input));
        } else if (input.startsWith(TASK_DELETE_COMMAND)) {
            controller.handleDelete(getCommandIndex(input));
        } else if (input.startsWith(TASK_TODO_COMMAND)) {
            handleTodo(input);
        } else if (input.startsWith(TASK_DEADLINE_COMMAND)) {
            handleDeadline(input);
        } else if (input.startsWith(TASK_EVENT_COMMAND)) {
            handleEvent(input);
        } else if (input.equals(HELP_COMMAND)) {
            controller.handleHelp();
        } else if (input.startsWith(FIND_COMMAND)) {
            handleFind(input);
        } else if (input.startsWith(POSTPONE_COMMAND)) {
            handlePostpone(input);
        } else {
            ui.printInvalidCommandErrorMessage();
        }
        return true;
    }

    private int getCommandIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private void handleTodo(String input) {
        String parsedTodo = input.substring(TASK_TODO_COMMAND_LENGTH).strip();
        controller.handleTodo(parsedTodo);
    }

    private void handleDeadline(String input) {
        int byIndex = input.indexOf(BY_COMMAND);
        if (byIndex == -1) {
            ui.printEmptyByErrorMessage();
            return;
        }
        String task = input.substring(TASK_DEADLINE_COMMAND_LENGTH, byIndex - 1).strip();
        String by = input.substring(byIndex + BY_COMMAND_LENGTH).strip();
        controller.handleDeadline(task, by);
    }

    private void handleEvent(String input) {
        int fromIndex = input.indexOf(FROM_COMMAND);
        int toIndex = input.indexOf(TO_COMMAND);
        if (fromIndex == -1 || toIndex == -1) {
            ui.printInvalidEventDateErrorMessage();
            return;
        }
        String task = input.substring(TASK_EVENT_COMMAND_LENGTH, fromIndex - 1).strip();
        String from = input.substring(fromIndex + FROM_COMMAND_LENGTH, toIndex - 1).strip();
        String to = input.substring(toIndex + TO_COMMAND_LENGTH).strip();
        controller.handleEvent(task, from, to);
    }

    private void handleFind(String input) {
        String keyword = input.substring(FIND_COMMAND_LENGTH).strip();
        controller.handleFind(keyword);
    }

    private void handlePostpone(String input) {
        int taskIndex = getCommandIndex(input);
        String daysString = input.split(" ")[2];
        int days = Integer.parseInt(daysString);
        controller.handlePostpone(taskIndex, days);
    }
}
