package utilities;

import java.net.URL;
import java.util.Scanner;

/**
 * The Bigmouth chatbot main class that initiates the program, loads tasks, and handles user input.
 */
public class Bigmouth {

    /**
     * The main method that sets up the necessary components (Storage, TaskList, Ui, Parser) and starts the chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner;
        scanner = new Scanner(System.in);
        Storage s = new Storage(getFileURL());
        s.loadFromFile();
        TaskList tasks = (TaskList) s.getTasks();
        Ui ui = new Ui();
        Parser parser = new Parser(ui, tasks, s, scanner);

        ui.showWelcome();
        parser.parseInput();
    }

    /**
     * Retrieves the file URL where tasks are stored for loading and saving.
     *
     * @return The path to the tasks file.
     */
    public static String getFileURL() {
        final URL fileURL = Bigmouth.class.getProtectionDomain().getCodeSource().getLocation();
        String path = fileURL.getPath();
        String rootPath = path.substring(0, path.indexOf("ip") + 3) + "/data/utilities.Bigmouth.txt";
        return rootPath;
    }
}
