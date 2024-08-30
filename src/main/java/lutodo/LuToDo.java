package lutodo;

import lutodo.commands.Command;
import lutodo.parser.Parser;
import lutodo.storage.Storage;
import lutodo.tasklist.TaskList;
import lutodo.ui.Ui;


public class LuToDo {

    private Storage storage;
    private TaskList tasks;


    public LuToDo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        new LuToDo("docs/taskListFile.txt").run();
    }

}
