package commands;


import exceptions.EchoException;
import tasks.Task;
import tasks.TaskList;

/**
 * The Parser class is responsible for interpreting user commands
 * and performing the corresponding actions on the task list.
 */
public class Parser {

    /**
     * Parses the user input and performs the corresponding action on the task list.
     *
     * @param userInput the input command from the user.
     * @param allTasks the task list to be manipulated based on the command.
     * @return A string response based on the command execution.
     */
    public static String parse(String userInput, TaskList allTasks) throws EchoException {
        // parse the command
        String[] commandArray = userInput.split(" ", 2);
        assert commandArray.length <= 2 : "Command array should be at most 2 in length";
        String command = commandArray[0].toUpperCase();

        try {
            switch (Command.valueOf(command)) {
            case BYE:
                return ChatCommand.bye();
            case HI:
                return ChatCommand.greet();
            case LIST:
                return ListCommand.run(allTasks);
            case MARK:
                return MarkCommand.run(commandArray, allTasks);
            case UNMARK:
                return UnmarkCommand.run(commandArray, allTasks);
            case TAG:
                return TagCommand.run(commandArray, allTasks);
            case DELETE:
                return DeleteCommand.run(commandArray, allTasks);
            case FIND:
                return FindCommand.run(commandArray, allTasks);
            case HELP:
                return HelpCommand.getHelp(userInput);
            default:
                Task task = Task.createTask(userInput);
                return allTasks.add(task);
            }
        } catch (ClassCastException e) {
            throw new EchoException("Input Error: " + "\n" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new EchoException("Oops! You enter an invalid command.");
        } catch (AssertionError e) {
            throw new EchoException(e.getMessage());
        }
    }
}
