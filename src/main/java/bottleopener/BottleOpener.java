package bottleopener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The {@code BottleOpener.BottleOpener} class is the main entry point for the chatbot.
 * It initializes the necessary classes, loads the saved tasks, and processes user input
 * until the user decides to exit the application.
 */
public class BottleOpener {
    private static final String BOT_NAME = "BottleOpener";
    private static final String FILE_PATH = "data/BottleOpener.txt";

    /**
     * The main method of the BottleOpener.BottleOpener chatbot.
     * It sets up the user interface, loads tasks from storage,
     * and starts the command processing loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Ui ui = new Ui(BOT_NAME);
        Storage storage = new Storage(FILE_PATH);

        System.out.println(ui.showGreeting());

        Tasklist tasks = new Tasklist();
        tasks = storage.load(tasks);
        boolean hasExited = false;
        while (!hasExited) {
            storage.save(tasks);
            String userInput = "";
            try {
                userInput = br.readLine();
            } catch (IOException e) {
                System.out.println("Invalid entry!\n");
            }

            Parser parser = new Parser(userInput, tasks, ui);
            parser.execute();
            hasExited = parser.checkExit();
        }
    }
}
