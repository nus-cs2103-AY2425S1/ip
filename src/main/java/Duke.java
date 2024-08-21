import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.storage.readData();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.startup();
        duke.continueReading();
        duke.ui.shutdown();
    }

    private void continueReading() {
        Parser parser = new Parser(new Scanner(System.in));
        boolean running = true;
        while (running) {
            running = parser.handleUserInput();
        }
    }
}