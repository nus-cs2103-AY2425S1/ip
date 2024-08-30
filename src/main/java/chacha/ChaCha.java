package chacha;

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

    public ChaCha(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());

        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public void run() {
        // make scanner and find commands --> parse commands
        Parser parser = new Parser(this);
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.ui.printGreeting());
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            String output = parser.parseCommand(userInput);
            System.out.println(output);

            if (output.equals(this.ui.printExit())) {
                break;
            }

            scanner = new Scanner(System.in);
        }
    }

    public static void main(String[] args) {
        try {
            Path filePath = Paths.get("./src/main/java/chacha/data/chacha.txt");

            Files.createDirectories(filePath.getParent());
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            ChaCha chatbot = new ChaCha(String.valueOf(filePath));
            chatbot.run();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
