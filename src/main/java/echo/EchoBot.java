package echo;

import exceptions.DukeException;
import commands.Parser;
import storage.Storage;
import tasks.TaskList;

/**
 * Represents a simple task management bot that interacts with the user via the console.
 * It handles task storage, user input, and displays task-related information.
 */
public class EchoBot {
    private Ui ui = new Ui();
    private TaskList allTasks;

    /**
     * Creates an echobot object and sends greet message to the user.
     */
    public EchoBot() {
        Storage.init();
        this.allTasks = Storage.getData();
        this.ui.greet();
    }

    /**
     * Responds to user input by processing the command and returning the bot's response.
     *
     * @param input The user's input command.
     * @return The bot's response to the user's command.
     */
    public String getResponse(String input) {
        this.allTasks = Storage.getData();
        String userOutput = run(input, this.allTasks);
        return userOutput;
    }

    /**
     * Executes the user's command by parsing the input, updating the task list, and returning output.
     *
     * @param userInput The user's input command.
     * @param allTasks The current list of tasks managed by the bot.
     * @return The bot's output after processing the user's command.
     */
    public static String run(String userInput, TaskList allTasks) {
        String botOutput = "";
        try {
            botOutput = Parser.parse(userInput, allTasks);
            Storage.setData(allTasks);
        } catch (DukeException e) {
            botOutput = e.getMessage();
        } finally {
            return botOutput;
        }
    }
}
