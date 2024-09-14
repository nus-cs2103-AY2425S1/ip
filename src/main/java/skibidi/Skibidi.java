package skibidi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gui.MainGui;
import javafx.application.Application;
import skibidi.CommandParser.CommandParseException;
import skibidi.task.AbstractTask.TaskValidationException;

/** Driver class for the chatbot application. */
public class Skibidi {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final CommandParser parser;

    /** Constructor for Skibidi chatbot instance. */
    public Skibidi(String dataPath) {
        storage = new Storage(dataPath);
        taskList = new TaskList(storage.loadTasksFromDisk());
        ui = new Ui();
        parser = new CommandParser();
    }

    /** Start terminal session for Skibidi chatbot. */
    public void start() {
        System.out.println(Ui.getWelcomeMessage());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            try {
                String line = reader.readLine();
                if (parser.isExit(line)) {
                    System.out.println(Ui.getExitMessage());
                    break;
                }
                System.out.println(getResponse(line));
            } catch (IOException e) {
                System.out.println(e.toString());
            }
            System.out.println(Ui.getSeparator());
        }
    }

    /** Parse input command and get string message response. */
    public String getResponse(String input) {
        if (parser.isExit(input)) {
            return Ui.getExitMessage();
        }
        try {
            String message = parser.parseCommand(input).execute(taskList, storage, ui);
            storage.saveTasksToDisk(taskList);
            return message;
        } catch (CommandParseException | TaskValidationException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        // combine welcome message with input arguments
        String[] launchArgs = new String[args.length + 1];
        launchArgs[0] = Ui.getWelcomeMessage();
        System.arraycopy(args, 0, launchArgs, 1, args.length);
        Application.launch(MainGui.class, launchArgs);
    }
}
