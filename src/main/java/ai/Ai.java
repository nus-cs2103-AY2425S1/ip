package ai;

import ai.command.Command;
import ai.exception.AiException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point of the programme.
 */
public class Ai {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private String filePath;

    /**
     * Initialises Ai, including Ui, Storage, and TaskList.
     *
     * @param filePath Path of the .txt file to store the task list.
     */
    public Ai(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AiException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Obtains user reply using a scanner. Repeat the process until "bye" command is issued.
     */
    public void run() { 
        ui.showGreetings();
        boolean isExit = false;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String reply;

        while (!isExit) {
            try {
                reply = scanner.nextLine();
                ui.showLine();
                Command c = Parser.parse(reply);
                c.execute(tasks, ui);
                isExit = c.isExit();
                tasks.save(filePath);
            } catch (AiException e) {
                ui.showError(e.getMessage());
                ui.showLine();
            } finally {
                ui.showLine();
                tasks.save(filePath);
            }
        }
    }

    public static void main(String[] args) throws AiException {
        new Ai("./data/Ai.txt").run();
    }
}