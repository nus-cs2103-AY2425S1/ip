package ynch.ui;

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
        return switch (commandKeyword) {
            case "list" -> ValidCommand.list;
            case "mark" -> ValidCommand.mark;
            case "unmark" -> ValidCommand.unmark;
            case "todo" -> ValidCommand.todo;
            case "deadline" -> ValidCommand.deadline;
            case "event" -> ValidCommand.event;
            case "delete" -> ValidCommand.delete;
            case "find" -> ValidCommand.find;
            default -> ValidCommand.list;
        };
    }
}
