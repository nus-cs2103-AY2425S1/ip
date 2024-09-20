package storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import chatterboxexceptions.ChatterboxExceptions;
import parser.Parser;
import tags.Tag;
import tags.TagList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Handles the storage of Task history
 */
public class Storage {

    private final String HIST_FILE;

    /**
     * Initializes Storage class with no specified storage file, creates a data directory and storage file command1
     */
    public Storage() {

        checkDirectory();
        HIST_FILE = Paths.get(System.getProperty("user.dir"), "data", "command1.txt").toString();
    }

    /**
     * Initializes with path to txt file containing commands
     * @param filePath path to a file with command inputs
     */
    public Storage(String filePath) {
        this.HIST_FILE = filePath;
    }

    /**
     * Saves the input arraylist
     * @param userList contains ArrayList of task
     */
    public void saveHistory(ArrayList<Task> userList) {

        checkDirectory();

        File file = new File(HIST_FILE);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }


            FileWriter writer = new FileWriter(file);

            StringBuilder history = new StringBuilder();
            for (int i = 0; i < userList.size(); i++) {

                Task currentTask = userList.get(i);
                String taskStr = getStoredString(currentTask);
                history.append(taskStr);
                history.append(System.lineSeparator());
            }

            writer.write(history.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Gets the string representation of a task
     * @param currentTask
     * @return string representation of task
     */
    private static String getStoredString(Task currentTask) {
        String taskStr = String.format("%s | %s | %s", currentTask.getTaskSymbol(),
                currentTask.getStatus() ? "X" : " ", currentTask.getDescription());
        return taskStr;
    }

    /**
     * Loads the
     *
     * @param parser parser used to parse input
     * @param loadedTasks ArrayList to store tasks
     * @param loadedTags TagList to store tags
     * @throws FileNotFoundException
     */
    public void load(Parser parser, ArrayList<Task> loadedTasks, TagList loadedTags) throws FileNotFoundException {
        File f = new File(this.HIST_FILE);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            try {
                String nextLine = s.nextLine();
                //parse the Line for task
                parseTask(parser, nextLine, loadedTasks, loadedTags);

            } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
                System.out.println("Error: " + e.getMessage());

            }

        }
    }

    /**
     * Parses the task from the input string [Symbol] |   | [Text] ( from ... to )/tags ...
     *
     * @param parser
     * @param nextLine
     * @param loadedTasks
     * @throws ChatterboxExceptions.ChatterBoxNoInput
     */
    protected void parseTask(Parser parser, String nextLine, ArrayList<Task> loadedTasks, TagList loadedTags)
            throws ChatterboxExceptions.ChatterBoxNoInput {
        char type = nextLine.charAt(0);
        boolean status = nextLine.charAt(4) == 'X';

        int tagStart = nextLine.indexOf("/tags");


        Set<Tag> taskTagSet = new HashSet<>(); //stores the current tags for this task

        extractTags(nextLine, loadedTags, tagStart, taskTagSet);
        //rest includes text ( deadline/event )
        String rest = nextLine.substring(8); //moves to start of description
        Task nextTask;
        if (type == 'T') {
            nextTask = loadTask(tagStart, rest);

        } else if (type == 'D') {
            nextTask = loadDeadline(parser, rest, tagStart);
        } else {
            nextTask = loadEvent(parser, rest, tagStart);
        }
        if (status) {
            nextTask.setStatus(true);
        }
        for (Tag tag : taskTagSet) {
            nextTask.addTag(tag);
            tag.tagTask(nextTask);
        }
        loadedTasks.add(nextTask);

    }

    private static void extractTags(String nextLine, TagList loadedTags, int tagStart, Set<Tag> taskTagSet) {
        if (tagStart != -1) { //if /tag found
            String tags = nextLine.substring(tagStart + 6);
            String[] tagList = tags.split(" ");

            for (String tag : tagList) {

                Tag nextTag = loadedTags.addTagFromString(tag);
                taskTagSet.add(nextTag);
            }

        }
    }

    private static Event loadEvent(Parser parser, String rest, int tagStart)
            throws ChatterboxExceptions.ChatterBoxNoInput {
        Event nextTask;
        int startBracket = rest.indexOf("( from");
        int toStart = rest.indexOf("to ");
        if (startBracket < 0 || toStart < 0) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Error loading event");
        }
        String desc = rest.substring(0, startBracket).trim();
        String startDate = getStartDate(rest, startBracket, toStart);
        LocalDateTime startDateObj = parser.parseDateTime(startDate);
        String endDate;
        endDate = getEndDate(rest, tagStart, toStart);
        LocalDateTime endDateObj = parser.parseDateTime(endDate);


        if (startDateObj != null && endDateObj != null) {
            nextTask = new Event(desc, startDateObj, endDateObj);
        } else {
            nextTask = new Event(desc, startDate, endDate);
        }
        return nextTask;
    }

    private static String getStartDate(String rest, int startBracket, int toStart) {
        return rest.substring(startBracket + 7, toStart).trim();
    }

    private static String getEndDate(String rest, int tagStart, int toStart) {
        String endDate;
        if (tagStart == -1) {
            endDate = rest.substring(toStart + 3, rest.length() - 2).trim();

        } else {
            int bracketEnd = rest.indexOf(") /tags");

            endDate = rest.substring(toStart + 3, bracketEnd).trim();
        }
        return endDate;
    }

    private static Deadline loadDeadline(Parser parser, String rest, int tagStart)
            throws ChatterboxExceptions.ChatterBoxNoInput {
        Deadline nextTask;
        int startBracket = rest.indexOf("( by");
        int bracketEnd = rest.indexOf(") /tags");
        if (bracketEnd == -1) { //if no tags
            bracketEnd = rest.length() - 2;
        }
        if (startBracket < 0 || bracketEnd < 0) {
            throw new ChatterboxExceptions.ChatterBoxNoInput("Error loading deadline");
        }
        String desc = rest.substring(0, startBracket).trim();
        String deadline;
        if (tagStart == -1) {
            deadline = rest.substring(startBracket + 5, rest.length() - 2).trim();

        } else {
            deadline = rest.substring(startBracket + 5, bracketEnd).trim();
        }
        LocalDateTime deadlineObj = parser.parseDateTime(deadline);


        if (deadlineObj == null) {
            nextTask = new Deadline(desc, deadline);

        } else {
            nextTask = new Deadline(desc, deadlineObj);
        }
        return nextTask;
    }

    private static Todo loadTask(int tagStart, String rest) throws ChatterboxExceptions.ChatterBoxNoInput {
        Todo nextTask;
        if (tagStart != -1) { //if tags are present
            int end = rest.indexOf("/tags");
            rest = rest.substring(0, end);
        }
        nextTask = new Todo(rest.trim());
        return nextTask;
    }


    /**
     * Used to check for directory used to store data and create if not present,
     *
     */
    private static void checkDirectory() {

        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir"), "data"));
        } catch (IOException e) {
            System.out.println("Error creating data directory: " + e.getMessage());
        }
    }

    /**
     * Gets the HIST_FILE path string
     * @return HIST_FILE path string
     */
    public String getHistFilePath() {
        return HIST_FILE;
    }




}
