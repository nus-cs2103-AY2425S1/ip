package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.Todo;
import knight2103.tasks.Deadline;
import knight2103.tasks.Event;
import knight2103.Ui;
import knight2103.files.Storage;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    AddCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    /**
     * Executes the AddCommand which create the task(s) based on user input and
     * add them to the list of tasks. Three different types of task can be created which
     * correspond to their respective AddCommand: Todo, Deadline, Event.
     *
     * @param tasks The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     * @throws DateTimeParseException If the deadline in Deadline() is not written in
     * yyyy-MM-dd format or the start and end time written in Event() is not written in
     * yyyy-MM-ddThh:mm format.
     * @throws ArrayIndexOutOfBoundsException If the command is in inappropriate format.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
            return ui.showAdd(taskToAdd, tasks);
        } catch (DateTimeParseException e) {
            return "knight2103.tasks.Deadline format is wrong during input. " + "Please follow " +
                    "yyyy-mm-dd or with the time format";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "There's an issue in the instruction format. Please check.";
        } catch (IOException e) { // from save() in Storage class
            return "Problems creating an instance of FileWriter";
        }
    }
}
