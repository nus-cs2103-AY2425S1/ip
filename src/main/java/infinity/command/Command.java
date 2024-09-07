package infinity.command;

import infinity.infinityexception.InfinityException;
import infinity.storage.Storage;
import infinity.task.Deadline;
import infinity.task.Event;
import infinity.task.ToDos;
import infinity.tasklist.TaskList;
import infinity.ui.Ui;

/**
 * Command class to run the various commands the user gives
 */
public class Command {

    private enum KnownCommands {
        BYE,
        DEADLINE,
        DELETE,
        EVENT,
        FIND,
        HELP,
        LIST,
        MARK,
        SAVE,
        TODO
    }

    /**
     * Compares input on whether it is equal to the lowercase of command.
     *
     * @param input User input.
     * @param command Known Command.
     * @return true if equal, false otherwise.
     */
    private static boolean doesInputEqualCommand(String input, KnownCommands command) {
        return input.equals(command.toString().toLowerCase());
    }

    /**
     * Compares input on whether it starts with the lowercase of command.
     *
     * @param input User input.
     * @param command Known Command.
     * @return true if equal, false otherwise.
     */
    private static boolean doesInputStartsWithCommand(String input, KnownCommands command) {
        return input.startsWith(command.toString().toLowerCase());
    }

    /**
     * Finds the command to execute given the user input and executes it.
     *
     * @param currentInput User input.
     * @param botTasks Tasklist of tasks.
     * @return The bot output.
     * @throws InfinityException If there are errors encountered parsing or executing commands.
     */
    public static String findCommand(String currentInput, TaskList botTasks) throws InfinityException {

        String botOutput;

        if (doesInputEqualCommand(currentInput, KnownCommands.BYE)) {

            botOutput = Ui.endBot();

        } else if (doesInputEqualCommand(currentInput, KnownCommands.LIST)) {

            botOutput = botTasks.listTasks();

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.MARK) && currentInput.length() > 5) {

            botOutput = botTasks.markTask(currentInput);
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.TODO) && currentInput.length() > 5) {

            botOutput = botTasks.addTask(new ToDos(currentInput.substring(5)));
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.DEADLINE) && currentInput.length() > 9) {

            botOutput = botTasks.addTask(new Deadline(currentInput.substring(9)));
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.EVENT) && currentInput.length() > 6) {

            botOutput = botTasks.addTask(new Event(currentInput.substring(6)));
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.DELETE) && currentInput.length() > 7) {

            botOutput = botTasks.deleteTask(currentInput.substring(7));
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.FIND) && currentInput.length() > 5) {

            botOutput = botTasks.findTasks(currentInput.substring(5));

        } else if (doesInputStartsWithCommand(currentInput, KnownCommands.SAVE)) {

            botOutput = Storage.saveFile(botTasks.getTasks());

        } else if (doesInputEqualCommand(currentInput, KnownCommands.HELP)) {

            botOutput = Ui.getCommands();

        } else {

            botOutput = Ui.invalidCommand();

        }

        assert !botOutput.isEmpty() : "Bot Output should not be empty";
        return botOutput;
    }
}
