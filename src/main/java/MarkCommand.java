import java.io.IOException;

public class MarkCommand extends Command {

    public MarkCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();
        int taskNum = Parser.splitCommandAndTaskNumber(command);
        System.out.println(tasks.markTask(taskNum));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
