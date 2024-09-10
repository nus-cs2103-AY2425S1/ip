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
    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;
//    private String folderName = "data";
//    private String fileName = "elysia.txt";
//    private String filePath = "./" + folderName + "/" + fileName;

    public Elysia() throws IOException {
        storage = new Storage(tasks);
        storage.load();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command command = parser.parseCommand(input);
            return command.execute(tasks, storage);
        } catch (ElysiaException | IOException e) {
            return e.getMessage();
        }
    }

}