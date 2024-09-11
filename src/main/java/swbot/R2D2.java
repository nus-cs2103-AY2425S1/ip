package swbot;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that contains the main program for the chatbot. It takes in input from the
 * user and interprets it based on different commands.
 */
public class R2D2 {
    private static final String FILE_PATH = "./data/R2D2.txt";
    private Storage storage;
    private InputHandler c3po;

    public R2D2() {
        this.storage = new Storage(FILE_PATH);
        ArrayList<Task> database = storage.loadTasks();
        this.c3po = new InputHandler(database, storage);
    }

    public String getResponse(String input) {
        String response = "";
        try {
            if (input.equals("bye")) {
                response = "Bye. Hope to see you again soon! *bzzzt*\n";
            } else {
                response = c3po.overallHandler(input);
            }
        } catch (BuzzException e) {
            response = " " + e.getMessage() + "\n";
        }
        return response;
    }
}
