package Johnson.GUI;

import Johnson.command.Command;

import Johnson.exceptions.MissingDateException;
import Johnson.exceptions.MissingDividerException;
import Johnson.exceptions.MissingTaskException;
import Johnson.exceptions.UnknownCommandException;

import Johnson.parser.Parser;
import Johnson.storage.UserData;
import Johnson.task.TaskList;
import Johnson.utils.Utilities;

import java.io.FileNotFoundException;

/**
 * Represents the main class of the Johnson application.
 */
public class Johnson {
    private static TaskList tasks;

    private Parser parser;

    private UserData data;

    /**
     * The greeting message displayed when the application is started.
     */
    private static final String GREETING = "Boots on the line, ready to assist!";

    public Johnson() {
        data = new UserData();
        tasks = data.getTasks();
        Command.setTaskList(tasks);
        parser = new Parser();
    }

    public static String greet() {
        Utilities.OutlineMessage(GREETING);
        return GREETING;
    }

    /**
     * Gets the response from the application based on the user input.
     *
     * @param input the user input.
     * @return the response from the application.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);

            if (command != null) {
                data.setTasks(tasks);
                data.saveTasks();
                return command.executeCommand();
            }
            return "Did you say something, Chief?";
        } catch (MissingDividerException e) {
            return MissingDividerException.MISSING_DIVIDER_MESSAGE;
        } catch (MissingDateException e) {
            return MissingDateException.MISSING_DATE_MESSAGE;
        } catch (MissingTaskException e) {
            return MissingTaskException.MISSING_TASK_MESSAGE;
        } catch (UnknownCommandException e) {
            return UnknownCommandException.UNKNOWN_COMMAND_MESSAGE;
        } catch (FileNotFoundException e) {
            return "We're gonna need a clear LZ for this one, Chief. \n" +
                    "Storage file not found.";
        }
    }
}
