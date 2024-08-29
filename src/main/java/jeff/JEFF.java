package jeff;

import jeff.command.Command;
import jeff.exceptions.JEFFException;
import jeff.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Main class that runs the JEFF chatbot.
 *
 * JEFF is a task management application that allows users to create and manage tasks.
 */
public class JEFF {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = DIR_PATH + "/JEFF.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a JEFF application with the specified file path for storage.
     */
    public JEFF() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_PATH);
    }

    public static void main(String[] args) {
        new JEFF().run();
    }

    /**
     * Runs the JEFF application, handling user input and executing commands.
     */
    public void run() {
        // Load saved files (if any)
        tasks =  new TaskList(storage.loadData());
        ui.showWelcome();
        ui.showLine();
        boolean exitChat = false;
        while (!exitChat) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                exitChat = c.isExit();
            } catch (JEFFException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}