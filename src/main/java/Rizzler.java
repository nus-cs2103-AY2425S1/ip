import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;


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