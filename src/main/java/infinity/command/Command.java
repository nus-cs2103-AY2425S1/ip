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
     * Finds the command to execute given the user input and executes it.
     *
     * @param currentInput User input.
     * @param botTasks Tasklist of tasks.
     * @return The bot output.
     * @throws InfinityException If there are errors encountered parsing or executing commands.
     */
    public static String findCommand(String currentInput, TaskList botTasks) throws InfinityException {

        String botOutput;

        if (currentInput.equals(KnownCommands.BYE.toString().toLowerCase())) {

            botOutput = Ui.endBot();

        } else if (currentInput.equals(KnownCommands.LIST.toString().toLowerCase())) {

            botOutput = botTasks.listTasks();

        } else if (currentInput.startsWith(KnownCommands.MARK.toString().toLowerCase())
                && currentInput.length() > 5) {

            botOutput = botTasks.markTask(currentInput);
            Storage.saveFile(botTasks.getTasks());

        } else if (currentInput.startsWith(KnownCommands.TODO.toString().toLowerCase())
                && currentInput.length() > 5) {

            botOutput = botTasks.addTask(new ToDos(currentInput.substring(5)));
            Storage.saveFile(botTasks.getTasks());

        } else if (currentInput.startsWith(KnownCommands.DEADLINE.toString().toLowerCase())
                && currentInput.length() > 9) {

            botOutput = botTasks.addTask(new Deadline(currentInput.substring(9)));
            Storage.saveFile(botTasks.getTasks());

        } else if (currentInput.startsWith(KnownCommands.EVENT.toString().toLowerCase())
                && currentInput.length() > 6) {

            botOutput = botTasks.addTask(new Event(currentInput.substring(6)));
            Storage.saveFile(botTasks.getTasks());

        } else if (currentInput.startsWith(KnownCommands.DELETE.toString().toLowerCase())
                && currentInput.length() > 7) {

            botOutput = botTasks.deleteTask(currentInput.substring(7));
            Storage.saveFile(botTasks.getTasks());

        } else if (currentInput.startsWith(KnownCommands.FIND.toString().toLowerCase())
                && currentInput.length() > 5) {

            botOutput = botTasks.findTasks(currentInput.substring(5));

        } else if (currentInput.startsWith(KnownCommands.SAVE.toString().toLowerCase())) {

            botOutput = Storage.saveFile(botTasks.getTasks());

        } else if (currentInput.equals(KnownCommands.HELP.toString().toLowerCase())) {

            botOutput = Ui.getCommands();

        } else {

            botOutput = Ui.invalidCommand();

        }

        assert !botOutput.isEmpty() : "Bot Output should not be empty";
        return botOutput;
    }
}
