import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import commands.Command;
import exceptions.BrockException;
import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import utility.Pair;

/**
 * Class that represents the chatbot.
 */
public class Brock {
    private static final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage();
    private static final Parser PARSER = new Parser();

    protected Pair<Boolean, String> createSaveFile() {
        boolean isSuccessful;
        String overallResponse;
        try {
            String[] responses = STORAGE.createFile();
            isSuccessful = true;
            overallResponse = responses[0] + " \\| " + responses[1];

        } catch (IOException e) {
            isSuccessful = false;
            overallResponse = "Error creating file!\n"
                    + "Please re-run the program and try again!"
                    + "Program will close now ...";
        }
        return new Pair<>(isSuccessful, overallResponse);
    }

    protected Pair<TaskList, String> loadTasksFromFile() {
        TaskList tasks;
        String overallResponse;
        try {
            ArrayList<Task> prevTasks = STORAGE.loadTasksFromFile();
            tasks = new TaskList(prevTasks);
            overallResponse = "Successfully read from save file!";

        } catch (FileNotFoundException e) {
            tasks = null;
            overallResponse = "Unable to find the save file!\n"
                    + "Please re-run the program and try again."
                    + "Program will close now ...";

        } catch (BrockException e) {
            // Save file was corrupted
            // Reset to blank file and proceed
            tasks = new TaskList(new ArrayList<>());
            overallResponse = e.getMessage();
        }
        return new Pair<>(tasks, overallResponse);
    }

    protected String getProcessedCommand(String rawCommand) {
        return UI.readCommand(rawCommand);
    }

    protected Pair<Boolean, String> respondToCommand(String processedCommand, TaskList tasks) {
        boolean isExit;
        String overallResponse;
        isExit = processedCommand.equalsIgnoreCase("bye");
        try {
            Command commandObj = PARSER.handleCommand(processedCommand);
            overallResponse = commandObj.execute(UI, STORAGE, tasks);

        } catch (BrockException e) {
            overallResponse = e.getMessage();
        }
        return new Pair<>(isExit, overallResponse);
    }
}

