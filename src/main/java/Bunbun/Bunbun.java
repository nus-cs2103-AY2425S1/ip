package Bunbun;

import Bunbun.utils.Command;
import Bunbun.utils.Parser;
import Bunbun.utils.Storage;
import Bunbun.utils.TaskList;
import Bunbun.utils.Ui;

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

    public void run() {
        this.ui.startScreen();
        this.storage.initializeTaskFile();

        while (true) {
            String msg = Parser.getMessage();
            if (msg.equals("bye")) {
                this.storage.writeAllFromList(list);
                this.ui.endScreen();
                break;
            } else {
                System.out.println(msg);
                ArrayList<String> tokens = Parser.getTokens();
                Command c = new Command(this.list, this.ui);
                c.execute(tokens);
            }
        }
    }

    public void close() {
        this.storage.writeAllFromList(list);
    }

    public String getResponse(String userInput) {
        Command c = new Command(this.list, this.ui);
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        return c.execute(tokens);
    }
    public static void main(String[] args) {
        new Bunbun("src/main/data").run();
    }
}
