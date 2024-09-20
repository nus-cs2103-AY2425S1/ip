package bot.action;

import java.util.Stack;

/**
 * Represents a list of actions to potentially undo.
 *
 * @author mongj
 */
public class Undo {
    private static final Stack<Action> tasksToUndo = new Stack<>();

    /**
     * Adds an action to the undo stack.
     *
     * @param action that will undo the last change.
     */
    public static void add(Action action) {
        tasksToUndo.add(action);
    }

    /**
     * Gets the last action from the undo stack.
     *
     * @return an action that when executed undoes the last change.
     */
    public static Action getLast() {
        return tasksToUndo.pop();
    }
}
