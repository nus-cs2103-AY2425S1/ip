import java.util.ArrayList;
import java.util.Scanner;

public class Pikappi {
    static Ui ui = new Ui();
    static Storage storage = new Storage("data/pikappi.txt");
    static TaskList tasks = new TaskList();
    static Parser parser;

    public static void main(String[] args) throws PikappiException {
        tasks = storage.load();
        ui.greet();
        boolean isExit = false;
        parser = new Parser(storage, tasks, ui);

        while (!isExit) {
            String command = ui.readCommand();
            ui.showLine();
            parser.parse(command);
            isExit = parser.isExit();
            ui.showLine();
        }
    }
}
