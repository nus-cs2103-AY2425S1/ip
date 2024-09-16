package mel.main;

import java.util.Scanner;

import mel.exceptions.MelException;
import mel.exceptions.TaskException;
import mel.tasks.TaskList;
import mel.utils.Gui;
import mel.utils.Storage;
import mel.utils.Ui;

/**
 * Mel class is the main class of Mel chatbot.
 */
public class Mel {
    private final TaskList taskList;
    private final Ui ui;
    private final Gui gui;
    private boolean isUsingGui;
    private String response = "";
    private boolean hasException = false;


    /**
     * Constructor for Mel chatbot, instantiates
     * TaskList, Storage and UI elements.
     */
    public Mel() {
        Storage storage = new Storage();
        taskList = new TaskList(this, storage);
        ui = new Ui(this);
        gui = new Gui(this);
    }

    /**
     * Passes task input to TaskList.
     * @param input task input string.
     * @see TaskList
     */
    public void executeTask(String input) {
        try {
            taskList.executeTask(input);
        } catch (MelException | TaskException e) {
            println(e.toString());
            setHasException();
        }
    }

    /**
     * Passes response string to UI for output to user.
     * @param str response string.
     * @see Ui
     */
    public void println(String str) {
        if (isUsingGui) {
            response += str + "\n";
        } else {
            ui.println(str);
        }
    }

    /**
     * Starts Mel chatbot session, and handles
     * session's read-response sequence until session end.
     * Not used in GUI.
     */
    public void run() {
        isUsingGui = false;
        taskList.loadTasks();
        ui.hello();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            String input = scanner.nextLine();
            isBye = ui.read(input);
        }
    }

    /**
     * Generates Mel chatbot response from user input,
     * handles session's read-response sequence.
     * Only used in GUI.
     * @param input user input.
     * @return Mel's response's status and string
     */
    public String[] getResponse(String input) {
        isUsingGui = true;
        boolean isBye = gui.read(input);
        String[] s = {"", response};
        response = "";
        if (isBye) {
            s[0] = "bye";
        } else if (hasException) {
            s[0] = "exception";
            hasException = false;
        } else {
            //Fallthrough: indicates regular response.
        }
        return s;
    }

    /**
     * Sets indicator that an error has been thrown.
     */
    public void setHasException() {
        hasException = true;
    }

    /**
     * Outputs introduction for Mel on startup.
     * @return String introductory message.
     */
    public String hello() {
        String s = gui.hello();
        taskList.loadTasks();
        return s + "\n" + response;
    }

    /**
     * Starts up Mel chatbot.
     * Not used in GUI.
     */
    public static void main(String[] args) {
        new Mel().run();
    }
}
