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
    private boolean isActive;

    /**
     * Constructor of Hamyo instance. Consists of Ui for user interactions,
     * TaskList to store users' tasks, Storage to load and save users' list of
     * tasks.
     */
    public Hamyo() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.isActive = true;
        this.storage = new Storage("./savedTasks.txt");

        this.ui.greet();
    }

    /**
     * Status of the hamyo chatbot - true if chatbot is running and waiting for input.
     *
     * @return true is hamyo isActive, false otherwise.
     */
    public boolean getStatus() {
        return this.isActive;
    }

    /**
     * Scans for user input and execute commands.
     */
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            storage.loadData(this.tasks);

            while (this.isActive) {
                try {
                    this.isActive = Parser.parse(this.tasks, scanner.nextLine());
                    storage.saveData(this.tasks);
                } catch (HamyoException e) {
                    Ui.setStringException(e);
                }
            }
            ui.terminate();
            scanner.close();
        } catch (HamyoException e) {
            Ui.setStringException(e);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            storage.loadData(this.tasks);
            this.isActive = Parser.parse(this.tasks, input);
            if (!this.isActive) {
                return "hamyo terminated!";
            }
            storage.saveData(this.tasks);
            String guiResponse = String.join("\n", Ui.getResponse().split("\n" + Ui.LINE));
            return guiResponse.substring(0, guiResponse.length() - 2);
        } catch (HamyoException e) {
            Ui.setStringException(e);
        }
        return null;
    }

    /**
     * Main of Hamyo chatbot.
     * Instantiates and runs chatbot upon application startup.
     *
     * @param args No arguments required.
     */
    public static void main(String[] args) {
        new Hamyo().run();
    }
}
