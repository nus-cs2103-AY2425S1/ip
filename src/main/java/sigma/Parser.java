package sigma;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sigma.task.*;

// deals with making sense of the user command
public class Parser {
    private FileWriter writer;

    public Parser() {
        try {
            writer = new FileWriter("data/sigma.txt", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // dont really need this yet
    public void greet() {
        System.out.println("hello, I'm Sigma, your personal chatbot");
    }

    public void goodbye() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("leaving so soon? goodbye!");
        }


    }

    // make this return a task instead
    public void handleTodo(String userInput) {
        String description = userInput.substring(4).trim(); // trims the input away
        if (description.isEmpty()) { // handle empty input
            System.out.println("todo...todo what? let's try this again");
            return;
        }
        Task task = new ToDo(description, false);
        TaskList.addToList(task);
        try {
            writer.write(task + "\n");
        } catch (IOException exception) {
            System.err.println("an error occurred writing to file: " + exception.getMessage());
        }
        System.out.println("added todo task:\n [T][ ] " + description);
    }

    public void handleDeadline(String userInput) {
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
            System.out.println("added deadline task:\n  [D][ ] " + description + " (by: " + dateBy + ")");
        } else {
            System.out.println("is the deadline in the room with us? let's try again");
        }
    }

    public void handleEvent(String userInput) {
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
            System.out.println("added event task:\n  [E][ ] " + description + " (from: " + fromDate + " to: " + toDate + ")");
        } else {
            System.out.println("bro really thinks bro can make an empty event and get away with it, lets try again");
        }
    }

    public void handleMarkUnmark(String userInput) {
        Pattern pattern = Pattern.compile("(mark|unmark) (\\d+)");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String action = matcher.group(1);
            int taskNumber = Integer.parseInt(matcher.group(2)) - 1;

            if (taskNumber >= 0 && taskNumber < TaskList.getSize()) {
                Task task = TaskList.getItems().get(taskNumber);
                if (action.equals("mark")) {
                    task.setStatus(true);
                    System.out.println("task marked as done:\n" + task);
                } else if (action.equals("unmark")) {
                    task.setStatus(false);
                    System.out.println("task unmarked:\n" + task);
                }
            }
        }
    }

    public static String stringToDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDateTime = LocalDateTime.parse(date, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");

        return parsedDateTime.format(outputFormatter);
    }
}
