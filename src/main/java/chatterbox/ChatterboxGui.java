package chatterbox;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import chatterboxexceptions.ChatterboxExceptions;
import command.Command;
import gui.GuiResponses;
import parser.Parser;
import storage.Storage;
import tags.TagList;
import tasks.Task;
import tasks.TaskList;

/**
 * Main class that encapsulates all gui chatbot functionality
 */
public class ChatterboxGui {
    private final GuiResponses guiResponses;
    private final Parser parser;
    private final Storage storage;

    private final TaskList tasks;
    private final TagList userTags;

    /**
     * Initiates ChatterboxGui with a prior history filepath
     * @param filepath contains the history of tasks
     */
    public ChatterboxGui(String filepath) {
        assert filepath != null;

        this.guiResponses = new GuiResponses();
        this.parser = new Parser();
        this.storage = new Storage(filepath);
        this.userTags = new TagList();
        ArrayList<Task> loadedTask = new ArrayList<>();
        TagList loadedTags = new TagList();
        try {
            storage.load(parser, loadedTask, loadedTags);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No history file found at path");
        }


        this.tasks = new TaskList(loadedTask);


    }

    /**
     * Initiates ChatterboxGui with no prior history
     */
    public ChatterboxGui() {
        this.guiResponses = new GuiResponses();

        this.parser = new Parser();
        this.storage = new Storage();
        ArrayList<Task> loaded = new ArrayList<>();
        TagList loadedTags = new TagList();

        try {
            storage.load(parser, loaded, loadedTags);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No history file found at path");
        }

        this.tasks = new TaskList(loaded);
        this.userTags = loadedTags;

    }


    /**
     * Checks if the ChatterboxGui instance has any tasks
     * @return true if there are tasks else false
     */
    public boolean hasTasks() {
        if (tasks.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * Processes the user input to return the appropriate response
     */
    public HashMap<String, String> processInput(String input) {
        input = input.trim();
        Command currCommand = parser.parseCommandType(input);

        HashMap<String, String> response = new HashMap<String, String>();
        String result;
        try {
            result = currCommand.execute(input, guiResponses, userTags, tasks, parser);
            response.put("type", "OK");
        } catch (ChatterboxExceptions.ChatterBoxError e) {

            result = guiResponses.getErrorMessage(e.getMessage());
            response.put("type", "ERROR");
        }
        response.put("response", result);

        storage.saveHistory(tasks.getTasks());

        return response;
    }




    /**
     * Gets the greeting string
     * @return greeting String
     */
    public String getGreeting() {
        return guiResponses.greeting();
    }

    public String getGoodbye() {
        return guiResponses.goodbye();
    }
    /**
     * Gets the name of the chatbot
     * @return name of the chatbot
     */
    public String getName() {
        return "Chatterbox";
    }
}
