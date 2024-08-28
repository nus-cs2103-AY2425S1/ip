import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;

public interface Command {
    boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
