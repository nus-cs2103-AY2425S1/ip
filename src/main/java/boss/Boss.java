package boss;

import boss.exceptions.BossException;

import java.io.File;
import java.io.IOException;

/**
 * Welcome! This class represents the main class of the Boss Chatbot.
 */
public class Boss {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Boss Object
     *
     * @param filePath The location of the file that the chatbot
     *  will read and write data from and to respectively.
     */
    public Boss(String filePath) {
        try {
            ui = new Ui();
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(e);
        }
    }

    /**
     * Gets the response of the Boss Chatbot based on the user's input!
     * Used for the GUI.
     *
     * @param input user input.
     * @return String containing response from the Boss Chatbot.
     */
    public String getResponse(String input) {
        if (input.isEmpty()) {
            return "You can't enter an empty command!";
        }
        switch(input) {
            case "hello":
            case "hey":
            case "hi":
            case "whats up":
                return "WHATS GOOD, MY HOMIE! I'm the boss!" + "\n" + "How can I help you?";
        }

        try {
            Parser parser = new Parser(storage, tasks);
            String responseText = parser.getResponse(input);

            assert !responseText.isEmpty() : "responseText cannot be empty!";

            return responseText;
        } catch (IOException e) {
            System.out.println(e);
            return "error";
        } catch (BossException e) {
            System.out.println(e);
            return "error";
        }

    }

    public static void main(String[] args) {
        System.out.println("Text-based UI has been removed");
    }

}