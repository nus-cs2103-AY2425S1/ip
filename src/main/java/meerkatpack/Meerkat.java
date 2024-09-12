package meerkatpack;

import com.sun.javafx.sg.prism.NGGroup;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import taskpack.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class

/**
 * meerkatpack.Main class that runs the program.
 */
public class Meerkat {

    private static final String STORAGE_FILE_PATH = "Meerkat.txt";
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object

        try {
            storage.readFromFile(STORAGE_FILE_PATH);
        } catch (FileNotFoundException e) {
            return;
        }

        while (true) {
            // scans the new text for new info
            String taskName = sc.nextLine();
            // splits string based on space
            parser.parse(taskName);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}

