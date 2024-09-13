package chatterbox;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;

/**
 * main class that encapsulates all gui chatbot functionality
 */
public class ChatterboxGui {
    private final GuiResponses guiResponses;
    private final Parser parser;
    private final Storage storage;

    private final TaskList tasks;

    /**
     * initiates Chatterbox with a prior history filepath
     * @param filepath contains the history of tasks
     */
    public ChatterboxGui(String filepath) {
        assert filepath != null;

        this.guiResponses = new GuiResponses();
        this.parser = new Parser();
        this.storage = new Storage(filepath);
        ArrayList<Task> loaded = new ArrayList<>();
        try {
            loaded = storage.load(parser);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No history file found at path");
        }


        this.tasks = new TaskList(loaded);


    }

    /**
     * Initiates Chatterbox with no prior history
     */
    public ChatterboxGui() {
        this.guiResponses = new GuiResponses();

        this.parser = new Parser();
        this.storage = new Storage();
        ArrayList<Task> loaded = new ArrayList<>();
        try {
            loaded = storage.load(parser);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No history file found at path");
        }

        this.tasks = new TaskList(loaded);

    }


    /**
     * checks if the Chatterbox instance has any tasks
     * @return true if there are tasks else false
     */
    public boolean hasTasks() {
        if (tasks.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Process input
     * @param input userinput from gui
     * @return Chatterbox response to the input
     */
    public String processInput(String input) {


        String result;
        input = input.trim();
        try {
            Parser.ValidCommand command = parser.parseCommand(input);

            int index;
            switch (command) {

            case BYE:
                result = null;
                break;
            case LIST:

                result = guiResponses.displayList(tasks.getTasks());
                break;

            case MARK:

                index = parser.extractNum(input) - 1; // -1 as the display  start from 1
                result = guiResponses.markMsg(tasks.markTask(index));
                break;


            case UNMARK:

                index = parser.extractNum(input) - 1; // -1 as the display  start from 1
                result = guiResponses.unmarkMsg(tasks.unmarkTask(index));
                break;


            case TODO:


                tasks.addTodo(parser.parseTodo(input));
                result = guiResponses.addTaskMsg("Todo", tasks.size());
                break;

            case DEADLINE:
                String[] parsed = parser.parseDeadline(input);


                LocalDateTime deadlineDate = parser.parseDateTime(parsed[1].substring(2));


                if (deadlineDate == null) {

                    tasks.addDeadline(parsed[0], parsed[1]);

                } else {
                    //add back by for string

                    tasks.addDeadline(parsed[0], deadlineDate);
                }

                result = guiResponses.addTaskMsg("Deadline", tasks.size());
                break;

            case EVENT:
                String[] eventParsed = parser.parseEvent(input);

                LocalDateTime startDate = parser.parseDateTime(eventParsed[1].substring(4)); //from 4
                LocalDateTime endDate = parser.parseDateTime(eventParsed[2].substring(2));

                if (startDate == null || endDate == null) {

                    tasks.addEvent(eventParsed[0].trim(), eventParsed[1], eventParsed[2]);

                } else {
                    tasks.addEvent(eventParsed[0].trim(), startDate, endDate);
                }
                result = guiResponses.addTaskMsg("Event", tasks.size());
                break;

            case DELETE:
                int delIndex = parser.extractNum(input) - 1;
                result = guiResponses.delTaskMsg(tasks.deleteTask(delIndex), tasks.size());
                break;

            case FIND:
                String keywords = parser.parseFind(input).trim();

                ArrayList<Task> matches = tasks.findTasks(keywords);
                result = guiResponses.getSearchList(matches);
                break;

            case TAG:
                // input for tag will be tag /i{index} /t{text}
                String tagText = parser.parseTagText(input);
                int tagIndex = parser.parseTagIndex(input) - 1;
                tasks.tagTask(tagIndex, tagText);
                break;
            default:
                result = "Error occured...";
                ChatterboxExceptions.checkMessage(input);

                break;

            }

        } catch (ChatterboxExceptions.ChatterBoxError e) {
            result = ("Sorry there was an error: " + e.getMessage());
        }

        storage.saveHistory(tasks.getTasks());
        return result;
    }


    /**
     * Dummy echo testing
     * @return repeats the string with haha:
     */
    public String getResponse(String input) {
        return "haha: " + input;
    }

    /**
     * Gets the greeting string
     * @return greeting String
     */
    public String getGreeting() {
        return guiResponses.greeting();
    }
    public static void main(String[] args) {

        Chatterbox myChat = new Chatterbox(
                Paths.get(System.getProperty("user.dir"), "data" , "command1.txt").toString());
        myChat.run();


    }
}
