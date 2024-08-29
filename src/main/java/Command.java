import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws GavinException, IOException;
    boolean isExit();
}
