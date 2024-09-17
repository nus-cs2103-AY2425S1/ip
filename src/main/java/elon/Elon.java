package elon;

import elon.command.Command;
import elon.task.TaskList;

import java.io.IOException;

/**
 * The main entry point for the Elon application.
 * Initializes and runs the application, handling user input and interactions.
 */
public class Elon {
    private Ui ui = new Ui();
    private TaskList list;

    private Storage storage;

    /**
     * Constructs an Elon object with the specified Storage.
     *
     * @param storagePath the String path to create Storage object used for loading and saving tasks
     */
    public Elon(String storagePath) {
        try {
            this.storage = new Storage(storagePath);
            TaskList list = new TaskList(storage.loadFile());
        } catch (IOException e) {
            System.out.println(e);
            list = new TaskList();
        }
    }


    /**
     * Starts the application by greeting the user, loading the task list,
     * and processing user input commands through the Parser.
     *
     * @throws ElonException if an error occurs during command processing
     */
    private void run() throws ElonException {
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] inputArr = ui.getInputArr();
                Command command = Parser.parse(inputArr);
                command.execute(list, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                ui.drawLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String[] inputArr) {
        try {
            Command command = Parser.parse(inputArr);
            return command.execute(list, ui, storage);
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * The main method to run the Elon application.
     * Initializes the Storage and Elon objects and starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            Elon elon = new Elon("./Data.txt");
            elon.run();
        } catch (ElonException e) {
            System.out.println(e);
        }
    }
}
