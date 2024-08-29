import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The DGPT class represents a simple task management system.
 * It allows users to add tasks, mark them as done, unmark them, and view the list of tasks.
 */
public class DGPT {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a DGPT instance with an empty task list.
     */
    public DGPT(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DgptFileNotFoundException | IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;
        String input;

        // bot is ready for user
        while (isActive) {
            ui.showUser();
            input = scanner.nextLine();
            if (input.equals("bye")) {
                isActive = false;
            } else {
                try {
                    Parser.parse(input, tasks, ui);
                } catch (TaskNotFoundException | IncorrectInputException e) {
                    ui.showError(e);
                }
            }
        }

        scanner.close();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e);
        }
        ui.showBye();
    }



    /**
     * Main method to start the program and interact with the user.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new DGPT("./data/dgpt.txt").run();
    }
}
