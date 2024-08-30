package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.Ui;
import knight2103.files.Storage;

public class ModifyCommand extends Command {
    ModifyCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    /**
     * Executes the ModifyCommand which comes in three different variations:
     * mark, unmark, delete to modify the selected task. Depending on the exact
     * command used, the task will be modified accordingly.
     *
     * @param tasks The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     * @throws IndexOutOfBoundsException If integer keyed in the command is
     * out of range of the length of the list of tasks.
     * @throws NumberFormatException If the predicate part of command is not an Integer.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskAffected;
            int taskIndex = Integer.parseInt(this.predicate) - 1;
            if (this.verb == CommandVerb.MARK) {
                taskAffected = tasks.mark(taskIndex);
                ui.showMark(taskAffected);
            } else if (this.verb == CommandVerb.UNMARK) {
                taskAffected = tasks.unmark(taskIndex);
                ui.showUnmark(taskAffected);
            } else { // CommandVerb.DELETE)
                taskAffected = tasks.delete(taskIndex);
                ui.showDelete(taskAffected, tasks);
            }
            storage.save(tasks);
        } catch (NumberFormatException e) {
            System.out.println("Please state the task number in INTEGER. Definitely not the task name");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. " + "Please check that it is " +
                    "<knight2103.command.CommandVerb> <Integer> format");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("There aren't so many tasks. Please check if the task number is correct. "
                    + "To see all tasks, type list");
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
