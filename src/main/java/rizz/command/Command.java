package rizz.command;
import rizz.source.TaskList;
import rizz.source.Ui;
import rizz.source.Storage;
import java.io.IOException;



public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    
    public boolean isExit() {
        return false; 
    }
    
}

