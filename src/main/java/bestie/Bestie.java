package bestie;

import java.util.Scanner;

import bestie.command.Command;
import bestie.command.ExitCommand;


/**
 * Creates an instance of the Bestie chatbot.
 */
public class Bestie {
    // load the bestie.txt file in the same directory
    //private static final String FILE_PATH = "bestie.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner sc = new Scanner(System.in);


    /**
     * Creates an instance of the Bestie chatbot, initialising the ui, storage and tasklist
     *
     * @param filePath path of the bestie.txt file where tasks are stored.
     */
    public Bestie(String filePath) {
        this.ui = new Ui(sc);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasksFromFile());
    }


    /**
     * Starts execution of the Bestie chatbot.
     *  Terminates chatbot when the user keys in an exit command.
     */
    public void run() {

        // greet the user
        this.ui.welcome();
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.readNextCommand();
            Command instruction = Parser.parse(userInput);
            instruction.execute(tasks, ui, storage);
            if (instruction instanceof ExitCommand) {
                isExit = true;
            }
        }

    }
    public static void main(String[] args) {
        new Bestie("bestie.txt").run();
    }


}
