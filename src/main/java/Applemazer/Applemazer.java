package applemazer;

import commands.Command;
import java.util.Scanner;

public class Applemazer {
    private final Ui ui;
    private static Storage storage;
    public static TaskList tasks;
    public static Scanner sc = new Scanner(System.in);

    public Applemazer(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    private void process() {
        ui.greeting();
        boolean processing = true;
        while (processing) {
            if (!sc.hasNext()) { break; } // For automated testing of text UIs.
            String command = sc.next();
            try {
                Command c = Parser.parse(command);
                c.execute(tasks.getList(), storage);
                processing = c.continueProcessing();
            } catch (Exception e) {
                System.err.println(e.getMessage() + "\n");
            }
        }
        sc.close();
        ui.farewell();
    }

    public static void main(String[] args) {
        new Applemazer("./data/Applemazer.ser").process();
    }
}
