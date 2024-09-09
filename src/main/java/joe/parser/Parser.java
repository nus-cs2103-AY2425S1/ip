package joe.parser;

import joe.controller.Controller;
import joe.ui.Ui;

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
     * @param input
     *            The input from the user.
     */
    public boolean parse(String input) {
        if (input.equals("bye")) {
            controller.endProgram();
            return false;
        } else if (input.contains("|")) {
            ui.printReservedCharacterErrorMessage();
        } else if (input.equals("list")) {
            controller.handleList();
        } else if (input.startsWith("mark")) {
            controller.handleDone(getCommandIndex(input));
        } else if (input.startsWith("unmark")) {
            controller.handleUndone(getCommandIndex(input));
        } else if (input.startsWith("delete")) {
            controller.handleDelete(getCommandIndex(input));
        } else if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else if (input.equals("help")) {
            controller.handleHelp();
        } else if (input.startsWith("find")) {
            handleFind(input);
        } else {
            ui.printInvalidCommandErrorMessage();
        }
        return true;
    }

    public int getCommandIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    public void handleTodo(String input) {
        String parsedTodo = input.substring(5);
        controller.handleTodo(parsedTodo);
    }

    public void handleDeadline(String input) {
        int byIndex = input.indexOf("/by ");
        if (byIndex == -1) {
            ui.printEmptyByErrorMessage();
            return;
        }
        String task = input.substring(9, byIndex - 1);
        String by = input.substring(byIndex + 4);
        controller.handleDeadline(task, by);
    }

    public void handleEvent(String input) {
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1) {
            ui.printInvalidEventDateErrorMessage();
            return;
        }
        String task = input.substring(6, fromIndex - 1);
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        controller.handleEvent(task, from, to);
    }

    public void handleFind(String input) {
        String keyword = input.substring(5);
        controller.handleFind(keyword);
    }
}
