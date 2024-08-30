import java.io.IOException;

public class AddCommand extends Command {
    private String taskType;
    private String[] commandParts;

    public AddCommand(String type, String[] commandParts) {
        this.taskType = type;
        this.commandParts = commandParts;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SusanException, IOException {
        if (commandParts.length < 2) {
            throw new SusanException("The description of a task cannot be empty.");
        }

        Task task;
        switch (taskType) {
            case "todo":
                task = new ToDo(commandParts[1]);
                break;
            case "deadline":
                String[] deadlineParts = commandParts[1].split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new SusanException("The description or date of a deadline cannot be empty.");
                }
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = commandParts[1].split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new SusanException("The description or date/time of an event cannot be empty.");
                }
                task = new Event(eventParts[0], eventParts[1], eventParts[2]);
                break;
            default:
                throw new SusanException("Unknown command type.");
        }

        tasks.add(task);
        storage.load(tasks);
        ui.showAddTask(task, tasks.size());
    }
}