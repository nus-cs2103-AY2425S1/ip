import java.util.ArrayList;
import java.util.Scanner;

public class FutureYou {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an FutureYou instance with a new TaskList and Ui.
     */
    public FutureYou() {
        taskList = new TaskList();
        ui = new Ui();
    }

    /**
     * The main method that runs the FutreYou Program.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        FutureYou futureYou = new FutureYou();
        futureYou.ui.hello();
        String userCommand = "";
        while (!userCommand.equals("bye")) {
            userCommand = futureYou.ui.readUserCommand();
            futureYou.ui.respond(userCommand);
        }
        futureYou.ui.close();
    }
}