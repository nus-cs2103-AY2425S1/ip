package elysia;

import elysia.command.Command;
import elysia.dateparser.Parser;
import elysia.exception.ElysiaException;
import elysia.storage.Storage;
import elysia.task.Task;
import elysia.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point of the (Elysia) chatbot application.
 * Initializes the application.
 */
public class Elysia {
    private static ArrayList<Task> tasks;
    private static String folderName = "data";
    private static String fileName = "elysia.txt";
    private static String filePath = "./data/elysia.txt";

    /**
     * Runs the program until termination.
     **/
    public static void run() throws IOException {
        boolean hasExited = false;
        Scanner scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
        Storage storage = new Storage(tasks);
        Storage.createFile(folderName, fileName);
        storage.scanFileContents(filePath);
        Ui ui = new Ui();

        ui.showWelcomeMessage();


        while (!hasExited) {
            String input = scanner.nextLine();
            Parser parser = new Parser();

            try {
                Command command = parser.parseCommand(input);
                command.execute(tasks, storage);
                hasExited = command.hasExited;
            } catch (ElysiaException e) {
                ui.showFailMessage(e.getMessage());
            }
        }

        try {
            storage.handleExit(folderName, fileName, filePath);
        } catch (IOException e) {
            ui.showFailMessage(e.getMessage());
        }

        ui.showExitMessage();
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}