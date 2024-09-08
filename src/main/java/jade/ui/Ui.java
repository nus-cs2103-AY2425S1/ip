package jade.ui;

import java.util.Scanner;

import jade.command.ExitCommand;
import jade.command.GreetCommand;
import jade.parser.Parser;
import jade.task.TaskManager;

/**
 * Handles user interaction and input for the Jade application.
 */
public class Ui {
    public static final String INDENT = "     "; // 5 spaces for indentation
    public static final String TOP_LINE = "    " + "_".repeat(60) + "\n";
    public static final String BOT_LINE = "\n" + "    " + "_".repeat(60);

    private final TaskManager taskManager;
    private final Scanner sc;
    private final Parser parser;

    /**
     * Constructs a UI object with the specified TaskManager.
     *
     * @param taskManager The TaskManager to interact with.
     */
    public Ui(TaskManager taskManager, Parser parser) {
        this.taskManager = taskManager;
        this.parser = parser;

        this.sc = new Scanner(System.in);
    }

    /**
     * Starts the user interface and handles user commands.
     */
    public void run() {
        System.out.println(new GreetCommand().run());
        parser.parse(sc, taskManager);
        System.out.println(new ExitCommand().run());
    }
}
