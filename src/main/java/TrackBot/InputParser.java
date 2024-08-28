package TrackBot;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class InputParser {

    public static void checkUserInput(String userInput, TrackList trackList) throws TrackBotException, IOException {
        if (userInput.equalsIgnoreCase("list")) {
            trackList.listToString();
        } else if (userInput.equalsIgnoreCase("how are you?")) {
            System.out.println("I'm good, thank you. How about you?");
        } else if (userInput.equalsIgnoreCase("bye")) {
            // 
        } else if (userInput.toLowerCase().startsWith("mark")) {
            String input = userInput.substring(4).trim();
            if (input.isEmpty()) {
                throw TrackBotException.invalidFormat("mark", "mark <task number>");
            } else {
                try {
                    int num = Integer.parseInt(input) - 1;
                    trackList.markTask(num);
                } catch (NumberFormatException e) {
                    throw TrackBotException.invalidFormat("mark", "mark <task number>");
                }
            }
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            String input = userInput.substring(6).trim();
            if (input.isEmpty()) {
                throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
            } else {
                try {
                    int num = Integer.parseInt(input) - 1;
                    trackList.unmarkTask(num);
                } catch (NumberFormatException e) {
                    throw TrackBotException.invalidFormat("unmark", "unmark <task number>");
                }
            }
        } else if (userInput.toLowerCase().startsWith("todo")) {
            String taskDescription = userInput.substring(4).trim();
            if (taskDescription.isEmpty()) {
                throw TrackBotException.invalidFormat("todo", "todo <task description>");
            }
            ToDo todo = new ToDo(taskDescription);
            trackList.addToList(todo);
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            String[] parts = userInput.substring(8).trim().split(" /by ", 2);
            if (parts.length < 2) {
                throw TrackBotException.invalidFormat("deadline", "deadline <task description> /by <date/time>");
            }
            Deadline deadline = new Deadline(parts[0], parts[1]);
            trackList.addToList(deadline);
        } else if (userInput.toLowerCase().startsWith("event")) {
            String[] parts = userInput.substring(5).trim().split(" /from | /to ", 3);
            if (parts.length < 3) {
                throw TrackBotException.invalidFormat("event", "event <description> /from <start> /to <end>");
            }
            Event event = new Event(parts[0], parts[1], parts[2]);
            trackList.addToList(event);
        } else if (userInput.toLowerCase().startsWith("delete")) {
            String input = userInput.substring(6).trim();
            if (input.isEmpty()) {
                throw TrackBotException.invalidFormat("delete", "delete <task number>");
            } else {
                try {
                    int num = Integer.parseInt(input) - 1;
                    trackList.deleteFromList(num);
                } catch (NumberFormatException e) {
                    throw TrackBotException.invalidFormat("delete", "delete <task number>");
                }
            }
        } else {
            throw new TrackBotException("Sorry, I did not understand that command.");
        }
    }

    public static String checkDateFormat(String time) {
        LocalDate date;
        LocalDateTime dateTime;
        // Match dates in the format "MM-DD-YYYY"
        String regex_m = "^(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-([0-9]{4})$";
        // Match dates in the format "YYYY-MM-DD"
        String regex_y = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
        // Match dates in the format "DD-MMM-YYYY"
        String regex_d = "^(0?[1-9]|[12][0-9]|3[01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-([0-9]{4})$";
        // Match dates in the format "YYYY-MM-DD HH:MM"
        String regex_t1 = "^([0-9]{4})-([0-9]{2})-([0-9]{2})\\s((2[0-3])|([0-1][0-9])):[0-5][0-9]$";
        // Match dates in the format "YYYY-MM-DD HH:MM:SS"
        String regex_t2 = "^([0-9]{4})-([0-9]{2})-([0-9]{2})\\s((2[0-3])|([0-1][0-9])):[0-5][0-9]:[0-5][0-9]$";
        try {
            // Reformat as date only
            if (Pattern.matches(regex_m, time) || Pattern.matches(regex_y, time) || Pattern.matches(regex_d, time)) {
                date = LocalDate.parse(time);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return time;
            }

//          // Reformat as date and time only
            if (Pattern.matches(regex_t1, time)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                dateTime = LocalDateTime.parse(time, formatter);
                time = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
                return time;
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        return time;
    }
}
