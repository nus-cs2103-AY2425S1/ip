package tars;

import tars.commands.AddCommand;
import tars.commands.DeleteCommand;
import tars.commands.FindCommand;
import tars.commands.MarkCommand;

import tars.exceptions.TarsException;

import tars.tasks.TaskList;

/**
 * Handles the generation and formatting of responses for user interactions.
 *
 * <p>The {@code Response} class provides methods to display introductory and outro messages,
 * format responses based on user commands, and handle exceptions gracefully. It leverages
 * various command classes ({@link MarkCommand}, {@link DeleteCommand}, {@link AddCommand})
 * to execute user commands and generate appropriate responses.</p>
 */
public class Response {

    MarkCommand markCommand = new MarkCommand();
    DeleteCommand deleteCommand = new DeleteCommand();
    AddCommand addCommand = new AddCommand();
    FindCommand findCommand = new FindCommand();
    private final static String LOGO = """
                ________________ __________  _________
                \\__    ___/  _  \\\\______   \\/   _____/
                  |    | /  /_\\  \\|       _/\\_____  \\
                  |    |/    |    \\    |   \\/        \\
                  |____|\\____|__  /____|_  /_______  /
                                \\/       \\/        \\/\s
                """.trim();


    /**
     * Generates and displays a response based on the user's input and the current task list.
     *
     * <p>This method determines the type of command (e.g., mark, delete, add) from the input
     * and executes the corresponding command, handling any exceptions that occur.</p>
     *
     * @param input The user's input string.
     * @param tasks The {@link TaskList} object containing the tasks to be modified or accessed.
     */
    public String generateResponse(String input, TaskList tasks) {
        if (input.contains("mark")) {
            try {
                return markCommand.execute(input, tasks);
            } catch (TarsException e) {
                return e.getMessage();
            }

        } else if (input.contains("delete")) {
            try {
                return deleteCommand.execute(input, tasks);
            } catch (TarsException e) {
                return e.getMessage();
            }

        } else if (input.contains("find")){
            try {
                return findCommand.execute(input, tasks);
            } catch (TarsException e) {
                return e.getMessage();
            }

        } else {
            try {
                return addCommand.execute(input, tasks);
            } catch (TarsException e) {
                return e.getMessage();

            }
        }

    }

    /**
     * Displays the introductory message when the TARS application starts.
     */
    public String intro() {
        String introMessage = """
                \nGreetings, human! I'm TARS, your slightly sarcastic yet highly capable companion.
                Let's get this chat started! Just remember, my humor setting is at 75%, so things might get a bit cheeky.""";

        return (LOGO + introMessage);
    }

    /**
     * Displays the outro message when the TARS application is about to exit.
     */
    public String outro() {

        return """
                Well, that's a wrap! If you need anything else, just holler.
                But let’s be honest, you’re probably better off asking someone else.""";
    }

}
