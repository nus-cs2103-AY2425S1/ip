import java.io.IOException;

public abstract class Command {
     public abstract void execute(TaskList taskList, UI ui, Storage storage) throws IOException;

     public abstract boolean isExit();
}
