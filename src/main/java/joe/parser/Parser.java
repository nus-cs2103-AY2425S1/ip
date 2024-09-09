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
     * @param input The input from the user
     *            .
     */
    public boolean parse(String input) {
        assert input != null : "Input is null";
        if (input.equals("bye")) {
            controller.endProgram();
            return false;
        } else if (input.contains("|")) {
            ui.printBotResponse("| is a special character and cannot be used.");
        } else if (input.equals("list")) {
            controller.handleList();
        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            controller.handleDone(index);
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            controller.handleUndone(index);
        } else if (input.startsWith("todo")) {
            String parsedTodo = input.substring(5);
            controller.handleTodo(parsedTodo);
        } else if (input.startsWith("deadline")) {
            int byIndex = input.indexOf("/by ");
            if (byIndex == -1) {
                ui.printEmptyByErrorMessage();
                return true;
            }
            String task = input.substring(9, byIndex - 1);
            String by = input.substring(byIndex + 4);
            controller.handleDeadline(task, by);
        } else if (input.startsWith("event")) {
            int fromIndex = input.indexOf("/from ");
            int toIndex = input.indexOf("/to ");
            if (fromIndex == -1 || toIndex == -1) {
                ui.printInvalidEventDateErrorMessage();
                return true;
            }
            String task = input.substring(6, fromIndex - 1);
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);
            controller.handleEvent(task, from, to);
        } else if (input.startsWith("delete")) {
            controller.handleDelete(Integer.parseInt(input.split(" ")[1]) - 1);
        } else if (input.equals("help")) {
            controller.handleHelp();
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            controller.handleFind(keyword);
        } else {
            ui.printBotResponse("Give me a valid command!");
        }
        return true;
    }
}
