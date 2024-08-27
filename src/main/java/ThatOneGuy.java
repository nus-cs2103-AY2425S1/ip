import java.util.*;

import parser.Parser;
import tasks.*;
import storage.Storage;
import ui.Ui;

public class ThatOneGuy {
    private TaskManager tm;
    private Ui ui;
    private Storage storage;

    public ThatOneGuy() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tm = new TaskManager();
        storage.readData();
    }

    public static void main(String[] args) {
        ThatOneGuy guy = new ThatOneGuy();
        guy.ui.greet();
        guy.keepGoing();
        guy.ui.bye();
    }

    private void keepGoing() {
        Parser p = new Parser(new Scanner(System.in));
        boolean isActive = true;
        while (isActive) {
            isActive = p.cmd();
        }
    }
}
