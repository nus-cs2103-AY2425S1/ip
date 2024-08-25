import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
//    private final Scanner input;
    private Ui ui;
//    private ArrayList<Task> tasks;
    private TaskList taskList;
    private Storage storage;
    private static final String DIR_PATH = "./data/";
    private static final String FILE_NAME = "tasks.txt";

    public Bobby() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_NAME);
        this.taskList = new TaskList(this.storage.load());
    }

    public void run() {
        this.ui.sayHi();
        while (this.ui.runStatus()) {
            try {
                Parser.processInput(this.ui.getInput(), this.taskList, this.storage, this.ui);
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.run();
    }
}
