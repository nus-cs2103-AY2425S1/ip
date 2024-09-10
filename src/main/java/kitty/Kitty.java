package kitty;

import kitty.command.ByeCommand;
import kitty.kittyexceptions.FindException;
import kitty.kittyexceptions.MarksException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Provides an interactive ChatBot named Kitty.
 */
public class Kitty {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Kitty() {
        tasks = new TaskList();
        storage = new Storage();
        ui = new Ui(tasks, storage);

        try {
            storage.initializeTaskList(tasks);
        } catch (FileNotFoundException e) {
            ui.showErrorMessage("Cannot find ip/data/Kitty.txt file.");
        } catch (IOException e) {
            ui.showErrorMessage("Create file ip/data/Kitty.txt failed.");
        }
    }

    /**
     * Starts the ChatBot.
     *
     * @param args Program input.
     */
    public static void main(String[] args) {
        new Kitty().run();
    }

    public void run() {
        ui.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {

        if (input.contains("bye")) {
            return new ByeCommand(ui).run();
        }
        try {
            return Parser.parseFirstWord(input, ui, tasks, storage);
        } catch (FindException e) {
            return ui.showErrorMessage(e.toString());
        } catch (MarksException e) {
            return ui.showErrorMessage(e.toString());
        } catch (NumberFormatException e) {
            return ui.showErrorMessage("Please Input valid number.\n");
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMessage("Index out of bound!!! Please input number within the range of list size.\n");
        }

    }

}
