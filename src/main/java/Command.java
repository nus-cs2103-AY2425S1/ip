import java.io.IOException;

public abstract class Command {
    private String description;

    public Command(String description) {
        this.description = description;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();

    public String getDescription() {
        return description;
    }
}
