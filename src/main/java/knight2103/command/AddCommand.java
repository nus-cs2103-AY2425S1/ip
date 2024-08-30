package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.Todo;
import knight2103.tasks.Deadline;
import knight2103.tasks.Event;
import knight2103.Ui;
import knight2103.files.Storage;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    AddCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    /**
     * Executes the Command which comes in three different variations:
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
        Task taskToAdd;
        try {
            if (this.verb == CommandVerb.TODO) {
                taskToAdd = new Todo(this.predicate);
            } else if (this.verb == CommandVerb.DEADLINE) {
                String[] deadlineArray = this.predicate.split(" /by ");
                taskToAdd = new Deadline(deadlineArray[0], deadlineArray[1]);
            } else { // CommandVerb.EVENT
                String[] eventArray = this.predicate.split(" /from | /to ");
                taskToAdd = new Event(eventArray[0], eventArray[1], eventArray[2]);
            }
            tasks.add(taskToAdd);
            storage.save(taskToAdd);
            ui.showAdd(taskToAdd, tasks);
        } catch (DateTimeParseException e) {
            System.out.println("knight2103.tasks.Deadline format is wrong during input. " + "Please follow " +
                    "yyyy-mm-dd or with the time format");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
