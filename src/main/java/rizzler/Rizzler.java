package rizzler;

import rizzler.command.ByeCommand;
import rizzler.command.Command;
import rizzler.command.GreetCommand;

import rizzler.task.TaskLog;

import rizzler.ui.RizzlerSpeech;
import rizzler.ui.parser.Parser;

public class Rizzler {

    public static void main(String[] args) {
        Parser parser = new Parser();
        RizzlerSpeech rizzlerSpeech = new RizzlerSpeech();
        Storage storage = new Storage();
        TaskLog taskLog = storage.getTasks();

        // greet user
        new GreetCommand().execute(rizzlerSpeech, storage, taskLog);

        // interact with user
        boolean userIsDone = false;
        while (!userIsDone) {
            Command userCommand = parser.processInput();
            userCommand.execute(rizzlerSpeech, storage, taskLog);
            userIsDone = userCommand.shouldEnd();
        }

        // say bye to the user
        new ByeCommand().execute(rizzlerSpeech, storage, taskLog);
        parser.close();
    }
}