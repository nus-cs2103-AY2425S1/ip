package jade.ui;

import java.util.Scanner;

import jade.parser.Parser;
import jade.task.TaskManager;

/**
 * Handles user interaction and input for the Jade application.
 */
public class Ui {
    public static final String INDENT = "     "; // 5 spaces for indentation
    public static final String TOP_LINE = "    " + "_".repeat(60) + "\n";
    public static final String BOT_LINE = "\n" + "    " + "_".repeat(60);
    public static final String GREET = INDENT + "Hello! I'm Jade!\n"
            + INDENT + "What can I do for you?";
    public static final String EXIT = INDENT + "Bye. Hope to see you again soon!";

    private final TaskManager taskManager;
    private final Scanner sc;
    private final Parser parser;

    /**
     * Constructs a UI object with the specified TaskManager.
     *
     * @param taskManager The TaskManager to interact with.
     */
    public Ui(TaskManager taskManager) {
        this.taskManager = taskManager;

        this.sc = new Scanner(System.in);
        this.parser = new Parser();
    }

    /**
     * Starts the user interface and handles user commands.
     */
    public void run() {
        System.out.println(TOP_LINE + GREET + BOT_LINE);
        parser.parse(sc, taskManager);
        System.out.println(TOP_LINE + EXIT + BOT_LINE);
    }
}
