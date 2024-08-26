import java.io.IOException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;
    public DeadlineCommand(String[] inputs) {
        if (inputs.length != 2) {
            throw new FridayException("\tInvalid input. 'deadline' command requires a" +
                    " description and deadline\n\tusage: deadline <string> /by <yyyy-MM-dd HHmm>.");
        }
        this.description = inputs[0];
        this.by = inputs[1];
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        try {
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
        } catch (DateTimeParseException e) {
            System.out.println("\tInvalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }
}
