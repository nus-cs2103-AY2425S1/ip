import Hamyo.Misc.HamyoException;
import Hamyo.Misc.Parser;
import Hamyo.Misc.UI;
import Hamyo.Misc.Storage;

import Hamyo.Tasks.TaskList;

import java.util.Scanner;

/**
 * Main class of the Hamyo Chatbot.
 *
 * @author Han Yu.
 */
public class Hamyo {

    private final UI ui;
    private final Storage storage;
    private final TaskList tasks;
    private boolean active;

    /**
     * Constructor of Hamyo instance. Consists of UI for user interactions,
     * TaskList to store users' tasks, Storage to load and save users' list of
     * tasks.
     */
    public Hamyo() {
        this.ui = new UI();
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
                    UI.printException(e);
                }
            }
            ui.terminate();
            scanner.close();
        } catch (HamyoException e) {
            UI.printException(e);
        }
    }

    /**
     * Main of Hamyo chatbot. Instantiates and runs chatbot upon application startup.
     * @param args No arguments required.
     */
    public static void main(String[] args) {
        new Hamyo().run();
    }
}
