package thebotfather;

import thebotfather.command.Command;
import thebotfather.task.Task;
import thebotfather.util.*;

import java.util.ArrayList;

public class TheBotFather {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public TheBotFather(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList = storage.load();
        } catch (TheBotFatherException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.printGreeting();
        while (!isExit) {
            try {
                String completeLine = ui.readCommand();
                Command command = Parser.parse(completeLine, ui);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (TheBotFatherException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {

        new TheBotFather("./data/TheBotFather.txt").run();

    }
}
