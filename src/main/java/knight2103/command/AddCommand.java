package knight2103.command;

import knight2103.tasks.*;
import knight2103.Ui;
import knight2103.files.Storage;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    AddCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

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
            taskList.add(taskToAdd);
            storage.save(taskToAdd);
            ui.showAdd(taskToAdd, taskList);
        } catch (DateTimeParseException e) {
            System.out.println("knight2103.tasks.Deadline format is wrong during input. Please follow yyyy-mm-dd or with the time format");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
