public class Parser {

    public static void handleInput(String phrase, TaskList tasks, Ui ui) throws ChatBotException {
        String[] tmp = phrase.split(" ");
        switch (tmp[0]) {
        case "list":
            tasks.listTasks(ui);
            break;
        case "mark":
            tasks.getTask(Integer.parseInt(tmp[1]) - 1).mark();
            break;
        case "unmark":
            tasks.getTask(Integer.parseInt(tmp[1]) - 1).unmark();
            break;
        case "todo":
            String description = phrase.substring(5);
            tasks.addTask(new ToDo(description, TaskType.TODO));
            break;
        case "deadline":
            String[] deadlineParts = phrase.split(" /by ");
            tasks.addTask(new Deadline(deadlineParts[0].substring(9), deadlineParts[1], TaskType.DEADLINE));
            break;
        case "event":
            String[] eventParts = phrase.split(" /from | /to ");
            tasks.addTask(new Event(eventParts[0].substring(6), eventParts[1], eventParts[2], TaskType.EVENT));
            break;
        case "delete":
            tasks.removeTask(Integer.parseInt(tmp[1]) - 1);
            break;
        default:
            throw new ChatBotException("I'm sorry, but I don't know what that means.");
        }
    }

    public static Task parseTaskFromFile(String taskString) throws ChatBotException {
        String[] tmp = taskString.split(" \\| ");
        boolean isDone = !tmp[1].equals("0");

        if (!tmp[1].equals("0") && !tmp[1].equals("1")) {
            throw new ChatBotException("Corrupted task marking entry.");
        }

        switch (tmp[0]) {
        case "T":
            return new ToDo(tmp[2], isDone, TaskType.TODO);
        case "D":
            return new Deadline(tmp[2], isDone, tmp[3], TaskType.DEADLINE);
        case "E":
            return new Event(tmp[2], isDone, tmp[3], tmp[4], TaskType.EVENT);
        default:
            throw new ChatBotException("I'm sorry, there was an error reading the file. Invalid task Type.");
        }
    }
}
