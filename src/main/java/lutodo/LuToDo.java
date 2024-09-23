package lutodo;

import lutodo.commands.Command;
import lutodo.parser.Parser;
import lutodo.storage.Storage;
import lutodo.tasklist.TaskList;
import lutodo.ui.Ui;


/**
 * Main class of the project.
 */
public class LuToDo {

    private Storage storage;
    private TaskList tasks;


    /**
     * Constructs the chat box class with file path of the task list.
     * 
     * @param filePath The file path of the task list.
     */
    public LuToDo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chat box.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return Ui.LINE + "\n" + c.executeAndRespond(tasks, storage) + "\n" + Ui.LINE;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the chat box with text UI.
     */
    public static void main(String[] args) {
        new LuToDo("taskListFile.txt").run();
    }

}
