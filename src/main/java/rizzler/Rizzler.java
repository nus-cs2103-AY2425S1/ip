package rizzler;

import rizzler.command.ByeCommand;
import rizzler.command.Command;
import rizzler.command.GreetCommand;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;
import rizzler.ui.parser.Parser;

/**
 * Represents the entire chatbot.
 * Rizzler helps you to manage your tasks, and is definitely not interested in you.
 */
public class Rizzler {
    private boolean userIsDone = false;
    private Parser parser;
    private RizzlerSpeech rizzlerSpeech;
    private Storage storage;
    private TaskLog taskLog;

    /**
     * Constructor for a Rizzler instance.
     */
    public Rizzler() {
        parser = new Parser();
        storage = new Storage();
        taskLog = storage.getTasks();
        rizzlerSpeech = new RizzlerSpeech();
    }

    /**
     * Main method for the class to be executed upon startup.
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Rizzler().run();
    }

    /**
     * Executes continuously once run, as long as the user does not say "bye".
     * Wrapper for all the logic within the chatbot at the highest level.
     */
    public void run() {
        // greet user
        rizzlerSpeech.say(new GreetCommand().execute(storage, taskLog));

        // interact with user
        while (!userIsDone) {
            Command userCommand = parser.processInput();
            rizzlerSpeech.say(userCommand.execute(storage, taskLog));
            userIsDone = userCommand.shouldEnd();
        }

        // say bye to the user
        rizzlerSpeech.say(new ByeCommand().execute(storage, taskLog));
        parser.close();
    }

    public String getResponse(String userInput) {
        StringBuilder response = new StringBuilder();
        Command userCommand = parser.processInput(userInput);
        String[] responseLines = userCommand.execute(storage, taskLog);
        for (String responseLine : responseLines) {
            response.append(responseLine);
            response.append("\n");
        }
        return response.toString();
    }
}
