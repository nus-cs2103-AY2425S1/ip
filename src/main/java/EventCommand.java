import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class EventCommand extends Command {

    public EventCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        LocalDateTime from = LocalDateTime.parse(
                getArgs().get("from"), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        LocalDateTime to = LocalDateTime.parse(
                getArgs().get("to"), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        Task event = new Event(
                getArgs().get("main"),
                from,
                to
        );
        tasks.addTask(event);
    }

}
