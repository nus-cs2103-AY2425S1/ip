package bobbybot.commands;

import bobbybot.TaskList;

/**
 * Represents a memento for commands.
 */
public class Memento {
    private final TaskList state;

    /**
     * Creates a memento with the given state.
     *
     * @param state The state to save.
     */
    public Memento(TaskList state) {
        this.state = new TaskList();
        this.state.copyOver(state);
    }

    public TaskList getState() {
        return state;
    }
}
