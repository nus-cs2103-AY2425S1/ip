package Johnson.GUI;

import Johnson.command.Command;
import Johnson.command.ExitCommand;
import Johnson.exceptions.*;
import Johnson.parser.Parser;
import Johnson.storage.UserData;
import Johnson.task.TaskList;
import Johnson.utils.Utilities;

import java.util.Scanner;

/**
 * Represents the main class of the PHamBot application.
 */
public class Johnson {
    private static TaskList tasks;

    private Parser parser;

    private UserData data;
    private static final String[] UserGreetings = {"Hello", "Hi", "What's up"};

    private static final String GREETING = "Boots on the line, ready to assist!";

    private static final String GOODBYE = "Signing off for now, Chief.\nRadio in when you need me.";

    public Johnson() {
        data = new UserData();
        tasks = data.getTasks();
        Command.setUserData(tasks);
        parser = new Parser();
    }

    public static String greet() {
        Utilities.OutlineMessage(GREETING);
        return GREETING;
    }

    public static String sayGoodbye() {
        Utilities.OutlineMessage(GOODBYE);
        return GOODBYE;
    }

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
        }


    }
}
