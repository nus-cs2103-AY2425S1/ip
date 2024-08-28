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
            } else { // if (this.verb == CommandVerb.EVENT)
                String[] eventArray = this.predicate.split(" /from | /to ");
                taskToAdd = new Event(eventArray[0], eventArray[1], eventArray[2]);
            }
            taskList.add(taskToAdd); // need to check if it works...
            storage.save(taskToAdd);
            ui.showAdd(taskToAdd, taskList);
        } catch (DateTimeParseException e) {
            System.out.println("Deadline format is wrong during input. Please follow yyyy-mm-dd format");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's an issue in the instruction format. Please check.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
