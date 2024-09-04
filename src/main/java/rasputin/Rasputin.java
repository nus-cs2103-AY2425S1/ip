package rasputin;

import javafx.application.Application;
import javafx.application.Platform;
import rasputin.command.Command;
import rasputin.command.InvalidCommand;

import rasputin.parser.Parser;

import rasputin.storage.Storage;

import rasputin.task.RasputinException;
import rasputin.task.TaskList;

import rasputin.gui.Ui;

/**
 * Represents a chatbot to store a list of tasks for the user.
 */
public class Rasputin {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Takes in the path of the .txt file containing the list of tasks to be read and written to.
     *
     * @param filePath Path of file to be opened.
     */
    public Rasputin(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readFile());
        this.parser = new Parser(tasks, storage);
//        run();
    }

    /**
     * Starts the chatbot and parses input from the user until it is terminated.
     */
    public void run() {
        Ui.printGreeting();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String input = Ui.readInput();
                Command cmd = parser.parseInput(input);
                cmd.execute();
                isTerminated = cmd.isTerminated();
            } catch (RasputinException e) {
                new InvalidCommand(e.getMessage()).execute();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parseInput(input);
            return cmd.execute();
        } catch (RasputinException e) {
            return new InvalidCommand(e.getMessage()).execute();
        }

    }

    public static void main(String[] args) {
        Rasputin rasputin = new Rasputin("src/main/data/rasputin.txt");
        rasputin.run();
    }

}
