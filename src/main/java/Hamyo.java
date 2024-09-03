import java.util.Scanner;

import hamyo.misc.HamyoException;
import hamyo.misc.Parser;
import hamyo.misc.Storage;
import hamyo.misc.Ui;
import hamyo.tasks.TaskList;

/**
 * Main class of the Hamyo Chatbot.
 *
 * @author Han Yu.
 */
public class Hamyo {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private boolean active;

    /**
     * Constructor of Hamyo instance. Consists of Ui for user interactions,
     * TaskList to store users' tasks, Storage to load and save users' list of
     * tasks.
     */
    public Hamyo() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.active = true;
        this.storage = new Storage("./savedTasks.txt");

        this.ui.greet();
    }

    /**
     * Continuously scans for user input and execute commands while it is
     * active, and terminates once this.active is set to false using the bye
     * command.
     */
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            storage.loadData(this.tasks);

            while (this.active) {
                try {
                    this.active = Parser.parse(scanner.nextLine(), this.tasks);
                    storage.saveData(this.tasks);
                } catch (HamyoException e) {
                    Ui.printException(e);
                }
            }
            ui.terminate();
            scanner.close();
        } catch (HamyoException e) {
            Ui.printException(e);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Main of Hamyo chatbot. Instantiates and runs chatbot upon application startup.
     * @param args No arguments required.
     */
    public static void main(String[] args) {
        new Hamyo().run();
    }
}
