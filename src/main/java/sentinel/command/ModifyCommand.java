package sentinel.command;

import sentinel.Sentinel;
import sentinel.task.Task;
import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;
import sentinel.utils.SentinelString;

/**
 * The ModifyCommand class is responsible for modifying tasks in the list.
 * Depending on the command type, it can mark tasks as done, unmark them, or delete them.
 */
public class ModifyCommand extends Command {
    private final Sentinel.CommandType commandType;

    /**
     * Constructs a ModifyCommand with the specified user interface, task list, and command type.
     *
     * @param ui         The user interface object for displaying messages.
     * @param list       The list of tasks managed by the application.
     * @param commandType The type of modification command (mark, unmark, delete).
     */
    public ModifyCommand(Ui ui, SentinelList list, Sentinel.CommandType commandType) {
        super(ui, list);
        this.commandType = commandType;
    }

    /**
     * Executes the modify command, which performs the action specified by the command type
     * (mark, unmark, or delete) on the task at the given index.
     *
     * @param input The user's input string containing the task index.
     */
    @Override
    public String execute(String input) {
        int num;
        try {
            num = Parser.parseIndex(input);
        } catch (Exception e) {
            ui.showError(e);
            return e.getMessage();
        }
        if (num > list.size()) {
            ui.showNoItemExists();
            return SentinelString.stringNoItemExists();
        } else if (num <= 0) {
            ui.showModifyGuidelines();
            return SentinelString.stringModifyGuidelines();
        }
        switch (commandType) {
        case delete -> {
            Task removed = list.remove(num - 1);
            ui.showRemovedAndRemaining(list, removed);
            return SentinelString.stringRemovedAndRemaining(list, removed);
        }
        case mark -> {
            return toggleTaskMark(num - 1, true);
        }
        case unmark -> {
            return toggleTaskMark(num - 1, false);
        }
        default -> throw new IllegalArgumentException("Unknown command");
        }
    }

    /**
     * Toggles the mark status of a task at the specified index.
     * If the task is already marked/unmarked, it displays an appropriate message.
     *
     * @param index The index of the task to be marked or unmarked.
     * @param mark  Whether to mark (true) or unmark (false) the task.
     */
    private String toggleTaskMark(int index, boolean mark) {
        if (mark == list.isTaskDone(index)) {
            ui.showAlreadyMarked(list.get(index));
            return SentinelString.stringAlreadyMarked(list.get(index));
        } else {
            list.toggleMark(index);
            ui.showTaskMark(list.get(index));
            return SentinelString.stringTaskMark(list.get(index));
        }
    }
}
