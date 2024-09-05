package sage;

import sage.Command.Command;
import sage.List.TaskList;

import java.io.IOException;

public class Sage {
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

//    public Sage(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//
//        try {
//            tasks = new TaskList(storage.loadTasks());
//        } catch (IOException | SageException e) {
//            ui.showError(e.getMessage());
//            tasks = new TaskList();
//        }
//    }

    public Sage(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | SageException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (SageException e) {
            return "Error: " + e.getMessage();
        }
    }

//    public static void main(String[] args) {
//        new Sage("data/sage.txt").run();
//    }

//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String input = ui.readInput();
//                Command command = Parser.parse(input);
//                command.execute(tasks, ui, storage);
//                isExit = command.isExit();
//
//            } catch (SageException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//    }
}
