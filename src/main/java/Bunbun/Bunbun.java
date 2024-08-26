package Bunbun;

import java.util.ArrayList;
import Bunbun.utils.*;

/**
 * This class implements a chatbot by the name of Bunbun.Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Bunbun {

    private Storage storage;
    private TaskList list;
    private UI ui;

    public Bunbun(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI();
        this.list = new TaskList(this.storage, this.ui);
    }

    public void run() {
        this.ui.startScreen();
        storage.initializeTaskFile();

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
    public static void main(String[] args) {
        new Bunbun("src/main/data").run();
    }
}
