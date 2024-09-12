package kietwoforone;

import kietwoforone.commands.Command;
import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.parser.Parser;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.Task;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

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
