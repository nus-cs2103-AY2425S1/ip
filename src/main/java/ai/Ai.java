package ai;

import java.io.IOException;
import java.util.Scanner;

import ai.command.Command;
import ai.exception.AiException;

/**
 * Entry point of the programme.
 */
public class Ai {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private String filePath;
    private boolean isExit = false;

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
        System.out.println(ui.showGreeting());

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        String reply;

        while (!isExit) {
            try {
                reply = scanner.nextLine();

                Command c = Parser.parse(reply);
                System.out.println(c.execute(tasks, ui));
                isExit = c.isExit();
                tasks.save(filePath);
            } catch (AiException e) {
                System.out.println(e.getMessage());
            } finally {
                tasks.save(filePath);
            }
        }
    }

    public String getResponse(String input) {
        String aiReply = "";
        try {
            Command c = Parser.parse(input);
            aiReply += c.execute(tasks, ui);
            isExit = c.isExit();
            tasks.save(filePath);

            return aiReply;
        } catch (AiException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) throws AiException {
        new Ai("./data/Ai.txt").run();
    }
}
