package parser;

import task.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import exceptions.TaskNotFoundException;
import exceptions.MissingDeadlineException;
import exceptions.MissingEventTimeException;

public class Parser {
    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }

    public static Task getTask(ArrayList<Task> taskList, int taskNum) throws TaskNotFoundException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new TaskNotFoundException("Task not found.\n");
        }
        return taskList.get(taskNum - 1);
    }

    public static String concatenate(String[] parts, int start) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < parts.length - 1; i++) {
            result.append(parts[i]).append(" ");
        }
        result.append(parts[parts.length - 1]);
        return result.toString();
    }

    public static String concatenateUntil(String[] parts, String delimiter) throws MissingDeadlineException {
        StringBuilder result = new StringBuilder();
        int i = 1;
        while (!parts[i].equals(delimiter)) {
            result.append(parts[i]).append(" ");
            i++;
            if (i == parts.length) {
                throw new MissingDeadlineException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        return result.toString().trim();
    }

    public static String getFollowingDate(String[] parts, String delimiter) throws MissingEventTimeException {
        int i = 0;
        while (!parts[i].equals(delimiter)) {
            i++;
            if (i == parts.length) {
                throw new MissingEventTimeException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        i++;
        if (i == parts.length) {
            throw new MissingEventTimeException("Please provide a date/time after `" + delimiter + "`.\n");
        }

        return concatenate(parts, i);
    }

    public static String getFollowingDate(String[] parts, String delimiter, String stopDelimiter) throws MissingEventTimeException {
        int i = 0;
        while (!parts[i].equals(delimiter)) {
            i++;
            if (i == parts.length) {
                throw new MissingEventTimeException("Missing `" + delimiter + "` or incorrect format.\n");
            }
        }
        i++;
        if (i == parts.length || parts[i].equals(stopDelimiter)) {
            throw new MissingEventTimeException("Please provide a date/time after `" + delimiter + "`.\n");
        }

        StringBuilder date = new StringBuilder();
        while (i < parts.length && !parts[i].equals(stopDelimiter)) {
            date.append(parts[i]).append(" ");
            i++;
        }
        return date.toString().trim();
    }

    public static String dateConverter(String date) {
        String[] words = date.split("\\s+");
        LocalDate parsedDate = LocalDate.parse(words[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = parsedDate.format(formatter);
        words[0] = formattedDate;
        return String.join(" ", words);
    }
}
