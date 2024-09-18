package tina;

/**
 * The <code>Ui</code> class handles user interaction, including greeting the user,
 * processing commands, and exiting the application.
 */
public class Ui {

    /**
     * Processes the user's input by parsing it and executing the corresponding command.
     * If the input cannot be processed due to a TinaException, the method returns the
     * error message from the exception.
     *
     * @param tasks The TaskList object that contains the current list of tasks. This should not be null.
     * @param input The user's input string to be parsed and executed. This should not be null.
     * @return A string representing the result of the command or an error message if a TinaException occurs.
     */
    public String runInput(TaskList tasks, String input) {
        try {
            return Parser.parseInput(input, tasks);
        } catch (TinaException e) {
            return e.getMessage();
        }
    }

}
