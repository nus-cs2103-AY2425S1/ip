package twilight;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Acts as the main class for running the chatbot.
 */
public class Twilight {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Twilight(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getStoredTasks());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (InvalidInputException e) {
            return e.toString();
        }
    }

//    public static void main(String[] args) {
//        ui.greet();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String command = ui.readInput();
//                Command c = Parser.parse(command);
//                c.execute(tasks, storage);
//                isExit = c.isExit();
//            } catch (InvalidInputException e) {
//                ui.printMessage(e.toString());
//            }
//        }
//        ui.bidFarewell();
//    }
}
