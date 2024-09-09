package kotori;

import kotori.command.Command;
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
        assert (storage.isSuccess());
        this.taskList = this.storage.load();
        this.parser = new Parser(storage, taskList);
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


