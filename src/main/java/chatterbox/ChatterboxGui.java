package chatterbox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import chatterboxexceptions.ChatterboxExceptions;
import command.Command;
import gui.GuiResponses;
import parser.Parser;
import storage.Storage;
import tags.TagList;
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
    private final TagList userTags;

    /**
     * initiates Chatterbox with a prior history filepath
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
     * Initiates Chatterbox with no prior history
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
    //    public String processInput(String input) {
    //
    //
    //        String result = ""; //used to store gui response
    //        input = input.trim();
    //        try {
    //            Parser.ValidCommand command = parser.parseCommand(input);
    //
    //            int index;
    //            switch (command) {
    //
    //            case BYE:
    //                result = null;
    //                break;
    //            case LIST:
    //
    //                result = guiResponses.displayList(tasks);
    //                break;
    //
    //            case MARK:
    //
    //                index = parser.extractNum(input) - 1; // -1 as the display  start from 1
    //                result = guiResponses.markMsg(tasks.markTask(index));
    //                break;
    //
    //
    //            case UNMARK:
    //
    //                index = parser.extractNum(input) - 1; // -1 as the display  start from 1
    //                result = guiResponses.unmarkMsg(tasks.unmarkTask(index));
    //                break;
    //
    //
    //            case TODO:
    //
    //
    //                tasks.addTodo(parser.parseTodo(input));
    //                result = guiResponses.addTaskMsg("Todo", tasks.size());
    //                break;
    //
    //            case DEADLINE:
    //                String[] parsed = parser.parseDeadline(input);
    //
    //
    //                LocalDateTime deadlineDate = parser.parseDateTime(parsed[1]);
    //
    //
    //                if (deadlineDate == null) {
    //
    //                    tasks.addDeadline(parsed[0], parsed[1]);
    //
    //                } else {
    //                    //add back by for string
    //
    //                    tasks.addDeadline(parsed[0], deadlineDate);
    //                }
    //
    //                result = guiResponses.addTaskMsg("Deadline", tasks.size());
    //                break;
    //
    //            case EVENT:
    //                String[] eventParsed = parser.parseEvent(input);
    //                System.out.println(eventParsed[1]);
    //                System.out.println(eventParsed[2]);
    //                LocalDateTime startDate = parser.parseDateTime(eventParsed[1]); //from 4
    //                LocalDateTime endDate = parser.parseDateTime(eventParsed[2]);
    //                if (startDate == null || endDate == null) {
    //
    //                    tasks.addEvent(eventParsed[0].trim(), eventParsed[1], eventParsed[2]);
    //
    //                } else {
    //                    tasks.addEvent(eventParsed[0].trim(), startDate, endDate);
    //                }
    //                System.out.println("Event added");
    //                result = guiResponses.addTaskMsg("Event", tasks.size());
    //                break;
    //
    //            case DELETE:
    //                int delIndex = parser.extractNum(input) - 1;
    //                result = guiResponses.delTaskMsg(tasks.deleteTask(delIndex), tasks.size());
    //                break;
    //
    //            case FIND:
    //                String keywords = parser.parseFind(input).trim();
    //
    //                ArrayList<Task> matches = tasks.findTasks(keywords);
    //                result = guiResponses.getSearchList(matches);
    //                break;
    //
    //            case TAG:
    //                // input for tag will be tag /i{index} /t{text}
    //                String tagText = parser.parseTagText(input);
    //                int tagIndex = parser.parseTagIndex(input) - 1;
    //                Tag tag;
    //                if (userTags.containsTag(tagText)) {
    //                    tag = userTags.getTag(tagText); //finds the tag
    //                    tasks.getTask(tagIndex).addTag(tag); //adds tag to the task object
    //                    tag.tagTask(tasks.getTask(tagIndex)); //adds task to the tag object
    //
    //
    //                    result = guiResponses.taggedTasks(tasks.getTask(tagIndex), tagText);
    //
    //                    break;
    //                }
    //                tag = new Tag(tagText);
    //                userTags.addTag(tag);
    //                tasks.getTask(tagIndex).addTag(new Tag(tagText));
    //                result = guiResponses.taggedTasks(tasks.getTask(tagIndex), tagText);
    //                break;
    //
    //            case ALLTAGS:
    //
    //                result = guiResponses.displayAllTags(userTags.getAllTags());
    //                break;
    //            default:
    //                result = "Error occured...";
    //                ChatterboxExceptions.checkMessage(input);
    //
    //                break;
    //
    //            }
    //
    //        } catch (ChatterboxExceptions.ChatterBoxError e) {
    //            result = ("Sorry there was an error: " + e.getMessage());
    //        }
    //
    //        storage.saveHistory(tasks.getTasks());
    //        return result;
    //    }

    public String processInput(String input) {
        input = input.trim();
        Command currCommand = parser.parseCommandType(input);
        String result = null;
        try {
            result = currCommand.execute(input, guiResponses, userTags, tasks, parser);
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

}
