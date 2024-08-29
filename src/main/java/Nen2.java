import java.util.Scanner;

/**
 * This class represents a chatbot, name Nen2
 * @author Gan Ren Yick (A0276246X)
 */

public class Nen2 {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Nen2(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks, ui);
    }

    public void run() {
        Scanner messageReader = new Scanner(System.in);

        ui.greet();
        while (true) {
            if(!parser.parseInput(messageReader.nextLine())) {
                break;
            }
        }
        storage.save(tasks.toDataStringArr());
        ui.exit();
    }

    public static void main(String[] args) {
        new Nen2("data/nen2.txt").run();
    }










}
