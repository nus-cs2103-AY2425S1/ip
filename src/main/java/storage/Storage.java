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
        System.out.println("loading");
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
            System.out.println("Error has occurred " + e.getMessage());

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

        try {
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                //parse the Line for task
                parseTask(parser, nextLine, loadedTasks, loadedTags);


            }
        } catch (ChatterboxExceptions.ChatterBoxNoInput e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(String.format("Previous Task list of size: %d Loaded", loadedTasks.size()));

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

        // parse tags if available
        Set<Tag> taskTagSet = new HashSet<>(); //stores the current tags for this task

        if (tagStart != -1) {
            String tags = nextLine.substring(tagStart + 7);
            String[] tagList = tags.split(" ");
//            System.out.println("parsed tags");
//            System.out.println(tagList.length);
            for (String tag : tagList) {
//                System.out.println("Tag: " + tag);

                Tag nextTag = loadedTags.addTagFromString(tag);
                taskTagSet.add(nextTag);
            }

        }
        //rest includes text ( deadline/event )
        String rest = nextLine.substring(8); //moves to start of description

        if (type == 'T') {
            if (tagStart != -1) { //if tags are present
                int end = rest.indexOf("/tags");
                rest = rest.substring(0, end);
            }
            Todo currTodo = new Todo(rest.trim());
            if (status) {
                currTodo.setStatus(true);
            }
            //add tags to todo
            for (Tag tag : taskTagSet) {
                currTodo.addTag(tag);
            }
            loadedTasks.add(currTodo);
        } else if (type == 'D') {
            int startBracket = rest.indexOf("( by");
            int bracketEnd = rest.indexOf(") /tags");
            String desc = rest.substring(0, startBracket).trim();
            String deadline;
            if (tagStart == -1) {
                deadline = rest.substring(startBracket + 5, rest.length() - 2).trim();

            } else {

                deadline = rest.substring(startBracket + 5, bracketEnd).trim();
//                System.out.println(deadline);
            }
            LocalDateTime deadlineObj = parser.parseDateTime(deadline);
            Deadline newDead;

            if (deadlineObj == null) {
                newDead = new Deadline(desc, deadline);

            } else {
                newDead = new Deadline(desc, deadlineObj);
            }
            if (status) {
                newDead.setStatus(true);
            }
            for (Tag tag : taskTagSet) {
                newDead.addTag(tag);
            }
            loadedTasks.add(newDead);

        } else {
            int startBracket = rest.indexOf("( from");
            int toStart = rest.indexOf("to");
            String desc = rest.substring(0, startBracket).trim();
            String startDate = rest.substring(startBracket + 7, toStart).trim();
            LocalDateTime startDateObj = parser.parseDateTime(startDate);
            String endDate;
            if (tagStart == -1) {
                endDate = rest.substring(toStart + 3, rest.length() - 2).trim();

            } else {
                int bracketEnd = rest.indexOf(") /tags");

                endDate = rest.substring(toStart + 3, bracketEnd).trim();
            }
            LocalDateTime endDateObj = parser.parseDateTime(endDate);

            Event nextEvent;
            if (startDateObj != null && endDateObj != null) {
                nextEvent = new Event(desc, startDateObj, endDateObj);
            } else {
                nextEvent = new Event(desc, startDate, endDate);
            }

            if (status) {
                nextEvent.setStatus(true);
            }

            for (Tag tag : taskTagSet) {
                nextEvent.addTag(tag);
            }
            loadedTasks.add(nextEvent);

        }
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
