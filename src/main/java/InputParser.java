public class InputParser {
    public static ToDo parseInputAsToDo(String input) {
        return new ToDo(input.replace("todo ", "").trim());
    }
    public static Event parseInputAsEvent(String input) {
        String[] parts = input.split(" /from ");
        String[] times = parts[1].split(" /to ");
        String description = parts[0].replace("event ", "").trim();
        String startTime = times[0].trim();
        String endTime = times[1].trim();
        return new Event(description, startTime, endTime);
    }

    public static Deadline parseInputAsDeadline(String input) {
        String[] parts = input.split(" /by ");
        String description = parts[0].replace("deadline ", "").trim();
        String deadline = parts[1].trim();
        return new Deadline(description, deadline);
    }
}
