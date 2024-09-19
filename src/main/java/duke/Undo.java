package duke;

import java.util.Objects;

/**
 * Undoes the previous command from user.
 */
public class Undo {
    private static Task task;
    private static String previousCommand;

    private static final int PARTS_COMMAND = 0;
    private static final int PARTS_INDEX = 1;

    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static final String REPLY_NO_PREVIOUS_COMMAND = "There is no previous command to undo.";

    Undo() {
        task = null;
        previousCommand = null;
    }

    /**
     * Parses through previous command to reverse it.
     *
     * @return String reply to inform user of successful undoing of previous command.
     * */
    public static String undo() {
        if (hasNoPreviousCommand()) {
            return REPLY_NO_PREVIOUS_COMMAND;
        }
        String[] parts = previousCommand.split(" \\| ");
        String command = parts[PARTS_COMMAND];

        String reply = "";

        if (Objects.equals(command, COMMAND_MARK)) {
            reply = TaskList.unmark(Integer.parseInt(parts[PARTS_INDEX]));

        } else if (Objects.equals(command, COMMAND_UNMARK)) {
            reply = TaskList.mark(Integer.parseInt(parts[PARTS_INDEX]));

        } else if ((Objects.equals(command, COMMAND_TODO)) ||
                (Objects.equals(command, COMMAND_DEADLINE)) || (Objects.equals(command, COMMAND_EVENT))) {
            reply = TaskList.delete(Integer.parseInt(parts[PARTS_INDEX]));

        } else if (Objects.equals(command, COMMAND_DELETE)) {
            reply = TaskList.add(task);

        }

        previousCommand = null;
        task = null;

        return reply;
    }

    /**
     * Saves the previous command.
     *
     * @param command String command to be saved.
     * @param index Integer index of relevant task in the task list.
     * */
    public static void saveCommand(String command, int index) {
        previousCommand = command + " | " + index;
    }

    /**
     * Saves previous task, if it was deleted.
     *
     * @param previousTask Task item that was deleted.
     * */
    public static void saveTask(Task previousTask) {
        task = previousTask;
    }

    /**
     * Checks if there exists a previous command to undo.
     *
     * @return Boolean verifying if there exists a previous command to be undone.
     * */
    public static boolean hasNoPreviousCommand() {
        return Objects.equals(previousCommand, null);
    }
}
