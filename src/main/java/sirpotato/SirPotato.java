package sirpotato;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.util.ArrayList; 
import java.util.Scanner;  

/**
* SirPotato is the name of the chatbot 
* that we will be using in this project
* @author Rahul Agarwal
*/
public class SirPotato {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for the chatbot, named SirPotato
     * Initialises the scanner and the toDoList.
     */
    public SirPotato() {
        this.storage = new Storage("../../../data/list.txt");
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("No history detected. Will create new file");
            tasks = new TaskList();
        }
    }



    /**
     * Starts the chat with the bot. Keeps accepting user input until the user types 'bye'.
     * Saves the data to a file as you go / creates a file if one doesn't exist 
     */
    public void startChat() {
        ui.displayWelcomeMessage();

        while (true) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parseCommand(userInput);
                command.execute(tasks, ui, storage);

                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException | IOException e) {
                ui.respond(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        SirPotato potato = new SirPotato();
        potato.startChat();

    }
}