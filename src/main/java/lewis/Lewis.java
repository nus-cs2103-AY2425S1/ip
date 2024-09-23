package lewis;

import java.util.ArrayList;

/**
 * This class implements Lewis, a chatbot who is designed to interact with the user.
 */
public class Lewis {
    /** Exit flag for Lewis to close */
    private static boolean isExit = false;
    /**
     * Initialises the data for Lewis bot to function. This includes loading the tasklist from
     * the hard drive and the sorted command list that Lewis can parse.
     */
    private static void init() {
        ArrayList<String> savedTasks = Storage.load();
        TaskList tasklist = TaskList.of(savedTasks);
    }

    /**
     * Tells Lewis to run, accepting user input until the "bye" or "exit" command is given
     */
    public void run() {
        Parser parser = Parser.of();
        init();
        Ui.printLine();
        System.out.println("Hello! My name is Lewis, a chatbot.\nHow can I help you?");

        while (!isExit) {
            String userInput = Ui.readLine();
            try {
                Command command = parser.parseCommand(userInput);
                Lewis.isExit = command.isExit();
                command.execute();
            } catch (LewisException e) {
                Ui.printString(e.getMessage());
            } finally {
                Ui.printLine();
            }
        }
    }

    public String getResponse(String input) {
        return "Lewis heard: " + input;
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
        System.exit(0);
    }
}
