import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String info, LocalDateTime deadline) {
        super(new Deadline(info, deadline));
    }
}
