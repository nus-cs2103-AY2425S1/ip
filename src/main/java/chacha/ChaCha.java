package chacha;

import chacha.command.Parser;
import chacha.task.Task;
import chacha.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class ChaCha {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    private boolean isEnd;
    private String response;

    public ChaCha() {
        try {
            Path filePath = Paths.get("./src/main/java/chacha/data/chacha.txt");

            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            this.ui = new Ui();
            this.storage = new Storage(String.valueOf(filePath));
            this.tasks = new TaskList(storage.load());
            this.isEnd = false;

        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Creates Parser object, Scanner object.
     * Reads user inputs and calls respective methods to handle respective user inputs.
     *
     */
    public void run() {
        // make scanner and find commands --> parse commands
        Parser parser = new Parser(this, this.storage, this.tasks, this.ui);
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.ui.printGreeting());
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            this.response = parser.parseCommand(userInput);

            if (this.isEnd) {
                break;
            }

            scanner = new Scanner(System.in);
        }
    }

    /**
     * Generates a response for the user input.
     * @param userInput
     * @return
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser(this, this.storage, this.tasks, this.ui);
        return parser.parseCommand(userInput);
    }

    public void updateIsEnd() {
        this.isEnd = true;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }


    /**
     * Checks if chacha.txt file exists in ./data.
     * Create data directory and chacha.txt, whichever does not exist.
     * Runs the chatbot.
     *
     * @throws IOException if an I/O exception occurs.
     */
    public static void main(String[] args) {
        ChaCha chatbot = new ChaCha();
//        chatbot.run();
    }
}
