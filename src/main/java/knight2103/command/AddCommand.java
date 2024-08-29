package knight2103.command;

import knight2103.tasks.*;
import knight2103.Ui;
import knight2103.files.Storage;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    AddCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    /**
     * Executes the AddCommand which create the task(s) based on user input and
     * add them to the taskList. Three different types of task can be created which
     * correspond to their respective AddCommand: Todo, Deadline, Event.
     *
     * @param taskList The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     * @throws DateTimeParseException If the deadline in Deadline() is not written in
     * yyyy-MM-dd format or the start and end time written in Event() is not written in
     * yyyy-MM-ddThh:mm format.
     * @throws ArrayIndexOutOfBoundsException If the command is in inappropriate format.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskToAdd;
        try {
            if (this.verb == CommandVerb.TODO) {
                taskToAdd = new Todo(this.predicate);
            } else if (this.verb == CommandVerb.DEADLINE) {
                String[] deadlineArray = this.predicate.split(" /by ");
                taskToAdd = new Deadline(deadlineArray[0], deadlineArray[1]);
            } else { // if (this.verb == knight2103.command.CommandVerb.EVENT)
                String[] eventArray = this.predicate.split(" /from | /to ");
                taskToAdd = new Event(eventArray[0], eventArray[1], eventArray[2]);
            }
            taskList.add(taskToAdd); // need to check if it works...
            storage.save(taskToAdd);
            ui.showAdd(taskToAdd, taskList);
        } catch (DateTimeParseException e) {
            System.out.println("Format is wrong during input. Please follow yyyy-mm-dd or with the time format");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check.");
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
