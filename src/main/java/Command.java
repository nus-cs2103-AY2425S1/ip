import java.io.IOException;

public interface Command {
    void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException;
}

