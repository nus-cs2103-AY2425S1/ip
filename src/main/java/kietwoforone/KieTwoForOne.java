package kietwoforone;

import kietwoforone.commands.Command;
import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.parser.Parser;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.Task;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;
import java.util.ArrayList;

/**
 * Represents the chatbot and its interactions with the user.
 */
public class KieTwoForOne {

    private static TaskList tasks = new TaskList(new ArrayList<Task> (100));
    private static UI ui = new UI();
    private static Storage storage;

    /**
     * Runs the chatbot by initially displaying the welcome message and setting isExit as false.
     * When the user keys in a command, the command is read and the parsed to execute the appropriate command.
     * Incorrect inputs throws a KieTwoForOne exception and the chatbot will display the error message.
     * Lastly the separation line is drawn to separate the different commands.
     * If isExit is true, function exits.
     */
    public static void run() {
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KieTwoForOneException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Loads the saved tasks from the text file and adds it to the task list ad then runs the chatbot.
     * Throws a KieTwoForOne exception when the file does not exist.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            storage.loadFile(tasks.getTaskList());
        } catch (KieTwoForOneException e) {
            System.out.println(e.getMessage());
        }
        KieTwoForOne.run();
    }

}
