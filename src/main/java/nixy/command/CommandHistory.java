package nixy.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * CommandHistory class represents the history of commands that the user had input.
 */
public class CommandHistory {
    private static final int MAX_SIZE = 3;
    private Deque<UndoableCommand> history;

    public CommandHistory() {
        history = new ArrayDeque<>();
    }

    /**
     * Adds a command to the history.
     *
     * @param command The command to add.
     */
    public void add(UndoableCommand command) {
        if (history.size() >= MAX_SIZE) {
            history.pollFirst();
        }
        history.addLast(command);
    }

    /**
     * Removes and returns the most recent command from the history.
     *
     * @return The most recent command.
     */
    public UndoableCommand pop() {
        return history.pollLast();
    }
}
