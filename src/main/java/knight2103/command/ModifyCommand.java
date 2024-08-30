package knight2103.command;

import knight2103.tasks.*;
import knight2103.Ui;
import knight2103.files.Storage;

public class ModifyCommand extends Command {
    ModifyCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    /**
     * Executes the ModifyCommand which comes in three different variations:
     * mark, unmark, delete to modify the taskList. Depending on the exact
     * command used, the taskList will be modified accordingly.
     *
     * @param taskList The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     * @throws IndexOutOfBoundsException If integer keyed in the command is
     * out of range of the length of taskList.
     * @throws NumberFormatException If the predicate part of command is not an Integer.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskAffected;
            int taskIndex = Integer.parseInt(this.predicate) - 1;
            if (this.verb == CommandVerb.MARK) {
                taskAffected = taskList.mark(taskIndex);
                ui.showMark(taskAffected);
            } else if (this.verb == CommandVerb.UNMARK) {
                taskAffected = taskList.unmark(taskIndex);
                ui.showUnmark(taskAffected);
            } else { // if (this.verb == knight2103.command.CommandVerb.DELETE)
                taskAffected = taskList.delete(taskIndex); // ugh
                ui.showDelete(taskAffected, taskList);
            }
            storage.save(taskList);
        } catch (NumberFormatException e) {
            System.out.println("Please state the task number in INTEGER. Definitely not the task name");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("There aren't so many tasks. Please check if the task number is keyed in correctly. To see all tasks, type list");
        }
    }

    /**
     * Returns whether the bot programme should be exited upon command execution.
     *
     * @return if programme exit after execution.
     */
    public boolean isExit() {
        return false;
    }
}
