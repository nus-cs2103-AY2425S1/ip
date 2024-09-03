package hoshi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Parser;
import hoshi.utils.Storage;


/**
 * Main class for Hoshi
 */
public class Hoshi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Hoshi method
     */
    public Hoshi() {

        String filePath = "data/Hoshi.txt";

        ui = new Ui();

        storage = new Storage(filePath);

        taskList = new TaskList();

        parser = new Parser();

        try {
            storage.load(taskList);
        } catch (FileNotFoundException e) {
            ui.displayLoadingError(e.getMessage());
        }
    }

    /**
     * Run method - main flow of Hoshi
     */
    public String run(String input) {

        String response = "";

        response = parser.parseCommand(input, taskList, ui);

        //ui.displayWelcome();
        //Scanner scanner = new Scanner(System.in);

        //ui.displayPrompt();
        //input = scanner.nextLine();


        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.displaySavingError(e.getMessage());
            response = e.getMessage();
        }
        return response;
    }

}
