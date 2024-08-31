package commands;

import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

/**
 * Concrete implementation of mark command class.
 */
public class MarkCommand extends Command {
    public MarkCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) {
        try {
            int index = Integer.parseInt(details[1]);
            if (!tl.isValidIdx(index)) {
                ui.printResponse(String.format("Someones tryna be funny, idx: %d is out of range!",
                        details[1]));
                return;
            }
            if (this.command == CommandTypes.MARK) {
                Task t = tl.markAsDone(index, storage);
                if (t != null) {
                    ui.printResponse("Nice! I've marked this task as done:", Utility.INDENT + t);
                } else {
                    ui.printResponse("Task has already been completed!");
                }
            } else {
                Task t = tl.markAsUndone(index, storage);
                if (t != null) {
                    ui.printResponse("Ok, I've marked this task as not done yet:",
                            Utility.INDENT + t);
                } else {
                    ui.printResponse("Task is already unmarked!");
                }
            }
        } catch (NumberFormatException e) {
            ui.printResponse("Invalid index passed, not a number!: " + details[1]);
        }
    }
}
