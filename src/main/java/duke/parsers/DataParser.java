package duke.parsers;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    public static ArrayList<Task> readTasksFromFile(Path filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                System.out.println(line);

                Task task = parseTaskFromLine(line);
                if (task != null && task.isReal()) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    private static Task parseTaskFromLine(String line) {
        // Remove the numbering at the beginning of the line
        line = line.replaceFirst("^\\d+\\.\\s*", "");

        Pattern todoPattern = Pattern.compile("^\\[T\\](\\[[ X]\\])\\s+(.+)$");
        Pattern deadlinePattern = Pattern.compile("^\\[D\\](\\[[ X]\\])\\s+(.+)\\s+\\(by:\\s+(.+)\\)$");
        Pattern eventPattern = Pattern.compile("^\\[E\\](\\[[ X]\\])\\s+(.+)\\s+\\(from:\\s+(.+)\\s+to:\\s+(.+)\\)$");

        Matcher todoMatcher = todoPattern.matcher(line);
        Matcher deadlineMatcher = deadlinePattern.matcher(line);
        Matcher eventMatcher = eventPattern.matcher(line);

        if (todoMatcher.matches()) {
            Todo todo = new Todo(todoMatcher.group(2));
            setCompletionStatus(todo, todoMatcher.group(1));
            return todo;
        } else if (deadlineMatcher.matches()) {
            Deadline deadline = new Deadline(deadlineMatcher.group(2) + " /by " + deadlineMatcher.group(3));
            setCompletionStatus(deadline, deadlineMatcher.group(1));
            return deadline;
        } else if (eventMatcher.matches()) {
            Event event = new Event(eventMatcher.group(2) + " /from " + eventMatcher.group(3) + " /to " + eventMatcher.group(4));
            setCompletionStatus(event, eventMatcher.group(1));
            return event;
        }

        return null;
    }
    private static void setCompletionStatus(Task task, String status) {
        if (status.equals("[X]")) {
            task.complete();
        } else {
            task.uncomplete();
        }
    }
}