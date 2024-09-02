import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * The {@code BottleOpener} class is the main entry point for the chatbot.
 * It initializes the necessary classes, loads the saved tasks, and processes user input
 * until the user decides to exit the application.
 */
public class BottleOpener {

    /**
     * The main method of the BottleOpener chatbot. It sets up the user interface, loads tasks from storage,
     * and starts the command processing loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String botName = "BottleOpener";
        String filepath = "data/BottleOpener.txt";
        Ui ui = new Ui(botName);
        Storage storage = new Storage(filepath);

        System.out.println(ui.showGreeting());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Tasklist tasks = new Tasklist();
        tasks = storage.load(tasks);
        boolean flag = false;
        while (!flag) {
            storage.save(tasks);
            String inp = "";
            try {
                inp = br.readLine();
            } catch (IOException e) {
                System.out.println("Invalid entry!\n");
            }

            Parser parser = new Parser(inp, tasks, ui);
            parser.execute();
            flag = parser.checkExit();
        }
    }
}
