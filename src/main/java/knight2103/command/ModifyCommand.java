package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.Ui;
import knight2103.files.Storage;

import java.io.IOException;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskAffected;
            String stringToReturn;
            int taskIndex = Integer.parseInt(this.predicate) - 1;
            if (this.verb == CommandVerb.MARK) {
                taskAffected = tasks.mark(taskIndex);
                stringToReturn = ui.showMark(taskAffected);
            } else if (this.verb == CommandVerb.UNMARK) {
                taskAffected = tasks.unmark(taskIndex);
                stringToReturn = ui.showUnmark(taskAffected);
            } else { // CommandVerb.DELETE
                taskAffected = tasks.delete(taskIndex);
                stringToReturn = ui.showDelete(taskAffected, tasks);
            }
            storage.save(tasks);
            return stringToReturn;
        } catch (NumberFormatException e) {
            return "Please state the task number in INTEGER. Definitely not the task name";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "There's an issue in the instruction format. " + "Please check that it is " +
                    "<knight2103.command.CommandVerb> <Integer> format";
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage()
                    + "There aren't so many tasks. Please check if the task number is correct. "
                    + "To see all tasks, type list";
        } catch (IOException e) { // from save() in Storage class
            return "Problems creating an instance of FileWriter";
        }
    }
}
