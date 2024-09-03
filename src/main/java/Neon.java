
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.time.LocalDate;


public class Neon {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    private static final String STORAGE_FILE_PATH = "./data/data.txt";

    public Neon(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.printGreetingLine();
        boolean isExit = false;

        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            isExit = parser.processInput(scanner.nextLine());
        }

        ui.printClosingLine();

        storage.save(tasks.getTasks());
    }

    public static void main(String[] args) throws IOException {
        new Neon(STORAGE_FILE_PATH).run();
    }

}
