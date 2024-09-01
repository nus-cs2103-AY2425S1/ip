import java.util.Scanner;

import echo.*;


/**
 * Represents a simple task management bot that interacts with the user via the console.
 * It handles task storage, user input, and displays task-related information.
 */
public class EchoBot {
    private Scanner scanner = new Scanner(System.in);
    private Ui ui = new Ui();
    private TaskList allTasks;

    /**
     * Creates an echobot object and sends greet message to the user.
     */
    public EchoBot() {
        Storage.init();
        this.allTasks = Storage.getData();
        this.ui.greet();
    }

    public String getResponse(String input) {
        this.allTasks = Storage.getData();
        String userOutput = run(input, this.allTasks);
        return userOutput;
    }

    public static String run(String userInput, TaskList allTasks) {
        String botOutput = "";
        try {
            botOutput = Parser.parse(userInput, allTasks);
            Storage.setData(allTasks);
        } catch (DukeException e) {
            botOutput = e.getMessage();
        } finally {
            return botOutput;
        }
    }
}
