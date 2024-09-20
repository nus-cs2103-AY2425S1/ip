package chacha;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import chacha.parser.CommandParser;
import chacha.task.TaskList;

/**
 * Represents the ChaCha chatbot.
 * It initialises the necessary components like Storage, Tasks and Ui.
 */
public class ChaCha {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    private boolean isEnd;

    /**
     * Constructs a new ChaCha instance
     * It checks if the file for storing tasks exists and creates it if not.
     * In case of errors during file creation, the respective exceptions are handled.
     */
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
     * Generates a response for the user input.
     *
     * @param userInput User input.
     * @return String representation of response.
     */
    public String getResponse(String userInput) {
        CommandParser commandParser = new CommandParser(this, this.storage, this.tasks, this.ui);
        return commandParser.parseCommand(userInput);
    }

    /**
     * Update ChaCha object isEnd status.
     */
    public void updateIsEnd() {
        this.isEnd = true;
    }

    /**
     * Retrieves ChaCha object isEnd status.
     *
     * @return ChaCha isEnd.
     */
    public boolean getIsEnd() {
        return this.isEnd;
    }
    /**
     * Checks if chacha.txt file exists in ./data.
     * Create data directory and chacha.txt, whichever does not exist.
     * Runs the chatbot.
     *
     */
    public static void main(String[] args) {
        ChaCha chatbot = new ChaCha();
    }
}
