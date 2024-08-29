public class Parser {
    public static String parseCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    public static int parseIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    public static Task parseToDoTask(String input) {
        String description = input.substring(5);
        return new ToDo(description);
    }

    public static Task parseDeadlineTask(String input) {
        String[] temp = input.split(" /by ");
        return new Deadline(temp[0].substring(9), temp[1]);
    }

    public static Task parseEventTask(String input) {
        String[] temp = input.split(" /from | /to ");
        String description = temp[0].substring(6);
        String start = temp[1];
        String end = temp[2];
        return new Event(description, start, end);
    }

    public static Task parseTaskFromFile(String task) {
        return new Task(task);
    }
}
