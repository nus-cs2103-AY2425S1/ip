package lutchat;

import java.util.Scanner;

/**
 * The main class of the Lutchat application.
 * This class is responsible for initializing the application, handling user input,
 * and managing the flow of the program.
 */
public class Lutchat {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Lutchat object.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Lutchat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error Loading Tasks... " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Lutchat application.
     * This method is responsible for greeting the user, handling the input loop, and saving tasks upon exit.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String userInput = scanner.nextLine();
            isRunning = Parser.parse(userInput, ui, tasks, storage);
        }

        storage.save(tasks);
        ui.exit();
        scanner.close();
    }

    /**
     * The main method that serves as the entry point for the Lutchat application.
     *
     * @param args Command-line arguments. Not used in this application.
     */
    public static void main(String[] args) {
        String filePath = "data/tasks.txt";
        Lutchat lutchat = new Lutchat(filePath);
        lutchat.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Lutchat heard: " + input;
    }
}