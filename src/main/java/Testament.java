import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class Testament {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    public Testament(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = storage.load();
        parser = new Parser(ui, taskList, storage);
    }

    public void run() {
        ui.welcome();
        parser.getUserInput();
    }

    public static void main(String[] args) {
        new Testament("src/main/java/Memory/TaskList.txt").run();
    }
}