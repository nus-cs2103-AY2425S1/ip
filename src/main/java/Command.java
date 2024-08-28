import java.time.format.DateTimeFormatter;

public abstract class Command {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public abstract void actionable(TaskList list, Ui ui);

    public abstract boolean isExit();
}
