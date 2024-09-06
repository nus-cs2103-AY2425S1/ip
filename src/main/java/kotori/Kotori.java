package kotori;

import java.util.Scanner;

import kotori.command.Command;
import kotori.command.ExitCommand;
import kotori.command.GreetCommand;
import kotori.parser.Parser;
import kotori.storage.Storage;
import kotori.taskList.TaskList;



/**
 * This class represents the bot.
 * */

public class Kotori {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Produce a bot.
     * */
    public Kotori() {
        this.storage = new Storage("data", "Kotori.txt");
        this.taskList = this.storage.load();
        this.parser = new Parser(storage, taskList);
    }



    /**
     * Let the bot start running.
     * */

    public void run() {
        // Create a scanner
        Scanner s = new Scanner(System.in);
        // Greet the user
        new GreetCommand().execute();
        // Get the input and execute the correct command
        while (s.hasNextLine()) {
            Command command = parser.parse(s.nextLine());
            command.execute();
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Kotori().run();
    }

    /**
     * Return a string for Ui.
     *
     * @param input the input of user.
     * @return The String for ui to output.
     * */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        return command.execute();
    }

    /**
     * Return a string of reading status for Ui.
     *
     * @return The String for ui to output.
     * */
    public String getReadingStatus() {
        if (!storage.hasFile()) {
            return "There is no existing memory file so I create a new one for you~ ^_^\n";
        } else if (storage.isCorrupted()) {
            return "The memory file is corrupted so I create a new one for you~ ^_^\n";
        } else {
            return "I have read the memory file, use 'list' command to view current task list~ ^_^\n";
        }
    }

    public String getGreeting() {
        return new GreetCommand().execute();
    }
}


