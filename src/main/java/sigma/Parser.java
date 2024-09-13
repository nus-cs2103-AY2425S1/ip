package sigma;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sigma.task.*;

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
     * @return The greeting message
     */
    public String greet() {
        return "hello, I'm Sigma, your personal chatbot";
    }

    /**
     * Greets the user goodbye and closes the program
     */
    public String goodbye() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "leaving so soon? goodbye!";
    }

    /**
     * Adds a To-do task to the TaskList
     * @param userInput The to-do task that the user wants to add
     */
    public String handleTodo(String userInput) {
        String description = userInput.substring(4).trim(); // trims the input away
        if (description.isEmpty()) { // handle empty input
            return "todo...todo what? let's try this again";
        }
        Task task = new ToDo(description, false);
        TaskList.addToList(task);
        try {
            writer.write(task + "\n");
        } catch (IOException exception) {
            System.err.println("an error occurred writing to file: " + exception.getMessage());
        }
        return "added todo task:\n [T][ ] " + description;
    }

    /**
     * Adds a Deadline task to the TaskList
     * @param userInput The Deadline task that the user wants to add
     */
    public String handleDeadline(String userInput) {
        Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            String dateBy = stringToDate(by);
            Task task = new Deadline(description, false, dateBy);
            TaskList.addToList(task);
            try {
                writer.write(task + "\n");
            } catch (IOException exception) {
                System.err.println("an error occurred writing to file");
            }
            return "added deadline task:\n  [D][ ] " + description + " (by: " + dateBy + ")";
        } else {
            return "is the deadline in the room with us? let's try again";
        }
    }

    /**
     * Adds an Event task to the TaskList
     * @param userInput The Event task that the user wants to add
     */
    public String handleEvent(String userInput) {
        Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String fromDate = stringToDate(from);
            String to = matcher.group(3);
            String toDate = stringToDate(to);
            Task task = new Event(description, false, fromDate, toDate);
            TaskList.addToList(task);
            try {
                writer.write(task + "\n");
            } catch (IOException e) {
                System.err.println("an error occurred writing to file: " + e.getMessage());
            }
            return "added event task:\n  [E][ ] " + description
                    + " (from: " + fromDate + " to: " + toDate + ")";
        } else {
           return "bro really thinks bro can make an empty event and get away with it, lets try again";
        }
    }

    /**
     * Marks a task as done or undone
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
                    task.setStatus(true);
                    return "task marked as done:\n" + task;
                } else if (action.equals("unmark")) {
                    task.setStatus(false);
                    return "task unmarked:\n" + task;
                }
            }
        }
        return "No such task found!";
    }

    /**
     * Converts the date provided by the user into a Date object
     * @param date The date given by the user as a String
     * @return The formatted date object
     */
    public static String stringToDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");

        return parsedDateTime.format(outputFormatter);
    }

    /**
     * Returns all tasks containing descriptions that match the given keyword(s)
     * @param userInput The keyword
     */
    public String handleFind(String userInput) {
        Pattern pattern = Pattern.compile("(find) (.+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String keyword = matcher.group(2).toLowerCase();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            // could possibly create a list in TaskList for this

            for (Task task : TaskList.getItems()) {
                if (task.getName().toLowerCase().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
            if (!matchingTasks.isEmpty()) {
//                String begin = "Here are the matching tasks in your list:\n";
//                String matching = "";
//                String end = "\n No remaining matching tasks found.";
//                for (int i = 0; i < matchingTasks.size(); i++) {
//                    matching = (i + 1) + ". " + matchingTasks.get(i).toString();
//                }
//                return begin + matching + end;
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
