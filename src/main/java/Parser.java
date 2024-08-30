import java.time.LocalDateTime;
import java.util.Arrays;

public class Parser {
    public static Sentinel.CommandType parseForCommand(String input) {
        String[] arr = input.split(" ");
        return Sentinel.CommandType.valueOf(arr[0].toLowerCase());
    }

    public static Task parseTask(Sentinel.CommandType commandType, String input, Ui ui) {
        String taskName = parseTaskName(commandType, input);
        if (taskName.isEmpty()) {
            ui.showEmptyTaskNameError(commandType);
            return null;
        }

        switch (commandType) {
            case todo -> {
                return new ToDo(taskName);
            }
            case deadline -> {
                String deadlineTime = parseTime(input, "/by");
                LocalDateTime deadlineDateTime = GeminiAPI.formatDateTime(deadlineTime);
                if (deadlineDateTime != null) {
                    return new Deadline(taskName, deadlineDateTime);
                } else {
                    ui.showDeadlineCommandGuidelines();
                }
            }
            case event -> {
                String fromTime = parseTime(input, "/from");
                String toTime = parseTime(input, "/to");
                LocalDateTime fromDateTime = GeminiAPI.formatDateTime(fromTime);
                LocalDateTime toDateTime = GeminiAPI.formatDateTime(toTime);
                if (fromDateTime == null || toDateTime == null) {
                    ui.showEventCommandGuidelines();
                } else if (fromDateTime.isAfter(toDateTime)){
                    ui.showEventDateOrder();
                } else return new Event(taskName, fromDateTime, toDateTime);
            }
        }
        return null;
    }
    private static String parseTaskName(Sentinel.CommandType commandType, String input) {
        String[] stringArr = input.split(" ");
        String taskName = "";

        switch (commandType) {
            case todo -> taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, stringArr.length)).trim();

            case deadline -> {
                // For 'deadline', taskName is everything after the command and before "/by"
                int byIndex = Arrays.asList(stringArr).indexOf("/by");
                taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, byIndex)).trim();
            }
            case event -> {
                // For 'event', taskName is everything after the command and before "/from"
                int fromIndex = Arrays.asList(stringArr).indexOf("/from");
                taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, fromIndex)).trim();
            }
        }

        return taskName;
    }
    private static String parseTime(String input, String delimiter) {
        String[] parts = input.split(delimiter);
        return parts.length > 1 ? parts[1].trim() : "";
    }
    public static int parseIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (Exception e) {
            return -1;
        }
    }

}
