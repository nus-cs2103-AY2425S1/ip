package twilight;

import java.util.Scanner;
import java.util.ArrayList;

public class Twilight {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Scanner input = new Scanner(System.in);
        Storage storage = new Storage("data/Twilight.txt");
        TaskList tasks =  new TaskList(storage.getStoredTasks());
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readInput();
                Command c = Parser.parse(command);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (InvalidInputException e) {
                ui.printMessage(e.toString());
            }
        }
        ui.bidFarewell();
    }
}
