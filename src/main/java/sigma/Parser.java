package sigma;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sigma.task.Task;
import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.ToDo;

/**
 * Class that handles the logic relating to user commands
 */
public class Parser {
    private FileWriter writer;

    /**
     * Constructor for a Parser
     */
    public Parser() {
        try {
            writer = new FileWriter("data/sigma.txt", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Greets the user upon command
     *
     * @return The greeting message
     */
    public String greet() {
        return "Hello, I'm your personal skibidi sigma chatbot. What can I gyat for you?";
    }

    /**
     * Greets the user goodbye and closes the program
     */
    public String goodbye() {
        try {
            Storage.saveToFile();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "leaving so soon? goodbye!";
    }

    /**
     * Adds a To-do task to the TaskList
     *
     * @param userInput The to-do task that the user wants to add
     */
    public String handleTodo(String userInput) {
        String description = userInput.substring(4).trim(); // trims the input away
        if (description.isEmpty()) {
            return "todo...todo what? let's try this again";
        }
        Task task = new ToDo(description, false);
        TaskList.addToList(task);
        return "added todo task:\n [T][ ] " + description;
    }

    /**
     * Adds a Deadline task to the TaskList
     *
     * @param userInput The Deadline task that the user wants to add
     */
    public String handleDeadline(String userInput) {
        Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            String dateBy = convertStringToDate(by);
            Task task = new Deadline(description, false, dateBy);
            TaskList.addToList(task);
            return "added deadline task:\n" + task;
        } else {
            return "is the deadline in the room with us? let's try again";
        }
    }

    /**
     * Adds an Event task to the TaskList
     *
     * @param userInput The Event task that the user wants to add
     */
    public String handleEvent(String userInput) {
        Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String fromDate = convertStringToDate(from);
            String to = matcher.group(3);
            String toDate = convertStringToDate(to);
            Task task = new Event(description, false, fromDate, toDate);
            TaskList.addToList(task);
            return "added event task:\n" + task;
        } else {
           return "bro really thinks bro can make an empty event and get away with it, lets try again";
        }
    }

    /**
     * Marks a task as done or undone
     *
     * @param userInput The task that the user wants to mark or unmark
     */
    public String handleMarkUnmark(String userInput) {
        Pattern pattern = Pattern.compile("(mark|unmark) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String action = matcher.group(1);
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;
            if (taskNumber >= 0 && taskNumber < TaskList.getSize()) {
                Task task = TaskList.getItems().get(taskNumber);
                if (action.equals("mark")) {
                    task.setDone(true);
                    return "task marked as done:\n" + task;
                } else if (action.equals("unmark")) {
                    task.setDone(false);
                    return "task unmarked:\n" + task;
                }
            } else {
                return "Invalid task number entered, try again!";
            }
        }
        return "No such task found, try again!";
    }

    /**
     * Deletes the task that the user specifies
     *
     * @param userInput The task that the user wants to delete
     */
    public String handleDelete(String userInput) {
        Pattern pattern = Pattern.compile("(delete) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;
            if (taskNumber >= 0 && taskNumber < TaskList.getSize()) {
                Task task = TaskList.get(taskNumber);
                TaskList.remove(task);
                // write to file also
                return "task removed:\n" + task.toString() + "\nNow you have " + TaskList.getSize() + " tasks in the list";
            } else {
                return "Invalid task number, try again!";
            }
        }
        return "No sussy tasks found!";
    }

    /**
     * Converts the date provided by the user into a Date object
     *
     * @param date The date given by the user as a String
     * @return The formatted date object
     */
    public static String convertStringToDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(date, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
            return parsedDateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return "Invalid date format, please use the format yyyy-MM-dd HH:mm:ss";
        }

    }

    /**
     * Returns all tasks containing descriptions that match the given keyword(s)
     *
     * @param userInput The keyword
     */
    public String handleFind(String userInput) {
        Pattern pattern = Pattern.compile("(find) (.+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String keyword = matcher.group(2).toLowerCase();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : TaskList.getItems()) {
                if (task.getName().toLowerCase().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
            if (!matchingTasks.isEmpty()) {
                StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    result.append((i + 1)).append(". ").append(matchingTasks.get(i).toString()).append("\n");
                }
                return result.toString();
            }

        }
        return "\n No remaining matching tasks found.";
    }
}
