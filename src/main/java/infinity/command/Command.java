package infinity.command;

import infinity.infinityexception.InfinityException;
import infinity.parser.Parser;
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

    /**
     * Commands that the bot knows and can use as enum.
     */
    public enum KnownCommands {
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
     * @param input User input.
     * @param botTasks Tasklist of tasks.
     * @return The bot output.
     * @throws InfinityException If there are errors encountered parsing or executing commands.
     */
    public static String findCommand(String input, TaskList botTasks) throws InfinityException {

        String botOutput;

        if (doesInputEqualCommand(input, KnownCommands.BYE)) {

            botOutput = Ui.endBot();

        } else if (doesInputEqualCommand(input, KnownCommands.LIST)) {

            botOutput = botTasks.listTasks();

        } else if (doesInputStartsWithCommand(input, KnownCommands.MARK)) {

            botOutput = Parser.parseAndRun(
                    botTasks::markTask,
                    input,
                    KnownCommands.MARK);
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(input, KnownCommands.TODO)) {

            botOutput = Parser.parseAndRun((String eachInput) -> botTasks.addTask(new ToDos(eachInput)),
                                            input,
                                            KnownCommands.TODO);
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(input, KnownCommands.DEADLINE)) {

            botOutput = Parser.parseAndRun((String eachInput) -> botTasks.addTask(new Deadline(eachInput)),
                                            input,
                                            KnownCommands.DEADLINE);
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(input, KnownCommands.EVENT)) {

            botOutput = Parser.parseAndRun((String eachInput) -> botTasks.addTask(new Event(eachInput)),
                                            input,
                                            KnownCommands.EVENT);
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(input, KnownCommands.DELETE)) {

            botOutput = Parser.parseAndRun(
                    botTasks::deleteTask,
                    input,
                    KnownCommands.DELETE);
            Storage.saveFile(botTasks.getTasks());

        } else if (doesInputStartsWithCommand(input, KnownCommands.FIND)) {

            botOutput = Parser.parseAndRun(
                    botTasks::findTasks,
                    input,
                    KnownCommands.FIND);

        } else if (doesInputStartsWithCommand(input, KnownCommands.SAVE)) {

            botOutput = Storage.saveFile(botTasks.getTasks());

        } else if (doesInputEqualCommand(input, KnownCommands.HELP)) {

            botOutput = Ui.getCommands();

        } else {

            botOutput = Ui.invalidCommand();

        }

        assert !botOutput.isEmpty() : "Bot Output should not be empty";
        return botOutput;
    }
}
