package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.TodoTask;
import knight2103.tasks.DeadlineTask;
import knight2103.tasks.EventTask;
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
                taskToAdd = new TodoTask(this.predicate);
            } else if (this.verb == CommandVerb.DEADLINE) {
                String[] deadlineArray = this.predicate.split(" /by ");
                taskToAdd = new DeadlineTask(deadlineArray[0], deadlineArray[1]);
            } else { // CommandVerb.EVENT
                String[] eventArray = this.predicate.split(" /from | /to ");
                taskToAdd = new EventTask(eventArray[0], eventArray[1], eventArray[2]);
            }
            tasks.add(taskToAdd);
            storage.saveToFile(taskToAdd);
            return ui.showAdd(taskToAdd, tasks);
        } catch (DateTimeParseException e) {
            return "Failed to execute Command:\nInput of Date or time format is wrong."
                    + "\nFor Deadline task, the format should be in yyyy-MM-dd"
                    + "\nFor Event task, the format should be in yyyy-MM-ddThh:mm";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Failed to execute Command:\n"
                    + "There's an issue in the instruction format. Please check.";
        } catch (IOException e) { // from save() in Storage class
            return "Failed to execute Command:\nProblems creating an instance of FileWriter";
        }
    }
}
