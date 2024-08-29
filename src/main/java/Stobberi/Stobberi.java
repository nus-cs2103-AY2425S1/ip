package Stobberi;

import Stobberi.Command.Command;
import Stobberi.StobberiException.StobberiException;
import Stobberi.components.Parser;
import Stobberi.components.Storage;
import Stobberi.components.TaskList;
import Stobberi.components.Ui;

public class Stobberi {
    private TaskList taskList;
    private Ui ui;
    Storage storage;

    public Stobberi() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("data/list.txt");
    }

    public void run() {
        taskList.updateTaskList(storage.getList());
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute();
                isExit = c.isExit();
            } catch (StobberiException e) {
                Ui.displayForm(e.getMessage());
            }
        }
        ui.goodbye();
        storage.saveList(taskList.getListOfTasks());
    }

    public static void main(String[] args) {
        new Stobberi().run();
    }
}
