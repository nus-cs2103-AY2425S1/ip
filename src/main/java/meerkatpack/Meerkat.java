package meerkatpack;

import java.io.FileNotFoundException;
import java.util.Scanner;  // Import the Scanner class
import java.io.IOException;

public class Meerkat {

    private static final String STORAGEFILEPATH = "Meerkat.txt";

    public static void main(String[] args) throws IOException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        try {
            storage.readFromFile(STORAGEFILEPATH);
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

