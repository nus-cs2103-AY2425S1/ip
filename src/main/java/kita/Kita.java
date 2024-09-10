package kita;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main Kita chatbot class
 */
public class Kita {

    private static TaskList tasks;
    private static Scanner getInput;
    private static Commands commandsExecutor;
    private static Storage saveSystem;

    /**
     * Initialises a Kita instance for use with a GUI
     */
    public Kita() {
        setUp();
    }

    public static void setUp() {
        try {
            saveSystem = new Storage();
            tasks = new TaskList(saveSystem.readTasksFromFile());
        } catch (Exception e) {
            System.out.println("Oh no, Kita failed to create/read from the save file successfully :c");
            System.out.println(e);
            return;
        }

        commandsExecutor = new Commands(tasks, saveSystem);

        assert saveSystem != null;
        assert tasks != null;
        assert commandsExecutor != null;
    }

    /**
     * For getting the output as a String given an inputStr (for use with the GUI)
     * @param inputStr
     * @return ParserMessage
     * @throws IOException
     */
    public ParserMessage getOutput(String inputStr) throws IOException {
        return Parser.parse(inputStr, commandsExecutor);
    }

    /**
     * Exposes the underlying Commands instance used by this instance of Kita to run commands
     * @return The Commands instance
     */
    public Commands getCommandsExecutor() {
        return Kita.commandsExecutor;
    }

    /**
     * For handling of command line input if the entry point is Kita.java
     * Will call `setup()` internally to populate the necessary objects
     *
     * @param args
     */
    public static void main(String[] args) {
        setUp();
        getInput = new Scanner(System.in);
        commandsExecutor.hello();

        while (true) {
            try {
                String command = getInput.nextLine();
                commandsExecutor.printLine();
                Parser.parse(command, commandsExecutor);
            } catch (Exception e) {
                System.out.println(e);
            }
            commandsExecutor.printLine();
        }
    }
}
