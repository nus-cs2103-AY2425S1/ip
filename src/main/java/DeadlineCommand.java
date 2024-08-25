import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DeadlineCommand extends Command {

    public DeadlineCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        LocalDateTime by = LocalDateTime.parse(
                getArgs().get("by"), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        Task deadline = new Deadline(
                getArgs().get("main"),
                by
        );
        tasks.addTask(deadline);
    }

}
