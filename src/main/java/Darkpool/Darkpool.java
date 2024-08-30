package Darkpool;

import Darkpool.Command.Command;
import Darkpool.util.*;

public class Darkpool {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Darkpool(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DarkpoolException e) {
//            ui.showLoadingError();
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.upperLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DarkpoolException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.lowerLine();
            }
        }
    }

    public static void main(String[] args) {
        new Darkpool("data/Darkpool.txt").run();
    }
}