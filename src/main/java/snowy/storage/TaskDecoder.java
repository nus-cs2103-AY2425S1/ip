package snowy.storage;

import snowy.data.SnowyException;
import snowy.tasklist.Deadline;
import snowy.tasklist.Event;
import snowy.tasklist.Task;
import snowy.tasklist.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskDecoder {

    public TaskDecoder() {

    };

    public Task decode(String input) throws SnowyException {
        String type = input.substring(1, 2);  // Extract the character between the first '[' and ']'
        boolean isDone = input.substring(4, 5).equals("X");
        String descriptionWithTags = input.substring(7).trim();

        String[] descriptionParts = descriptionWithTags.split(" \\| ", 2);  // Split by delimiter " | " to separate tags

        String description = descriptionParts[0].trim();  // Task description

        String tags = "";
        if (descriptionParts.length > 1) {
            tags = descriptionParts[1].trim();
        }

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            // Decode Deadline task
            String[] partsD = decodeDeadline(description);
            task = new Deadline(partsD[0], partsD[1]);
            break;
        case "E":
            // Decode Event task
            String[] partsE = decodeEvent(description);
            task = new Event(partsE[0], partsE[1], partsE[2]);
            break;
        default:
            throw new SnowyException("Invalid task type in storage");
        }

        if (isDone) {
            task.toggleStatus();
        }

        if (!tags.isEmpty()) {
            applyTags(task, tags);
        }

        return task;
    }

    private void applyTags(Task task, String parsedTags) {
        String[] tags = parsedTags.split(" ");
        for (String tag : tags) {
            task.addTag(tag);
        }
    }

    private String[] decodeDeadline(String description) throws SnowyException {
        try {
            String regex = "(.*)\\(by: (.*)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(description);

            if (matcher.matches()) {
                String taskDescription = matcher.group(1).trim();
                String dateString = matcher.group(2).trim();
                // Convert the date string back to the original format
                LocalDateTime by = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
                return new String[]{taskDescription, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))};
            } else {
                throw new SnowyException("Invalid Deadline format");
            }
        } catch (Exception e) {
            throw new SnowyException("Error decoding Deadline: " + e.getMessage());
        }
    }

    /**
     * Parses an Event description string and returns the description, from, and to dates.
     */
    private String[] decodeEvent(String description) throws SnowyException {
        try {
            // Regex to match the description, from, and to dates in the format (from: MM dd yyyy, h:mm a to: MM dd yyyy, h:mm a)
            String regex = "(.*)\\(from: (.*) to: (.*)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(description);

            if (matcher.matches()) {
                String taskDescription = matcher.group(1).trim();
                String fromString = matcher.group(2).trim();
                String toString = matcher.group(3).trim();
                // Convert the date strings back to the original format
                LocalDateTime from = LocalDateTime.parse(fromString, DateTimeFormatter.ofPattern("MM dd yyyy, h:mm a"));
                LocalDateTime to = LocalDateTime.parse(toString, DateTimeFormatter.ofPattern("MM dd yyyy, h:mm a"));
                return new String[]{
                        taskDescription,
                        from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                };
            } else {
                throw new SnowyException("Invalid Event format");
            }
        } catch (Exception e) {
            throw new SnowyException("Error decoding Event: " + e.getMessage());
        }
    }

}

