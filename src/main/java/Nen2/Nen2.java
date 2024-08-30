package Nen2;

import java.util.Scanner;

/**
 * This class represents a chatbot, name Nen2
 * @author Gan Ren Yick (A0276246X)
 */
public class Nen2 {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Nen2(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks, ui);
    }

    /**
     * Runs the Nen2
     */
    public void run() {
        Scanner messageReader = new Scanner(System.in);

        ui.greet();
        while (true) {
            if(!parser.continueParsing(messageReader.nextLine())) {
                break;
            }
        }
        storage.save(tasks.toDataStringArr());
        ui.exit();
    }

}
