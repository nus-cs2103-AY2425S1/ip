package jeff;

import javafx.scene.layout.VBox;
import jeff.command.Command;
import jeff.exceptions.JeffException;

/**
 * Main class that runs the JEFF chatbot.
 *
 * JEFF is a task management application that allows users to create and manage tasks.
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class Jeff {
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = DIR_PATH + "/JEFF.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a JEFF application with the specified file path for storage.
     */
    public Jeff() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_PATH);
    }

    /**
     * Runs the JEFF application, handling user input and executing commands.
     */
    public void run() {
        boolean exitChat = false;
        while (!exitChat) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                exitChat = c.isExit();
            } catch (JeffException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initialises the JEFF Chatbot to load saved tasks.
     */
    public void init() {
        // Load saved files (if any)
        tasks = new TaskList(storage.loadData());
    }

    public void getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (JeffException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
    }

    public void setDialogContainer(VBox vBox) {
        ui.setDialogContainer(vBox);
        ui.showWelcome();
    }
}
