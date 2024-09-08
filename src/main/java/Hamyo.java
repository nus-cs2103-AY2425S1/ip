import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

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
     * Continuously scans for user input and execute commands while it is
     * active, and terminates once this.active is set to false using the bye
     * command.
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
        if (!this.isActive) {
            return "Hamyo was terminated! Please relaunch.";
        }

        // Create a stream to hold the output.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream old = System.out;
        System.setOut(printStream);

        try {
            storage.loadData(this.tasks);
            this.isActive = Parser.parse(this.tasks, input);
            storage.saveData(this.tasks);
            if (!this.isActive) {
                ui.terminate();
            }
        } catch (HamyoException e) {
            Ui.printException(e);
        }

        System.out.flush();
        System.setOut(old);

        System.out.print(outputStream);
        String response = String.join("\n", outputStream.toString().split("\n" + Ui.LINE));
        return response.substring(0, response.length() - 2);
    }

    /**
     * Main of Hamyo chatbot. Instantiates and runs chatbot upon application startup.
     * @param args No arguments required.
     */
    public static void main(String[] args) {
        new Hamyo().run();
    }
}
