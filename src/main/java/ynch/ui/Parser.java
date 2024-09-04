/**
 * Parses String input from the user and returns ValidCommand
 *
 */

import java.util.Scanner;

/**
 * Parses user input and identifies valid commands.
 */
class Parser {

    /**
     * Processes the user input and returns the corresponding ValidCommand.
     *
     * @param userInput the input string provided by the user
     * @return the ValidCommand corresponding to the user input
     */
    ValidCommand processInput(String userInput) {
        String commandKeyword = userInput.split(" ")[0];
        switch (commandKeyword) {
            case "list":
                return ValidCommand.list;
            case "mark":
                return ValidCommand.mark;
            case "unmark":
                return ValidCommand.unmark;
            case "todo":
                return ValidCommand.todo;
            case "deadline":
                return ValidCommand.deadline;
            case "event":
                return ValidCommand.event;
            case "delete":
                return ValidCommand.delete;       
        }
        return ValidCommand.list;
    }
}
