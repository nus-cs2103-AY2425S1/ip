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
* Contains the method to start the chat 
* 
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
     * Alternate constructor for the chatbot, named SirPotato
     * Allows you to specify file path of data file
     * Initialises the scanner and the toDoList.
     */
    public SirPotato(String filepath) {
        this.storage = new Storage(filepath);
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

    /**
     * Generates the response for the user's chat message.
     * 
     * @param input The user's input to the chatbot
     * @return The chatbot's response after doing the specified instruction
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return ui.respond(e.getMessage());
        }
    }

}