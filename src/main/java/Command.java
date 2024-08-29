import java.io.IOException;
import java.util.ArrayList;

public class Command {
    protected boolean isExit;
    protected String keyword;

    public Command(String keyword) {
        this.isExit = false;
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    public boolean isExit() {
        return isExit;
    }
}
