package quack.command;

import java.io.IOException;
import quack.Quack;
import quack.TaskList;
import quack.Storage;
import quack.Ui;

/**
 * This class is responsible for handling the stopping of Quack.
 */
public class ExitCommand extends Command{
    
    /**
     * Creates a ExitCommand object.
     */
    public ExitCommand() {

    }

    @Override
    public void execute (Quack quack, TaskList taskList, Storage storage, Ui ui) {
        
        try {
            storage.saveData(taskList);
        } catch (IOException IOError){
            System.out.println(IOError.getMessage());
        } finally {
            quack.shutDown();
        }
    }
}
