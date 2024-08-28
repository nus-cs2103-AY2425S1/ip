package peter;

import java.util.Scanner;

import utilities.CommandParser;
import utilities.Storage;
import utilities.TaskList;
import utilities.UI;

public class Peter {
    public static final String FILE_PATH = "tasks/data.txt";
    private Storage store;
    private TaskList tl;

    public Peter() {
        this.store = new Storage(FILE_PATH);
        this.tl = new TaskList(this.store);
    }

    /**
     * Run driver logic for the program.
     */
    public void run() {
        UI.greetUserOnEntry();
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine().strip();
            if (!CommandParser.canParseCommand(command, tl, store)) {
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Peter().run();
    }
}
