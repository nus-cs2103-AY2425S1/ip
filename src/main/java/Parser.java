public class Parser {

    public static void handleInput(String phrase, TaskList taskList, Ui ui) throws ChatBotException {
        String[] tmp = phrase.split(" ");
        switch (tmp[0]) {
        case "list":
            taskList.listTasks(ui);
            break;
        case "mark":
            taskList.markTask(Integer.parseInt(tmp[1]) - 1, ui);
            break;
        case "unmark":
            taskList.unmarkTask(Integer.parseInt(tmp[1]) - 1, ui);
            break;
        case "todo":
            taskList.addToDo(phrase, ui);
            break;
        case "deadline":
            taskList.addDeadline(phrase, ui);
            break;
        case "event":
            taskList.addEvent(phrase, ui);
            break;
        case "delete":
            taskList.deleteTask(Integer.parseInt(tmp[1]) - 1, ui);
            break;
        case "on":
            taskList.printTasksOnDate(tmp[1], ui);
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
