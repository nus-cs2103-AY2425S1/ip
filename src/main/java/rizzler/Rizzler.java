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
        rizzlerSpeech.say(new GreetCommand().execute(storage, taskLog));

        // interact with user
        boolean userIsDone = false;
        while (!userIsDone) {
            Command userCommand = parser.processInput();
            rizzlerSpeech.say(userCommand.execute(storage, taskLog));
            userIsDone = userCommand.shouldEnd();
        }

        // say bye to the user
        rizzlerSpeech.say(new ByeCommand().execute(storage, taskLog));
        parser.close();
    }
}