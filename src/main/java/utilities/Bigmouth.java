package utilities;

import java.net.URL;
import java.util.Scanner;

import commands.Command;

/**
 * The Bigmouth chatbot main class that initiates the program, loads tasks, and handles user input.
 */
public class Bigmouth {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bigmouth() {
        this.storage = new Storage(getFileURL());
        storage.loadFromFile();
        this.tasks = (TaskList) storage.getTasks();
        this.ui = new Ui();
        this.parser = new Parser(ui, tasks, storage);
    }
    public static void main(String[] args) {
        System.out.println("Hello!");
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

    public String greetUser() {
        return "Hey! Use me to organise your tasks :D. Just tell" +
                "me what to do and I'll add it to your list!";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = parser.parseInput(input);
        if (command.isTypeGoodbye()) {
            return "Goodbye";
        }
        return command.getResponse();
    }
}
