package bunbun;

import bunbun.utils.Command;
import bunbun.utils.Storage;
import bunbun.utils.TaskList;
import bunbun.utils.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements a chatbot by the name of Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Bunbun {

    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Bunbun(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.list = new TaskList(this.storage, this.ui);
    }

    public void start() {
        this.storage.initializeTaskFile();
    }
    public void close() {
        this.storage.writeAllFromList(list);
    }

    public String getResponse(String userInput) {
        Command c = new Command(this.list, this.ui);
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        return c.execute(tokens);
    }
}
