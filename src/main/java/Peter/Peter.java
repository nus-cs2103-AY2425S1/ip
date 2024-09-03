package peter;

import utilities.CommandParser;
import utilities.Storage;
import utilities.TaskList;

public class Peter {
    public static final String FILE_PATH = "tasks/data.txt";
    private Storage store;
    private TaskList tl;

    public Peter() {
        this.store = new Storage(FILE_PATH);
        this.tl = new TaskList(this.store);
    }

    public String getResponse(String input) { 
        return CommandParser.parseCommand(input, tl, store); 
    }
}
