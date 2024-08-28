package mel.main;

import mel.exceptions.MelException;
import mel.exceptions.TaskException;
import mel.tasks.TaskList;
import mel.utils.Storage;
import mel.utils.UI;

import java.util.Scanner;

/**
 * Mel class is the main class of Mel chatbot.
 */
public class Mel {
    private final TaskList taskList;
    private final UI ui;

    /**
     * Constructor for Mel chatbot, instantiates
     * TaskList, Storage and UI elements.
     */
    public Mel() {
        Storage storage = new Storage();
        taskList = new TaskList(this, storage);
        ui = new UI(this);
    }

    /**
     * Passes task input to TaskList.
     * @param input task input string.
     * @see TaskList
     */
    public void taskAction(String input) {
        try {
            taskList.taskAction(input);
        } catch (MelException | TaskException e) {
            ui.println(e.toString());
        }
    }

    /**
     * Passes response string to UI for output to user.
     * @param str response string.
     * @see UI
     */
    public void println(String str) {
        ui.println(str);
    }

    /**
     * Starts Mel chatbot session, and handles
     * session's read-response sequence until session end.
     */
    public void run() {
        ui.hello();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            String input = scanner.nextLine();
            isBye = ui.read(input);
        }
    }

    /**
     * Main method to start up Mel chatbot.
     */
    public static void main(String[] args) {
        new Mel().run();
    }
}
