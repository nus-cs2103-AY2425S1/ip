import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Dude class is a chatbot, whose name is Dude, that can help you manage your tasks.
 */
public class Dude {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Dude object with the given file path.
     *
     * @param filePath The file path of the storage file.
     */
    public Dude(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.initTasks();
        } catch (IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DudeException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Dude("data.txt").run();
    }
}