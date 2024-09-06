import Commands.Command;
import Exceptions.KieTwoForOneException;
import Parser.Parser;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import UI.UI;

import java.util.ArrayList;

public class KieTwoForOne {

    private static TaskList tasks = new TaskList(new ArrayList<Task> (100));
    private static UI ui = new UI();
    private static Storage storage;

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

    public static void main(String[] args) {
        try {
            storage.loadFile(tasks.getTaskList());
        } catch (KieTwoForOneException e) {
            System.out.println(e.getMessage());
        }
        KieTwoForOne.run();
    }
}
