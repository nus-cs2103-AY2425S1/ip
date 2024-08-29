package Mediell;

import java.io.IOException;
import java.util.Scanner;

public class Mediell {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message = "";
        Storage storage = new Storage();
        TaskList items = storage.loadData();
        Ui ui = new Ui(items);
        ui.printGreeting();
        while (true) {
            message = scanner.nextLine();
            if (!ui.main(message)) {
                break;
            }
            storage.saveData(ui.getTasks());
        }
        ui.printFarewell();
    }
}
