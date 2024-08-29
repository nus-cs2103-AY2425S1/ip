import Parser.Parser;
import UI.UI;
import TaskList.TaskList;
import FileWriter.StorageFileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import Storage.Storage;

public class Main {
    private final Path FILE_PATH = Paths.get("data", "data.txt");
    private Parser parser;
    private UI ui;
    private TaskList tasklist;
    private StorageFileWriter fw;
    private Storage storage;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        start();
    }

    public void start() {
        // On start, need to create parser, load data from storage to make Task List
        parser = new Parser();
        ui = new UI();
        storage = new Storage(FILE_PATH);
        storage.load();
        fw = new StorageFileWriter(FILE_PATH);
        ui.welcomeMessage();
        fw.end();
    }
}
