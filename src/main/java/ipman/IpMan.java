package ipman;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The entry point class for the chatbot
 * This class initializes the task list, storage, user interface, and parser.
 * It also contains the main method to run the chatbot.
 *
 * @author miloaisdino
 */
public class IpMan {
    private final ArrayList<Task> list;
    private final Storage db;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a chatbot without persistence.
     * Initializes an empty task list, default storage, user interface, and parser.
     */
    public IpMan() {
        list = new ArrayList<>();
        db = new Storage();
        ui = new Ui();
        parser = new Parser(list, db, ui);
    }

    /**
     * Constructs a chatbot with persistence.
     * Initializes an empty task list, storage with the specified file path, user interface, and parser.
     *
     * @param filePath The path to save state
     */
    public IpMan(String filePath) {
        list = new ArrayList<>();
        db = new Storage(filePath);
        ui = new Ui();
        parser = new Parser(list, db, ui);
    }

    /**
     * The main method to run the chatbot.
     * If a test argument is provided, it runs without persistence.
     * Otherwise, it runs with persistence using the specified file path.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        boolean isTest = args.length > 0 && args[0] != null && Boolean.parseBoolean(args[0]);
        if (!isTest) {
            new IpMan("data/saved.txt").run();
        } else {
            new IpMan().run();
        }
    }

    /**
     * Runs the chatbot.
     * Displays the banner, loads saved tasks, and starts parsing user input.
     */
    public void run() {
        ui.showBanner();
        Scanner saved = db.getFileScanner();
        parser.parseFromScanner(saved, true);
        saved.close();
        parser.parseFromScanner(new Scanner(System.in), false);
    }
}
