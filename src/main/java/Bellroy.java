import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bellroy {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Bellroy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isRunning = true;
        while(isRunning) {
            String userInput = ui.getInput();
            parser.parse(userInput,taskList,ui,storage);
        }
    }
    public static void main(String[] args) {
        new Bellroy("Bellroy.txt").run();
    }
}
