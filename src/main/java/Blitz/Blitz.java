package blitz;

/* My import */
import command.Command;

import exception.BlitzException;

/* System import */
import java.util.Scanner;

public class Blitz {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Blitz object with specified file path, divider and tab.
     *
     * @param path File path to read and store the tasks.
     * @param divider Divider use to create Ui object.
     * @param tab Tab use to create Ui object.
     */
    public Blitz(String path, String divider, String tab) {
        this.ui = new Ui(divider, tab);
        this.storage = new Storage(path);
        this.taskList = storage.readFromFile(this.ui);
    }

    /**
     * Executes the main application.
     *
     * @throws BlitzException If there is file operation or command execution error.
     */
    public void run() {
        String[] greet = {"Hello! I'm Blitz.",
                "What can I do for you?"};
        String[] end = {"Bye. Hope to see you again soon!"};

        ui.printInDivider(greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            try {
                Command c = Parser.parse(inp);

                if (c.isExit()) {
                    break;
                }

                this.taskList = storage.readFromFile(this.ui);

                c.execute(this.taskList, this.ui, this.storage);
            } catch (BlitzException e) {
                ui.printError(e);
            }
        }

        ui.printInDivider(end);
    }

    /**
     * Entry point of the application.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args) {
        new Blitz("./Blitz.txt", "    -----------------------------------------------\n", "    ").run();
    }
}
