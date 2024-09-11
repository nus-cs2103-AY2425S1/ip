package main.commands;
import java.io.IOException;
import main.source.*;


public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    
    public boolean isExit() {
        return false; 
    }
    
}

