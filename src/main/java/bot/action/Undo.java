package bot.action;

import java.util.Stack;

public class Undo {
    private final static Stack<Action> tasksToUndo = new Stack<>();

    public static void add(Action action) {
        tasksToUndo.add(action);
    }

    public static Action getLast() {
        return tasksToUndo.pop();
    }
}
