package ipman;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;

/**
 * The entry point class for the chatbot
 * This class initializes the task taskList, storage, user interface, and parser.
 * It also contains the main method to run the chatbot.
 *
 * @author miloaisdino
 */
public class IpMan {
    private final ArrayList<Task> taskList;
    private final Storage db;
    private final TextUi textUi;
    private final Parser parser;

    /**
     * Constructs a chatbot without persistence.
     * Initializes an empty task taskList, default storage, user interface, and parser.
     */
    public IpMan() {
        taskList = new ArrayList<>();
        db = new Storage();
        textUi = new TextUi();
        parser = new Parser(taskList, db, textUi);
    }

    /**
     * Constructs a chatbot with persistence.
     * Initializes an empty task taskList, storage with the specified file path, user interface, and parser.
     *
     * @param filePath The path to save state
     */
    public IpMan(String filePath) {
        taskList = new ArrayList<>();
        db = new Storage(filePath);
        textUi = new TextUi();
        parser = new Parser(taskList, db, textUi);
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
            Application.launch(Ui.class, args);
        } else {
            new IpMan().runTextMode();
        }
    }

    /**
     * Initializes the chatbot by displaying the banner and loading saved state.
     */
    public void init() {
        Scanner saved = db.getFileScanner();
        parser.parseFromScanner(saved, true);
        saved.close();
    }

    /**
     * Runs the chatbot in text mode.
     * Displays the banner and starts parsing user input from the console.
     */
    public void runTextMode() {
        init();
        textUi.printBanner();
        parser.parseFromScanner(new Scanner(System.in), false);
    }

    public String getResponse(String input) {
        return parser.parseFromUi(input);
    }
}
