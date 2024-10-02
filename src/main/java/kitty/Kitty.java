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
        storage = new Storage("Kitty.txt");
        ui = new Ui(tasks, storage);

        try {
            storage.initializeTaskList(tasks);
        } catch (FileNotFoundException e) {
            String kittyFileNotFoundMessage = "Cannot find ip/data/Kitty.txt file.";
            ui.showErrorMessage(kittyFileNotFoundMessage);
        } catch (IOException e) {
            String kittyFileCreateFailMessage = "Create file ip/data/Kitty.txt failed.";
            ui.showErrorMessage(kittyFileCreateFailMessage);
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
            String requestForCorrectNumberFormatInputResponse = "Please Input valid number.\n";
            return ui.showErrorMessage(requestForCorrectNumberFormatInputResponse);
        } catch (IndexOutOfBoundsException e) {
            String requestForValidNumberInputResponse =
                    "Index out of bound!!! Please input number within the range of list size.\n";
            return ui.showErrorMessage(requestForValidNumberInputResponse);
        }

    }

}
