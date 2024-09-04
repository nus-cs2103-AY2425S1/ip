package victor;

import java.nio.file.Path;
import java.nio.file.Paths;

import victor.commands.Command;
import victor.messages.ReturnMessage;
import victor.parser.Parser;
import victor.storage.Storage;
import victor.tasklist.TaskList;

/**
 * Class between MainWindow class and data handling classes like Commands and TaskList.
 * Handles requests from the MainWindow handleUserInput method by parsing, executing, and
 * writing to file.
 */
public class Handler {
    private static final Path DATA_PATH = Paths.get("data", "data.txt");
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Handler object, initialises the task list for tasks by loading
     * from storage, and initialises parser for parsing input requests.
     */
    public Handler() {
        storage = new Storage(DATA_PATH);
        taskList = storage.load();
        parser = new Parser();
    }

    /**
     * Handles request by parsing and executing command, writing command to file, and
     * returning a string representation of the program output.
     * @param request A string containing the user's input to the task bot.
     * @return A string containing the program's response to the user input.
     */
    public String handleRequest(String request) {
        Command command = parser.parseInput(request);
        command.setData(taskList);
        ReturnMessage returnMessage = command.execute();
        command.write(DATA_PATH);
        return returnMessage.getMessagesAsString();
    }
}
