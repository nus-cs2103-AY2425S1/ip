import java.io.IOException;

public class AddEventCommand extends Command {

    public AddEventCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        String taskInfo = Parser.splitCommandAndTaskDescription(command);
        String taskDescriptionEvent = Parser.splitEventCommand(taskInfo)[0];
        String startInfo = Parser.splitEventCommand(taskInfo)[1];
        String endInfo = Parser.splitEventCommand(taskInfo)[2];

        String taskStart = Parser.splitCommandAndTaskDescription(startInfo);
        String taskEnd = Parser.splitCommandAndTaskDescription(endInfo);

        Task newEvent = new Event(taskDescriptionEvent, taskStart, taskEnd);
        String addedEvent = tasks.addTask(newEvent);
        System.out.println(addedEvent);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
