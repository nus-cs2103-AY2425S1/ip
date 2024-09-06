package meerkatpack;

import java.io.FileNotFoundException;
import java.util.Scanner;  // Import the Scanner class

public class Meerkat {

    private static final String STORAGE_FILE_PATH = "Meerkat.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        try {
            storage.readFromFile(STORAGE_FILE_PATH);
        } catch (FileNotFoundException e) {
            ui.printFileNonexistentMessage();
        }
        ui.printGreetingMessage();

        while (true) {
            // scans the new text for new info
            String taskName = sc.nextLine();
            // splits string based on space
            parser.parse(taskName);
        }
    }
}

