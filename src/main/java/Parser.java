import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("eee, d MMM uuuu h:mma");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static Task parseTask(String str) {
        char c = str.charAt(0);
        Task task = null;
        String[] parts;
        boolean isDone = false;
        String taskName;

        switch (c) {
            case 'T':
                parts = str.split("\\|", 3);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                task = new Todo(taskName);
                break;
            case 'D':
                parts = str.split("\\|", 4);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                String deadline = parts[3].trim();
                task = new Deadline(taskName, LocalDateTime.parse(deadline, parseFormatter));
                break;
            case 'E':
                parts = str.split("\\|", 5);
                isDone = parts[1].trim().equals("1");
                taskName = parts[2].trim();
                String start = parts[3].trim();
                String end = parts[4].trim();
                task = new Event(taskName, LocalDateTime.parse(start, parseFormatter), LocalDateTime.parse(end, parseFormatter));
                break;
        }
        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    public static String parseCommand(String input) {
        return input.split(" ")[0];
    }

    public static LocalDateTime parseTime(String input) {
        return LocalDateTime.parse(input, dateFormatter);
    }
}
